package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Async
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.base.RoleType
import ru.xpendence.slimer.base.StatusCode
import ru.xpendence.slimer.base.indexes
import ru.xpendence.slimer.dto.ContactDto
import ru.xpendence.slimer.dto.RegistrationTokenDto
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.dto.message.EmailMessageDto
import ru.xpendence.slimer.dto.message.ResponceDto
import ru.xpendence.slimer.dto.message.of
import ru.xpendence.slimer.entity.User
import ru.xpendence.slimer.exceptions.DataAccessException
import ru.xpendence.slimer.exceptions.RestTemplateException
import ru.xpendence.slimer.mapper.impl.UserMapper
import ru.xpendence.slimer.repository.UserRepository
import ru.xpendence.slimer.util.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 22.06.19
 * Time: 15:09
 * e-mail: 2262288@gmail.com
 */
@Service
@ServiceImpl
class UserServiceImpl @Autowired constructor(private val encoder: BCryptPasswordEncoder)
    : AbstractService<User, UserDto, UserMapper, UserRepository>() {

    @Value("\${message.sender.path}")
    lateinit var messageSenderPath: String

    @Value("\${message.sender.port}")
    lateinit var messageSenderPort: String

    @Value("\${email.registration.text}")
    lateinit var emailRegistrationText: String

    @Value("\${slimer.path}")
    lateinit var slimerPath: String

    @Value("\${slimer.port}")
    lateinit var slimerPort: String

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var contactService: ContactServiceImpl

    @Autowired
    lateinit var registrationTokenService: RegistrationTokenServiceImpl

    override val log = logger<UserServiceImpl>()

    override fun update(dto: UserDto?): UserDto? {
        val user = repository!!.findById(dto!!.id!!)
                .orElseThrow {
                    DataAccessException(StatusCode.DATABASE_ERROR.name, "Entity with id ${dto.id} not found.")
                }

        if (dto.weight == null) dto.weight = user.weight
        if (dto.height == null) dto.height = user.height
        if (dto.gender == null) dto.gender = user.gender!!.name
        if (dto.birthDate == null) dto.birthDate = user.birthDate
        if (dto.physicalActivityIndex == null) dto.physicalActivityIndex =
                indexes[user.physicalActivityIndex]!![user.gender]

        dto.age = dto.calculateAge()
        dto.dailyCaloriesIndex = dto.calculateDci()
        dto.bodyMassIndex = dto.calculateBmi()
        dto.bmiCategory = dto.defineBmiCategory()

        return mapper!!.toDto(repository.save(mapper.toEntity(dto)))
    }

    override fun preCreate(dto: UserDto?) {
        if (dto!!.password != null) dto.password = encode(dto.password!!)
        dto.roles.add(RoleType.GUEST.name)

        calculateFields(dto)
        sendRequestToMessageService(createAndSavePrimaryContact(dto), createAndSaveRegistrationToken(dto))
    }

    fun findByLogin(login: String): UserDto =
            mapper!!.toDto(repository!!.findByLogin(login)
                    ?: throw DataAccessException(StatusCode.DATABASE_ERROR.name, "User not found by login: $login"))

    fun encode(string: String): String = encoder.encode(string)

    @Async
    fun sendRequestToMessageService(savedContact: ContactDto, savedRegistrationToken: RegistrationTokenDto) {
        val emailMessageDto = EmailMessageDto()

        log.info("registration message sent to ${emailMessageDto.to}")
        val response = restTemplate.postForEntity(
                UriComponentsBuilder.fromHttpUrl("http://$messageSenderPath:$messageSenderPort/email").toUriString(),
                emailMessageDto.of(
                        savedContact.email!!,
                        "slava_rossii@list.ru",
                        "Поздравляем с регистрацией в приложении Slimer!",
                        String.format(
                                emailRegistrationText,
                                slimerPath, slimerPort, savedContact.email, savedRegistrationToken
                        )
                ),
                ResponceDto::class.java
        )
        if (response.statusCode != HttpStatus.OK) {
            throw RestTemplateException(
                    StatusCode.EXTERNAL_REQUEST_ERROR.name,
                    "Запрос в сервис отправки сообщений на email ${savedContact.email} завершился ошибкой."
            )
        }
        log.info("Успешно отправлено письмо на email ${savedContact.email}")
    }

    private fun calculateFields(dto: UserDto) {
        dto.age = dto.calculateAge()
        log.info("age calculated: ${dto.age} for dto ${dto.hashCode()}")

        dto.dailyCaloriesIndex = dto.calculateDci()
        log.info("daily calories index calculated: ${dto.dailyCaloriesIndex} for ${dto.hashCode()}")

        dto.bodyMassIndex = dto.calculateBmi()
        log.info("body mass index calculated: ${dto.bodyMassIndex} for ${dto.hashCode()}")

        dto.bmiCategory = dto.defineBmiCategory()
        log.info("BMI category defined: ${dto.bmiCategory} for ${dto.hashCode()}")
    }

    private fun createAndSaveRegistrationToken(dto: UserDto): RegistrationTokenDto {
        val savedRegistrationToken = registrationTokenService.save(RegistrationTokenDto())
        dto.registrationTokens.add(savedRegistrationToken!!)
        return savedRegistrationToken
    }

    private fun createAndSavePrimaryContact(dto: UserDto): ContactDto {
        val primaryContact = ContactDto()
        primaryContact.email = dto.registrationEmail
        val savedContact = contactService.save(primaryContact)
        dto.contacts.add(savedContact!!)
        return savedContact
    }
}

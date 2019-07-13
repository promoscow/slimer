package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.base.RoleType
import ru.xpendence.slimer.base.StatusCode
import ru.xpendence.slimer.base.indexes
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.entity.User
import ru.xpendence.slimer.exceptions.DataAccessException
import ru.xpendence.slimer.mapper.impl.UserMapper
import ru.xpendence.slimer.repository.RoleRepository
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
class UserServiceImpl @Autowired constructor(roleRepository: RoleRepository)
    : AbstractService<User, UserDto, UserMapper, UserRepository>() {
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

    override fun validate(dto: UserDto?) {

    }

    override fun preCreate(dto: UserDto?) {
        dto!!.roles.add(RoleType.GUEST.name)

        dto.age = dto.calculateAge()
        log.info("age calculated: ${dto.age} for dto ${dto.hashCode()}")

        dto.dailyCaloriesIndex = dto.calculateDci()
        log.info("daily calories index calculated: ${dto.dailyCaloriesIndex} for ${dto.hashCode()}")

        dto.bodyMassIndex = dto.calculateBmi()
        log.info("body mass index calculated: ${dto.bodyMassIndex} for ${dto.hashCode()}")

        dto.bmiCategory = dto.defineBmiCategory()
        log.info("BMI category defined: ${dto.bmiCategory} for ${dto.hashCode()}")
    }
}
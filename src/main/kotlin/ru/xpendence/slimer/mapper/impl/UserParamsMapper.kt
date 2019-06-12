package ru.xpendence.slimer.mapper.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import ru.xpendence.slimer.dto.UserParamsDto
import ru.xpendence.slimer.entity.User
import ru.xpendence.slimer.entity.UserParams
import ru.xpendence.slimer.mapper.AbstractMapper
import ru.xpendence.slimer.mapper.Mapper
import ru.xpendence.slimer.repository.UserRepository
import javax.annotation.PostConstruct

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 23:04
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = UserParams::class, dto = UserParamsDto::class)
class UserParamsMapper : AbstractMapper<UserParams, UserParamsDto>() {

    @field:Autowired
    private lateinit var userRepository: UserRepository

    @PostConstruct
    fun init() {
        mapper.createTypeMap(UserParams::class.javaObjectType, UserParamsDto::class.javaObjectType)
                .addMappings { m ->
                    m.skip { destination: UserParamsDto?, source: UserParams? ->
                        destination?.user = source?.user?.id
                    }
//                    m.skip { destination: UserParamsDto?, source: UserParams? ->
//                        destination?.user = source?.user?.id
//                    }
                }
                .postConverter = toDtoConverter()
        mapper.createTypeMap(UserParamsDto::class.javaObjectType, UserParams::class.javaObjectType)
                .addMappings { m ->
                    m.skip { destination: UserParams?, source: UserParamsDto? ->
                        destination?.user = User()
                    }
                }
                .postConverter = toEntityConverter()
    }

    override fun mapSpecificFields(source: UserParams, destination: UserParamsDto) {
        println()
        if (source.user != null) destination.user = source.user!!.id
    }

    override fun mapSpecificFields(source: UserParamsDto, destination: UserParams) {
        if (source.user != null) destination.user = userRepository.findByIdOrNull(source.user)
    }
}

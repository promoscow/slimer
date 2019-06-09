package ru.xpendence.slimer.mapper.impl

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.xpendence.slimer.dto.UserParamsDto
import ru.xpendence.slimer.entity.UserParams
import ru.xpendence.slimer.mapper.AbstractMapper
import ru.xpendence.slimer.mapper.Mapper
import javax.annotation.PostConstruct

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 23:04
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = UserParams::class, dto = UserParamsDto::class)
class UserParamsMapper @Autowired constructor(mapper: ModelMapper) : AbstractMapper<UserParams, UserParamsDto> (mapper) {

    @PostConstruct
    fun init() {

    }
}
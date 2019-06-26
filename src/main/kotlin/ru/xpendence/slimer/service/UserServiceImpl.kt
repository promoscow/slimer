package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.entity.User
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
class UserServiceImpl : AbstractService<User, UserDto, UserMapper, UserRepository>() {
    override val log = logger<UserServiceImpl>()

    override fun validate(dto: UserDto?) {

    }

    override fun preExecution(dto: UserDto?) {
        dto!!.age = dto.calculateAge()
        log.info("age calculated: ${dto.age} for dto ${dto.hashCode()}")
        dto.dailyCaloriesIndex = dto.calculateDci()
        log.info("daily calories index calculated: ${dto.dailyCaloriesIndex} for ${dto.hashCode()}")
        dto.bodyMassIndex = dto.calculateBmi()
        log.info("body mass index calculated: ${dto.bodyMassIndex} for ${dto.hashCode()}")
        dto.bmiCategory = dto.defineBmiCategory()
        log.info("BMI category defined: ${dto.bmiCategory} for ${dto.hashCode()}")
    }
}
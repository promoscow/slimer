package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.entity.User
import ru.xpendence.slimer.mapper.impl.UserMapper
import ru.xpendence.slimer.repository.UserRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 22.06.19
 * Time: 15:09
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class UserServiceImpl : AbstractService<User, UserDto, UserMapper, UserRepository>() {

    override fun validate(dto: UserDto?) {

    }

    override fun calculate(dto: UserDto?) {

    }
}
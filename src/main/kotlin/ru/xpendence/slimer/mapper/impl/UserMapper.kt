package ru.xpendence.slimer.mapper.impl

import org.springframework.stereotype.Service
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.entity.User
import ru.xpendence.slimer.mapper.AbstractMapper
import ru.xpendence.slimer.mapper.Mapper

/**
 * Author: Vyacheslav Chernyshov
 * Date: 6/13/19
 * Time: 4:18 PM
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@Mapper(entity = User::class, dto = UserDto::class)
class UserMapper : AbstractMapper<User, UserDto>()
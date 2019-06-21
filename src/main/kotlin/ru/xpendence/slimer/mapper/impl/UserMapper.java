package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.UserDto;
import ru.xpendence.slimer.entity.User;
import ru.xpendence.slimer.mapper.AbstractMapper;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-13
 * Time: 19:41
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = User.class, dto = UserDto.class)
public class UserMapper extends AbstractMapper<User, UserDto> {
}

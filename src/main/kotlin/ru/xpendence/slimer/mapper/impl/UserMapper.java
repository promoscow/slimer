package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.dto.UserDto;
import ru.xpendence.slimer.entity.User;
import ru.xpendence.slimer.mapper.AbstractMapper;
import ru.xpendence.slimer.mapper.Mapper;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-13
 * Time: 19:41
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = User.class, dto = UserDto.class)
public class UserMapper extends AbstractMapper<User, UserDto> {
//
//    private final UserParamsMapper userParamsMapper;
//
//    public UserMapper(UserParamsMapper userParamsMapper) {
//        this.userParamsMapper = userParamsMapper;
//    }
//
//    @PostConstruct
//    public void init() {
//        mapper.createTypeMap(User.class, UserDto.class)
//                .addMappings(m -> m.skip(UserDto::setParams)).setPostConverter(toDtoConverter());
//        mapper.createTypeMap(UserDto.class, User.class)
//                .addMappings(m -> m.skip(User::setParams)).setPostConverter(toEntityConverter());
//    }
//
//    @Override
//    protected void mapSpecificFields(User source, UserDto destination) {
//        whenNotNull(source.getParams(), userParamsMapper::toDto);
//    }
//
//    @Override
//    protected void mapSpecificFields(UserDto source, User destination) {
//        whenNotNull(source.getParams(), userParamsMapper::toEntity);
//    }
}

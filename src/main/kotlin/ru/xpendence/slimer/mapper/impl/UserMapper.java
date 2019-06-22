package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.base.PAI;
import ru.xpendence.slimer.dto.UserDto;
import ru.xpendence.slimer.entity.User;
import ru.xpendence.slimer.mapper.AbstractMapper;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-13
 * Time: 19:41
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = User.class, dto = UserDto.class)
public class UserMapper extends AbstractMapper<User, UserDto> {

    @PostConstruct
    public void init() {
        mapper.createTypeMap(User.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setPhysicalActivityIndex)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserDto.class, User.class)
                .addMappings(m -> m.skip(User::setPhysicalActivityIndex)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(User source, UserDto destination) {
        whenNotNull(
                source.getPhysicalActivityIndex(),
                i -> destination.setPhysicalActivityIndex(
                        PAI.getIndexes().get(source.getPhysicalActivityIndex()).get(source.getGender())
                )
        );
    }

    @Override
    protected void mapSpecificFields(UserDto source, User destination) {
//        whenNotNull(source.getPhysicalActivityIndex(), i -> destination.setDailyCaloriesIndex(PAI.getIndexes().values()));
    }
}

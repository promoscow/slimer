package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.base.BmiCategory;
import ru.xpendence.slimer.base.PAI;
import ru.xpendence.slimer.dto.UserDto;
import ru.xpendence.slimer.entity.User;
import ru.xpendence.slimer.mapper.AbstractMapper;

import javax.annotation.PostConstruct;
import java.util.Map;

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
                .addMappings(m -> {
                    m.skip(UserDto::setPhysicalActivityIndex);
                    m.skip(UserDto::setBmiCategory);
                }).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserDto.class, User.class)
                .addMappings(m -> {
                    m.skip(User::setPhysicalActivityIndex);
                    m.skip(User::setBmiCategory);
                }).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(User source, UserDto destination) {
        whenNotNull(
                source.getPhysicalActivityIndex(),
                i -> destination.setPhysicalActivityIndex(
                        PAI.getIndexes().get(source.getPhysicalActivityIndex()).get(source.getGender())
                )
        );
        whenNotNull(source.getBmiCategory(), c -> destination.setBmiCategory(c.name()));
    }

    @Override
    protected void mapSpecificFields(UserDto source, User destination) {
        whenNotNull(source.getPhysicalActivityIndex(),
                i -> destination.setPhysicalActivityIndex(
                        PAI.getIndexes().entrySet()
                                .stream()
                                .filter(e -> e.getValue().containsKey(destination.getGender())
                                        && e.getValue().containsValue(i))
                                .findFirst().map(Map.Entry::getKey).orElse(null)
                )
        );
        whenNotNull(source.getBmiCategory(), c -> destination.setBmiCategory(BmiCategory.valueOf(c)));
    }
}

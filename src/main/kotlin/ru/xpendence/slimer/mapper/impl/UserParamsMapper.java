package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.dto.UserParamsDto;
import ru.xpendence.slimer.entity.UserParams;
import ru.xpendence.slimer.mapper.AbstractMapper;
import ru.xpendence.slimer.mapper.Mapper;
import ru.xpendence.slimer.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-13
 * Time: 18:47
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = UserParams.class, dto = UserParamsDto.class)
public class UserParamsMapper extends AbstractMapper<UserParams, UserParamsDto> {

    private final UserRepository userRepository;

    public UserParamsMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(UserParams.class, UserParamsDto.class)
                .addMappings(m -> m.skip(UserParamsDto::setUser)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserParamsDto.class, UserParams.class)
                .addMappings(m -> m.skip(UserParams::setUser)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(UserParams source, UserParamsDto destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(u.getId()));
    }

    @Override
    protected void mapSpecificFields(UserParamsDto source, UserParams destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(userRepository.findById(u).orElse(null)));
    }
}

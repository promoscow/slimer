package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.RegistrationTokenDto;
import ru.xpendence.slimer.entity.RegistrationToken;
import ru.xpendence.slimer.mapper.AbstractMapper;
import ru.xpendence.slimer.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 19.07.19
 * Time: 21:40
 * e-mail: v.chernyshov@pflb.ru
 */
@Mapper(entity = RegistrationToken.class, dto = RegistrationTokenDto.class)
@Component
public class RegistrationTokenMapper extends AbstractMapper<RegistrationToken, RegistrationTokenDto> {

    private final UserRepository userRepository;

    public RegistrationTokenMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(RegistrationToken.class, RegistrationTokenDto.class)
                .addMappings(m -> m.skip(RegistrationTokenDto::setUser)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(RegistrationTokenDto.class, RegistrationToken.class)
                .addMappings(m -> m.skip(RegistrationToken::setUser)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(RegistrationToken source, RegistrationTokenDto destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(u.getId()));
    }

    @Override
    protected void mapSpecificFields(RegistrationTokenDto source, RegistrationToken destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(userRepository.findById(u).orElse(null)));
    }
}

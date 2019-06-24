package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.base.ProgramType;
import ru.xpendence.slimer.dto.ProgramDto;
import ru.xpendence.slimer.entity.Program;
import ru.xpendence.slimer.mapper.AbstractMapper;
import ru.xpendence.slimer.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 11:18
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
@Mapper(entity = Program.class, dto = ProgramDto.class)
public class ProgramMapper extends AbstractMapper<Program, ProgramDto> {

    private final UserRepository userRepository;

    public ProgramMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(Program.class, ProgramDto.class)
                .addMappings(m -> {
                    m.skip(ProgramDto::setUser);
                    m.skip(ProgramDto::setProgramType);
                }).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ProgramDto.class, Program.class)
                .addMappings(m -> {
                    m.skip(Program::setUser);
                    m.skip(Program::setProgramType);
                }).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Program source, ProgramDto destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(u.getId()));
        whenNotNull(source.getProgramType(), p -> destination.setProgramType(p.name()));
    }

    @Override
    protected void mapSpecificFields(ProgramDto source, Program destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(userRepository.findById(u).orElse(null)));
        whenNotNull(source.getProgramType(), p -> destination.setProgramType(ProgramType.valueOf(p)));
    }
}

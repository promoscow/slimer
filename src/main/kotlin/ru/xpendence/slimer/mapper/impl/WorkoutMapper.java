package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.WorkoutDto;
import ru.xpendence.slimer.entity.Workout;
import ru.xpendence.slimer.mapper.AbstractMapper;
import ru.xpendence.slimer.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.07.19
 * Time: 13:21
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
@Mapper(entity = Workout.class, dto = WorkoutDto.class)
public class WorkoutMapper extends AbstractMapper<Workout, WorkoutDto> {

    private final UserRepository userRepository;

    public WorkoutMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(Workout.class, WorkoutDto.class)
                .addMappings(m -> m.skip(WorkoutDto::setUser)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(WorkoutDto.class, Workout.class)
                .addMappings(m -> m.skip(Workout::setUser)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Workout source, WorkoutDto destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(u.getId()));
    }

    @Override
    protected void mapSpecificFields(WorkoutDto source, Workout destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(userRepository.findById(u).orElse(null)));
    }
}

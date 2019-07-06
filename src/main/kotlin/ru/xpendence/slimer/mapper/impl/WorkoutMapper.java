package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.WorkoutDto;
import ru.xpendence.slimer.entity.Workout;
import ru.xpendence.slimer.mapper.AbstractMapper;
import ru.xpendence.slimer.repository.ActivityRepository;
import ru.xpendence.slimer.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.07.19
 * Time: 13:21
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = Workout.class, dto = WorkoutDto.class)
public class WorkoutMapper extends AbstractMapper<Workout, WorkoutDto> {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public WorkoutMapper(UserRepository userRepository,
                         ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(Workout.class, WorkoutDto.class)
                .addMappings(m -> {
                    m.skip(WorkoutDto::setUser);
                    m.skip(WorkoutDto::setActivity);
                }).setPostConverter(toDtoConverter());
        mapper.createTypeMap(WorkoutDto.class, Workout.class)
                .addMappings(m -> {
                    m.skip(Workout::setUser);
                    m.skip(Workout::setActivity);
                }).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Workout source, WorkoutDto destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(u.getId()));
        whenNotNull(source.getActivity(), a -> destination.setActivity(a.getId()));
    }

    @Override
    protected void mapSpecificFields(WorkoutDto source, Workout destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(userRepository.findById(u).orElse(null)));
        whenNotNull(source.getActivity(), a -> destination.setActivity(activityRepository.findById(a).orElse(null)));
    }
}

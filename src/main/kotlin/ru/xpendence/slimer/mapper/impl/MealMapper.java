package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.MealDto;
import ru.xpendence.slimer.entity.Meal;
import ru.xpendence.slimer.mapper.AbstractMapper;
import ru.xpendence.slimer.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:42
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
@Mapper(entity = Meal.class, dto = MealDto.class)
public class MealMapper extends AbstractMapper<Meal, MealDto> {

    private final UserRepository userRepository;

    public MealMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(Meal.class, MealDto.class)
                .addMappings(m -> m.skip(MealDto::setUser)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(MealDto.class, Meal.class)
                .addMappings(m -> m.skip(Meal::setUser)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Meal source, MealDto destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(u.getId()));
    }

    @Override
    protected void mapSpecificFields(MealDto source, Meal destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(userRepository.findById(u).orElse(null)));
    }
}

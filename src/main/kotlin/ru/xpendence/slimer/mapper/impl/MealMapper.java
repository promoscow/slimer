package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.MealDto;
import ru.xpendence.slimer.entity.Meal;
import ru.xpendence.slimer.mapper.AbstractMapper;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:42
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
@Mapper(entity = Meal.class, dto = MealDto.class)
public class MealMapper extends AbstractMapper<Meal, MealDto> {
}

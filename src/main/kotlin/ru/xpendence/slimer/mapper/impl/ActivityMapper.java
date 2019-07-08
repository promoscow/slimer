package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.ActivityDto;
import ru.xpendence.slimer.entity.Activity;
import ru.xpendence.slimer.mapper.AbstractMapper;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.07.19
 * Time: 12:58
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = Activity.class, dto = ActivityDto.class)
public class ActivityMapper extends AbstractMapper<Activity, ActivityDto> {
}
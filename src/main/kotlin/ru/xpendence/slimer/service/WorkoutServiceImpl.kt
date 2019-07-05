package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.WorkoutDto
import ru.xpendence.slimer.entity.Workout
import ru.xpendence.slimer.mapper.impl.WorkoutMapper
import ru.xpendence.slimer.repository.WorkoutRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 23:21
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class WorkoutServiceImpl : AbstractService<Workout, WorkoutDto, WorkoutMapper, WorkoutRepository>()
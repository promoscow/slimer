package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.DayWorkoutsDto
import ru.xpendence.slimer.dto.WorkoutDto
import ru.xpendence.slimer.entity.Workout
import ru.xpendence.slimer.mapper.impl.WorkoutMapper
import ru.xpendence.slimer.repository.WorkoutRepository
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 23:21
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class WorkoutServiceImpl : AbstractService<Workout, WorkoutDto, WorkoutMapper, WorkoutRepository>() {

    fun getStatsByDateForUser(userId: Long, date: LocalDate): DayWorkoutsDto =
            DayWorkoutsDto(
                    userId,
                    date,
                    repository!!.getAllByDateForUser(userId, date)
                            .also { w -> if (w.isEmpty()) return DayWorkoutsDto(userId, date, 0) }
                            .map { w -> mapper!!.toDto(w) }
                            .sumBy { it.activity!!.calories!!.times(it.duration!!).div(60) }
            )
}
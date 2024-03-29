package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
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
 * e-mail: 2262288@gmail.com
 */
@Service
@ServiceImpl
class WorkoutServiceImpl @Autowired constructor(private val activityService: ActivityServiceImpl)
    : AbstractService<Workout, WorkoutDto, WorkoutMapper, WorkoutRepository>() {

    fun getStatsByDateForUser(userId: Long, date: LocalDate): DayWorkoutsDto =
            DayWorkoutsDto(
                    userId,
                    date,
                    repository!!.getAllByDateForUser(userId, date)
                            .also { w -> if (w.isEmpty()) return DayWorkoutsDto(userId, date, 0) }
                            .map { w -> mapper!!.toDto(w) }
                            .associate { it to activityService.get(it.activity!!) }
                            .map { (k, v) -> v!!.calories!!.times(k.duration!!).div(60) }
                            .sum()
            )
}
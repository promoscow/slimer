package ru.xpendence.slimer.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.xpendence.slimer.entity.Workout
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 23:41
 * e-mail: v.chernyshov@pflb.ru
 */
@Repository
interface WorkoutRepository : CommonRepository<Workout> {

    @Query(
            value = "select * from workouts where user_id = :userId and date = :requestedDate",
            nativeQuery = true
    )
    fun getAllByDateForUser(userId: Long, requestedDate: LocalDate): List<Workout>
}
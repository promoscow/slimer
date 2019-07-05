package ru.xpendence.slimer.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.xpendence.slimer.entity.Meal
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 23:40
 * e-mail: v.chernyshov@pflb.ru
 */
@Repository
interface MealRepository : CommonRepository<Meal> {

    @Query(
            value = "select * from meals where user_id = :userId and date = :requestedDate",
            nativeQuery = true
    )
    fun getAllByDateForUser(userId: Long, requestedDate: LocalDate): List<Meal>
}
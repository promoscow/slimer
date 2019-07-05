package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.base.StatusCode
import ru.xpendence.slimer.dto.CommonDayStatsDto
import ru.xpendence.slimer.entity.CommonDayStats
import ru.xpendence.slimer.exceptions.ApiException
import ru.xpendence.slimer.mapper.CommonDayStatsMapper
import ru.xpendence.slimer.repository.CommonDayStatsRepository
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 8:39
 * e-mail: v.chernyshov@pflb.ru
 */
@ServiceImpl
@Service
class CommonDayStatsServiceImpl @Autowired constructor(private val mealService: MealServiceImpl)
    : AbstractService<CommonDayStats, CommonDayStatsDto, CommonDayStatsMapper, CommonDayStatsRepository>() {

    fun calculateAndSave(userId: Long) {
        checkIfStatsExists(userId)
        val totalCalories = mealService.getCaloriesByDateForUser(userId, LocalDate.now().minusDays(1))

    }

    private fun checkIfStatsExists(userId: Long) {
        if (repository!!.getByUserIdAndDate(userId, LocalDate.now().minusDays(1)) != null) {
            throw ApiException(
                    StatusCode.BAD_REQUEST.name,
                    "Stats for user $userId and date ${LocalDate.now().minusDays(1)} already generated"
            )
        }
    }
}
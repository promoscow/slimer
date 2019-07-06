package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.base.StatusCode
import ru.xpendence.slimer.dto.CommonDayStatsDto
import ru.xpendence.slimer.dto.of
import ru.xpendence.slimer.entity.CommonDayStats
import ru.xpendence.slimer.exceptions.ApiException
import ru.xpendence.slimer.mapper.CommonDayStatsMapper
import ru.xpendence.slimer.repository.CommonDayStatsRepository
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 8:39
 * e-mail: 2262288@gmail.com
 */
@ServiceImpl
@Service
class CommonDayStatsServiceImpl @Autowired constructor(
        private val mealService: MealServiceImpl,
        private val workoutService: WorkoutServiceImpl,
        private val programService: ProgramServiceImpl
) : AbstractService<CommonDayStats, CommonDayStatsDto, CommonDayStatsMapper, CommonDayStatsRepository>() {

    fun calculateAndSave(userId: Long): CommonDayStatsDto {
        checkIfStatsExists(userId)
        val totalCalories = mealService.getCaloriesByDateForUser(userId, LocalDate.now().minusDays(1))
        val totalBurn = workoutService.getStatsByDateForUser(userId, LocalDate.now().minusDays(1)).calories
        val dto = CommonDayStatsDto()
        return mapper!!.toDto(
                repository!!.save(
                        mapper.toEntity(
                                dto.of(
                                        userId,
                                        LocalDate.now().minusDays(1),
                                        isGoalReached(userId, totalCalories, totalBurn),
                                        totalCalories,
                                        totalBurn
                                )
                        )
                )
        )
    }

    private fun isGoalReached(userId: Long, totalCalories: Int, totalBurn: Int) =
            programService.getCaloriesByDate(userId, LocalDate.now().minusDays(1)).calories > (totalCalories - totalBurn)

    private fun checkIfStatsExists(userId: Long) {
        if (repository!!.getByUserIdAndDate(userId, LocalDate.now().minusDays(1)) != null) {
            throw ApiException(
                    StatusCode.BAD_REQUEST.name,
                    "Stats for user $userId and date ${LocalDate.now().minusDays(1)} already generated"
            )
        }
    }
}
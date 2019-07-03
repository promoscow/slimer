package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.DayNutrientsDto
import ru.xpendence.slimer.dto.MealDto
import ru.xpendence.slimer.entity.Meal
import ru.xpendence.slimer.mapper.impl.MealMapper
import ru.xpendence.slimer.repository.MealRepository
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:49
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class MealServiceImpl : AbstractService<Meal, MealDto, MealMapper, MealRepository>() {

    fun getAllByDateForUser(userId: Long, date: LocalDate): List<MealDto> =
            repository!!.getAllByDateForUser(userId, date).map { mapper!!.toDto(it) }

    fun getStatsByDateForUser(userId: Long, date: LocalDate): DayNutrientsDto {
        return repository!!.getAllByDateForUser(userId, date)
                .also { meals ->
                    if (meals.isEmpty()) return DayNutrientsDto(
                            0.0, 0.0, 0.0, 0, userId, date
                    )
                }
                .map { mapper!!.toDto(it) }
                .flatMap { it.portions }
                .map { p ->
                    DayNutrientsDto(
                            p.product!!.proteins!!.times(p.weight!!).div(100),
                            p.product!!.carbohydrates!!.times(p.weight!!).div(100),
                            p.product!!.fats!!.times(p.weight!!).div(100),
                            p.product!!.calories!!.times(p.weight!!).div(100),
                            userId,
                            date
                    )
                }
                .reduce { acc, el ->
                    DayNutrientsDto(
                            acc.proteins + el.proteins,
                            acc.carbohydrates + el.carbohydrates,
                            acc.fats + el.fats,
                            acc.calories + el.calories,
                            userId,
                            date
                    )
                }
    }
}
package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
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
 * e-mail: 2262288@gmail.com
 */
@Service
@ServiceImpl
class MealServiceImpl @Autowired constructor(private val productService: ProductServiceImpl)
    : AbstractService<Meal, MealDto, MealMapper, MealRepository>() {

    fun getAllByDateForUser(userId: Long, date: LocalDate): List<MealDto> =
            repository!!.getAllByDateForUser(userId, date).map { mapper!!.toDto(it) }

    fun getStatsByDateForUser(userId: Long, date: LocalDate): DayNutrientsDto =
            repository!!.getAllByDateForUser(userId, date)
                    .also { meals ->
                        if (meals.isEmpty()) return DayNutrientsDto(
                                0.0, 0.0, 0.0, 0, userId, date
                        )
                    }
                    .map { mapper!!.toDto(it) }
                    .flatMap { it.portions }
                    .associate { it to productService.get(it.product!!) }
                    .map { (k, v) ->
                        DayNutrientsDto(
                                v!!.proteins!!.times(k.weight!!).div(100),
                                v.carbohydrates!!.times(k.weight!!).div(100),
                                v.fats!!.times(k.weight!!).div(100),
                                v.calories!!.times(k.weight!!).div(100),
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

    fun getCaloriesByDateForUser(userId: Long, date: LocalDate): Int =
            repository!!.getAllByDateForUser(userId, date)
                    .also { meals -> if (meals.isEmpty()) return 0 }
                    .map { mapper!!.toDto(it) }
                    .flatMap { it.portions }
                    .associate { it to productService.get(it.product!!) }
                    .map { (k, v) -> v!!.calories!!.times(k.weight!!).div(100) }
                    .sum()

}
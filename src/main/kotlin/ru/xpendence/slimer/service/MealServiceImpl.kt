package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.DayNutrientsDto
import ru.xpendence.slimer.dto.MealDto
import ru.xpendence.slimer.entity.Meal
import ru.xpendence.slimer.mapper.impl.MealMapper
import ru.xpendence.slimer.repository.MealRepository
import ru.xpendence.slimer.repository.ProductRepository
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:49
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class MealServiceImpl @Autowired constructor(private val productRepository: ProductRepository)
    : AbstractService<Meal, MealDto, MealMapper, MealRepository>() {

    fun getAllByDateForUser(userId: Long, date: LocalDate): List<MealDto> =
            repository!!.getAllByDateForUser(userId, date).map { mapper!!.toDto(it) }

    //взять все продукты
    //получить их из базы
    //взять все порции
    //получить и вернуть объект из сумм БЖУ по порциям
    fun getStatsByDateForUser(userId: Long, date: LocalDate): DayNutrientsDto {
//        val meals: List<MealDto> = repository!!.getAllByDateForUser(userId, date).map { mapper!!.toDto(it) }
//        val products: List<Product> = meals.flatMap { m -> productRepository.findAllById(m.portions.map { it.product!!.id }.toSet()) }
//
//        val portions: List<PortionDto> = meals.flatMap { it.portions }

        return repository!!.getAllByDateForUser(userId, date)
                .map { mapper!!.toDto(it) }
                .flatMap { it.portions }
                .map { p ->
                    DayNutrientsDto(
                            p.product!!.proteins!!.times(p.weight!!),
                            p.product!!.carbohydrates!!.times(p.weight!!),
                            p.product!!.fats!!.times(p.weight!!),
                            p.product!!.calories!!.times(p.weight!!),
                            userId,
                            date
                    )
                }.reduce { acc, el ->
                    DayNutrientsDto(
                            acc.proteins + el.proteins,
                            acc.carbohydrates + el.carbohydrates,
                            acc.fats + el.fats,
                            acc.calories + el.calories,
                            userId,
                            date
                    )
                }

//        flatMap
//                .map { p ->
//                    meals.flatMap { it.portions }.map {
//                        DayNutrientsDto(
//                                p.proteins!!.times(it.weight!!),
//                                p.carbohydrates!!.times(it.weight!!),
//                                p.fats!!.times(it.weight!!),
//                                p.calories!!.times(it.weight!!),
//                                userId,
//                                date)
//                    }
//                }

    }


}


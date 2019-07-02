package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.DayNutritionsDto
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
    fun getStatsByDateForUser(userId: Long, date: LocalDate): DayNutritionsDto {
        val meals: List<MealDto> = repository!!.getAllByDateForUser(userId, date).map { mapper!!.toDto(it) }
        meals
                .flatMap { meal -> productRepository.findAllById(meal.portions.map { it.product }.toSet()) }
                .
    }


}


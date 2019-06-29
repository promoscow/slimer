package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
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
}
package ru.xpendence.slimer.dto

import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 30.06.19
 * Time: 18:08
 * e-mail: 2262288@gmail.com
 */
data class DayNutrientsDto(val proteins: Double,
                           val carbohydrates: Double,
                           val fats: Double,
                           val calories: Int,
                           val userId: Long,
                           val date: LocalDate)
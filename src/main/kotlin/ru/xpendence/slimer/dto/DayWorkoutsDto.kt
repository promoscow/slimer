package ru.xpendence.slimer.dto

import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 23:34
 * e-mail: v.chernyshov@pflb.ru
 */
data class DayWorkoutsDto(val user: Long, val date: LocalDate, val calories: Int)
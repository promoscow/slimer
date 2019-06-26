package ru.xpendence.slimer.dto

import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.06.19
 * Time: 13:23
 * e-mail: 2262288@gmail.com
 */
data class TodayCaloriesDto(val user: Long, val date: LocalDate, val calories: Int) : AbstractDto()

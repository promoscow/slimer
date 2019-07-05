package ru.xpendence.slimer.dto

import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 8:29
 * e-mail: v.chernyshov@pflb.ru
 */
open class CommonDayStatsDto : AbstractDto() {

    open var user: Long? = null
    open var date: LocalDate? = null
    open var goal: Boolean? = null
    open var caloriesConsumed: Long? = null
    open var caloriesBurn: Long? = null
}
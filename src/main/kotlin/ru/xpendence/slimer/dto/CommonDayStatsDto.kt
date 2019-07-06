package ru.xpendence.slimer.dto

import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 8:29
 * e-mail: 2262288@gmail.com
 */
open class CommonDayStatsDto : AbstractDto() {

    open var user: Long? = null
    open var date: LocalDate? = null
    open var goal: Boolean? = null
    open var caloriesConsumed: Int? = null
    open var caloriesBurn: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as CommonDayStatsDto

        if (user != other.user) return false
        if (date != other.date) return false
        if (goal != other.goal) return false
        if (caloriesConsumed != other.caloriesConsumed) return false
        if (caloriesBurn != other.caloriesBurn) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + (goal?.hashCode() ?: 0)
        result = 31 * result + (caloriesConsumed?.hashCode() ?: 0)
        result = 31 * result + (caloriesBurn?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "CommonDayStatsDto(user=$user, date=$date, goal=$goal, caloriesConsumed=$caloriesConsumed, " +
                "caloriesBurn=$caloriesBurn)"
    }
}

fun CommonDayStatsDto.of(user: Long,
                         date: LocalDate,
                         goal: Boolean,
                         caloriesConsumed: Int,
                         caloriesBurn: Int): CommonDayStatsDto {
    this.user = user
    this.date = date
    this.goal = goal
    this.caloriesConsumed = caloriesConsumed
    this.caloriesBurn = caloriesBurn
    return this
}
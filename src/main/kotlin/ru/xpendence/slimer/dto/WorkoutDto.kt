package ru.xpendence.slimer.dto

import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.07.19
 * Time: 12:49
 * e-mail: 2262288@gmail.com
 */
open class WorkoutDto : AbstractDto() {

    open var user: Long? = null
    open var activity: ActivityDto? = null
    open var duration: Int? = null
    open var date: LocalDate? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as WorkoutDto

        if (user != other.user) return false
        if (activity != other.activity) return false
        if (duration != other.duration) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (activity?.hashCode() ?: 0)
        result = 31 * result + (duration ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "WorkoutDto(user=$user, activity=$activity, duration=$duration, date=$date)"
    }


}
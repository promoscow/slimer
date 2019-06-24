package ru.xpendence.slimer.dto

import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 9:28
 * e-mail: v.chernyshov@pflb.ru
 */
open class ProgramDto : AbstractDto() {

    open var user: Long? = null
    open var finished: Boolean? = null
    open var startWeight: Double? = null
    open var estimatedDays: Int? = null
    open var goalWeight: Double? = null
    open var programType: String? = null
    open var estimatedFinishDate: LocalDate? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ProgramDto

        if (user != other.user) return false
        if (finished != other.finished) return false
        if (startWeight != other.startWeight) return false
        if (estimatedDays != other.estimatedDays) return false
        if (goalWeight != other.goalWeight) return false
        if (programType != other.programType) return false
        if (estimatedFinishDate != other.estimatedFinishDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (finished?.hashCode() ?: 0)
        result = 31 * result + (startWeight?.hashCode() ?: 0)
        result = 31 * result + (estimatedDays ?: 0)
        result = 31 * result + (goalWeight?.hashCode() ?: 0)
        result = 31 * result + (programType?.hashCode() ?: 0)
        result = 31 * result + (estimatedFinishDate?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "ProgramDto(user=$user, finished=$finished, startWeight=$startWeight, estimatedDays=$estimatedDays, " +
                "goalWeight=$goalWeight, programType=$programType, estimatedFinishDate=$estimatedFinishDate)"
    }


}
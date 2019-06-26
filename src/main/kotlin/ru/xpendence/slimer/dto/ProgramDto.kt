package ru.xpendence.slimer.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ru.xpendence.slimer.dto.validation.Validate
import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 9:28
 * e-mail: 2262288@gmail.com
 */
open class ProgramDto : AbstractDto() {

    @NotNull(groups = [Validate.Create::class])
    open var user: Long? = null

    @Null(groups = [Validate.Create::class])
    open var finished: Boolean? = null

    @Null(groups = [Validate.Create::class])
    open var startWeight: Double? = null

    @Null(groups = [Validate.Create::class])
    open var estimatedDays: Int? = null

    @NotNull(groups = [Validate.Create::class])
    open var goalWeight: Double? = null

    @NotNull(groups = [Validate.Create::class])
    open var programType: String? = null

    @Null(groups = [Validate.Create::class])
    open var estimatedFinishDate: LocalDate? = null

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    open var actual: Boolean = true

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
        if (actual != other.actual) return false

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
        result = 31 * result + (actual?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "ProgramDto(user=$user, finished=$finished, startWeight=$startWeight, estimatedDays=$estimatedDays, " +
                "goalWeight=$goalWeight, programType=$programType, estimatedFinishDate=$estimatedFinishDate, " +
                "actual=$actual)"
    }
}
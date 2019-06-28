package ru.xpendence.slimer.dto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:34
 * e-mail: v.chernyshov@pflb.ru
 */
open class MealDto : AbstractDto() {

    open var portions: MutableList<PortionDto> = ArrayList()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as MealDto

        if (portions != other.portions) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + portions.hashCode()
        return result
    }

    override fun toString(): String {
        return "MealDto(portions=$portions)"
    }
}
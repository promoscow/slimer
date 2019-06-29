package ru.xpendence.slimer.dto

import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:34
 * e-mail: v.chernyshov@pflb.ru
 */
open class MealDto : AbstractDto() {

    open var name: String? = null
    open var portions: MutableList<PortionDto> = ArrayList()
    open var date: LocalDate? = null
    open var user: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as MealDto

        if (name != other.name) return false
        if (portions != other.portions) return false
        if (date != other.date) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + portions.hashCode()
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + (user?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "MealDto(name=$name, portions=$portions, date=$date, user=$user)"
    }
}
package ru.xpendence.slimer.dto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:15
 * e-mail: v.chernyshov@pflb.ru
 */
open class PortionDto : AbstractDto() {

    open var product: Long? = null
    open var weight: Int? = null
    open var meal: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as PortionDto

        if (product != other.product) return false
        if (weight != other.weight) return false
        if (meal != other.meal) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (product?.hashCode() ?: 0)
        result = 31 * result + (weight ?: 0)
        result = 31 * result + (meal?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "PortionDto(product=$product, weight=$weight, meal=$meal)"
    }

}
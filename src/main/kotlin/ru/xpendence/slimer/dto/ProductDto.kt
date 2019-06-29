package ru.xpendence.slimer.dto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-27
 * Time: 20:21
 * e-mail: 2262288@gmail.com
 */
open class ProductDto : AbstractDto() {

    open var name: String? = null
    open var proteins: Double? = null
    open var carbohydrates: Double? = null
    open var fats: Double? = null
    open var calories: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ProductDto

        if (name != other.name) return false
        if (proteins != other.proteins) return false
        if (carbohydrates != other.carbohydrates) return false
        if (fats != other.fats) return false
        if (calories != other.calories) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (proteins?.hashCode() ?: 0)
        result = 31 * result + (carbohydrates?.hashCode() ?: 0)
        result = 31 * result + (fats?.hashCode() ?: 0)
        result = 31 * result + (calories ?: 0)
        return result
    }

    override fun toString(): String {
        return "ProductDto(name=$name, proteins=$proteins, carbohydrates=$carbohydrates, fats=$fats, calories=$calories)"
    }
}
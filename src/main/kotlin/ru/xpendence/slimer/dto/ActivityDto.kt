package ru.xpendence.slimer.dto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.07.19
 * Time: 12:44
 * e-mail: 2262288@gmail.com
 */
open class ActivityDto() : AbstractDto() {

    var name: String? = null
    var type: String? = null
    var calories: Int? = null

    constructor(name: String?, type: String?, calories: Int?) : this() {
        this.name = name
        this.type = type
        this.calories = calories
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ActivityDto

        if (name != other.name) return false
        if (type != other.type) return false
        if (calories != other.calories) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (calories?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "ActivityDto(name=$name, type=$type, calories=$calories)"
    }
}
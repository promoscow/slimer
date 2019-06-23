package ru.xpendence.slimer.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import javax.validation.constraints.Null

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:11
 * e-mail: 2262288@gmail.com
 */
open class UserDto : AbstractDto() {

    open var height: Int? = null
    open var weight: Double? = null

    @Null
    open var age: Int? = null

    open var gender: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    open var birthDate: LocalDate? = null

    @Null
    open val contacts: List<ContactDto> = ArrayList()

    @Null
    open var dailyCaloriesIndex: Int? = null

    open var physicalActivityIndex: Double? = null

    @Null
    open var bodyMassIndex: Double? = null

    open var bmiCategory: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as UserDto

        if (height != other.height) return false
        if (weight != other.weight) return false
        if (age != other.age) return false
        if (gender != other.gender) return false
        if (birthDate != other.birthDate) return false
        if (contacts != other.contacts) return false
        if (dailyCaloriesIndex != other.dailyCaloriesIndex) return false
        if (physicalActivityIndex != other.physicalActivityIndex) return false
        if (bodyMassIndex != other.bodyMassIndex) return false
        if (bmiCategory != other.bmiCategory) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (height ?: 0)
        result = 31 * result + (weight?.hashCode() ?: 0)
        result = 31 * result + (age ?: 0)
        result = 31 * result + (gender?.hashCode() ?: 0)
        result = 31 * result + (birthDate?.hashCode() ?: 0)
        result = 31 * result + contacts.hashCode()
        result = 31 * result + (dailyCaloriesIndex ?: 0)
        result = 31 * result + (physicalActivityIndex?.hashCode() ?: 0)
        result = 31 * result + (bodyMassIndex?.hashCode() ?: 0)
        result = 31 * result + (bmiCategory?.hashCode() ?: 0)
        return result
    }


}
package ru.xpendence.slimer.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:11
 * e-mail: 2262288@gmail.com
 */
open class UserDto : AbstractDto() {

    @NotNull(groups = [])
    open var height: Int? = null
    open var weight: Double? = null

    @Null
    open var age: Int? = null

    open var gender: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    open var birthDate: LocalDate? = null

    @Null
    open val contacts: MutableList<ContactDto> = ArrayList()

    @Null
    open var dailyCaloriesIndex: Int? = null

    open var physicalActivityIndex: Double? = null

    @Null
    open var bodyMassIndex: Double? = null

    open var bmiCategory: String? = null

    open var programs: MutableList<ProgramDto> = ArrayList()

    open var meals: MutableList<MealDto> = ArrayList()

    open var workouts: MutableList<WorkoutDto> = ArrayList()
}
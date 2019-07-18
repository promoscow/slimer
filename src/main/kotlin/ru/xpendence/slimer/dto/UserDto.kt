package ru.xpendence.slimer.dto

import com.fasterxml.jackson.annotation.JsonFormat
import ru.xpendence.slimer.dto.validation.Validate
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:11
 * e-mail: 2262288@gmail.com
 */
open class UserDto : AbstractDto() {

    @NotNull(groups = [Validate.Create::class])
    @Null(groups = [Validate.Update::class])
    open var login: String? = null

    @NotNull(groups = [Validate.Create::class])
    open var password: String? = null

    @NotNull(groups = [Validate.Create::class])
    open var firstName: String? = null

    @NotNull(groups = [Validate.Create::class])
    open var lastName: String? = null

    @NotNull(groups = [Validate.Create::class])
    open var height: Int? = null

    @NotNull(groups = [Validate.Create::class])
    open var weight: Double? = null

    @Null
    open var age: Int? = null

    @NotNull(groups = [Validate.Create::class])
    open var gender: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    open var birthDate: LocalDate? = null

    @NotNull(groups = [Validate.Create::class])
    @Email
    open var registrationEmail: String? = null

    @Null
    open val contacts: MutableList<ContactDto> = ArrayList()

    @Null
    open var dailyCaloriesIndex: Int? = null

    @NotNull(groups = [Validate.Create::class])
    open var physicalActivityIndex: Double? = null

    @Null
    open var bodyMassIndex: Double? = null

    @Null
    open var bmiCategory: String? = null

    @Null
    open var programs: MutableList<ProgramDto> = ArrayList()

    @Null
    open var meals: MutableList<MealDto> = ArrayList()

    @Null
    open var workouts: MutableList<WorkoutDto> = ArrayList()

    @Null
    open var roles: MutableList<String> = ArrayList()
}
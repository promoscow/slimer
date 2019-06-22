package ru.xpendence.slimer.dto

import ru.xpendence.slimer.util.calculateDci
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
    open var gender: String? = null
    open var birthDate: LocalDate? = null

    @Null
    open val contacts: List<ContactDto> = ArrayList()

    @Null
    open var dailyCaloriesIndex: Int = calculateDci()

    open var physicalActivityIndex: Double? = null
}
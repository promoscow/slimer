package ru.xpendence.slimer.util

import ru.xpendence.slimer.base.Gender
import ru.xpendence.slimer.dto.UserDto
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 22.06.19
 * Time: 20:06
 * e-mail: v.chernyshov@pflb.ru
 */
fun UserDto.calculateDci(): Int =
        if (gender == Gender.MALE.name) {
            (5 + (10 * weight!!) + (6.25 * height!!) - (5 * calculateAge())).toInt()
        } else {
            ((10 * weight!!) + (6.25 * height!!) - (5 * calculateAge()) - 161).toInt()
        }

fun UserDto.calculateAge(): Int = (
        LocalDate.now().year - birthDate!!.year
                + if (LocalDate.now().dayOfYear > birthDate!!.dayOfYear) 1 else 0
        )
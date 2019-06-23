package ru.xpendence.slimer.util

import ru.xpendence.slimer.base.BmiCategory
import ru.xpendence.slimer.base.Gender
import ru.xpendence.slimer.dto.UserDto
import java.time.LocalDate
import kotlin.math.pow

/**
 * Author: Vyacheslav Chernyshov
 * Date: 22.06.19
 * Time: 20:06
 * e-mail: v.chernyshov@pflb.ru
 */
fun UserDto.calculateDci(): Int =
        if (gender == Gender.MALE.name) {
            (5 + (10 * weight!!) + (6.25 * height!!) - (5 * age!!)).toInt()
        } else {
            ((10 * weight!!) + (6.25 * height!!) - (5 * age!!) - 161).toInt()
        }

fun UserDto.calculateAge(): Int =
        (LocalDate.now().year - birthDate!!.year).plus(if (LocalDate.now().dayOfYear >= birthDate!!.dayOfYear) 0 else -1)

fun UserDto.calculateBmi(): Double = weight!!.div((height!!.toDouble() / 100).pow(2)).round(1)

fun UserDto.defineBmiCategory(): String =
        BmiCategory.values().first { bodyMassIndex!! > it.min && bodyMassIndex!! <= it.max }.name
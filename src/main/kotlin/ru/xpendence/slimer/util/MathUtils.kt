package ru.xpendence.slimer.util

import kotlin.math.round

/**
 * Author: Vyacheslav Chernyshov
 * Date: 23.06.19
 * Time: 13:57
 * e-mail: v.chernyshov@pflb.ru
 */

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this.times(multiplier)).div(multiplier)
}
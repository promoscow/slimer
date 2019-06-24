package ru.xpendence.slimer.base

/**
 * Author: Vyacheslav Chernyshov
 * Date: 23.06.19
 * Time: 21:31
 * e-mail: v.chernyshov@pflb.ru
 */
enum class ProgramType(val percentage: Double) {
    NONE(1.0),
    COMFORT(0.85),
    HARD(0.7),
    BRUTAL(0.55)
}
package ru.xpendence.slimer.base

/**
 * Author: Vyacheslav Chernyshov
 * Date: 23.06.19
 * Time: 16:18
 * e-mail: v.chernyshov@pflb.ru
 */
enum class BMI(val min: Double, val max: Double, val desctiption: String) {
    VERY_LOW(0.0, 6.0, "Выраженный дефицит массы тела"),
    LOW(16.1, 18.5, "Дефицит массы тела"),
    NORMAL(18.6, 25.0, "Норма"),
    EXCESS(25.1, 30.0, "Избыточная масса тела (предожирение)"),
    OBESTY_I(30.1, 35.0, "Ожирение первой степени"),
    OBESTY_II(35.1, 40.0, "Ожирение второй степени"),
    OBESTY_III(40.1, Double.MAX_VALUE, "Ожирение третьей степени")
}
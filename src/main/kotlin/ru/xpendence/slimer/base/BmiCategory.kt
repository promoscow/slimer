package ru.xpendence.slimer.base

/**
 * Author: Vyacheslav Chernyshov
 * Date: 23.06.19
 * Time: 16:18
 * e-mail: 2262288@gmail.com
 */
enum class BmiCategory(val min: Double, val max: Double, val desctiption: String) {
    VERY_LOW(0.0, 6.0, "Выраженный дефицит массы тела"),
    LOW(16.0, 18.5, "Дефицит массы тела"),
    NORMAL(18.6, 25.0, "Норма"),
    EXCESS(25.0, 30.0, "Избыточная масса тела (предожирение)"),
    OBESTY_I(30.0, 35.0, "Ожирение первой степени"),
    OBESTY_II(35.0, 40.0, "Ожирение второй степени"),
    OBESTY_III(40.0, Double.MAX_VALUE, "Ожирение третьей степени")
}
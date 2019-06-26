package ru.xpendence.slimer.base

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.06.19
 * Time: 20:19
 * e-mail: 2262288@gmail.com
 */
enum class StatusCode(val code: Int, val description: String) {
    OK(0, ""),
    BAD_REQUEST(1, "Ошибка при составлении запроса"),
    DATABASE_ERROR(2, "Ошибка при работе с базой")
}
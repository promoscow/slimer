package ru.xpendence.slimer.exceptions

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-09
 * Time: 18:38
 * e-mail: 2262288@gmail.com
 */
class DataAccessException(override val message: String) : RuntimeException(message)
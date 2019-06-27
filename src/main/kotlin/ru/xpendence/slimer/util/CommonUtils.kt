package ru.xpendence.slimer.util

import java.util.*
import java.util.function.Consumer

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.06.19
 * Time: 16:28
 * e-mail: v.chernyshov@pflb.ru
 */
fun <T> whenNotNull(o: T, c: Consumer<T>) {
    if (Objects.nonNull(o)) {
        if (o is String && (o as String).isEmpty()) {
        } else {
            c.accept(o)
        }
    }
}
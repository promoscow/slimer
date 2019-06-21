package ru.xpendence.slimer.annotations

/**
 * Author: Vyacheslav Chernyshov
 * Date: 21.06.19
 * Time: 13:40
 * e-mail: v.chernyshov@pflb.ru
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@MustBeDocumented
annotation class ServiceImpl
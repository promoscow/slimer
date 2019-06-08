package ru.xpendence.slimer.mapper

import kotlin.reflect.KClass

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:28
 * e-mail: 2262288@gmail.com
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@MustBeDocumented
annotation class Mapper(val entity: KClass<*>, val dto: KClass<*>)
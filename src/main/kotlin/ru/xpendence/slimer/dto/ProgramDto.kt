package ru.xpendence.slimer.dto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 9:28
 * e-mail: v.chernyshov@pflb.ru
 */
open class ProgramDto : AbstractDto() {

    open var user: Long? = null
    open var finished: Boolean? = null
    open var startWeight: Double? = null
    open var goalWeight: Double? = null
    open var programType: String? = null
}
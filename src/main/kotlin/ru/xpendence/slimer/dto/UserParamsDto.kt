package ru.xpendence.slimer.dto

import java.time.LocalDateTime

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:18
 * e-mail: 2262288@gmail.com
 */
open class UserParamsDto : AbstractDto() {

    open var user: Long? = null
    open var height: Int? = null
    open var weight: Double? = null
    open var gender: String? = null
    open var birthDate: LocalDateTime? = null
}
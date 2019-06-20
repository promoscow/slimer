package ru.xpendence.slimer.dto

import java.time.LocalDateTime

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:11
 * e-mail: 2262288@gmail.com
 */
open class UserDto : AbstractDto() {

    open var height: Int? = null
    open var weight: Double? = null
    open var gender: String? = null
    open var birthDate: LocalDateTime? = null
    open val contacts: List<ContactDto> = ArrayList()
}
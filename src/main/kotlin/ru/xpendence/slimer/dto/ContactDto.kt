package ru.xpendence.slimer.dto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-20
 * Time: 20:13
 * e-mail: 2262288@gmail.com
 */
open class ContactDto: AbstractDto() {

    open var phone: String? = null
    open var email: String? = null
    open var user: Long? = null
}
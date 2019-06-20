package ru.xpendence.slimer.dto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-20
 * Time: 20:13
 * e-mail: 2262288@gmail.com
 */
open class ContactDto(

        open var phone: String?,
        open var email: String?,
        open var user: Long?

) : AbstractDto()
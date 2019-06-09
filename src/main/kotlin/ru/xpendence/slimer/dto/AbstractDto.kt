package ru.xpendence.slimer.dto

import java.time.LocalDateTime

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:14
 * e-mail: 2262288@gmail.com
 */
open class AbstractDto(

        var id: Long? = null,
        var created: LocalDateTime? = null,
        var updated: LocalDateTime? = null
)
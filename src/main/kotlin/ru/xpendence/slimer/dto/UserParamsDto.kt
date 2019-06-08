package ru.xpendence.slimer.dto

import java.time.LocalDateTime

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:18
 * e-mail: 2262288@gmail.com
 */
data class UserParamsDto(

        var user: Long? = null,
        var height: Int? = null,
        var weight: Double? = null,
        var gender: String? = null,
        var birthDate: LocalDateTime? = null

) : AbstractDto()
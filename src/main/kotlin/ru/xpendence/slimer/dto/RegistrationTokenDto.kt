package ru.xpendence.slimer.dto

import java.time.LocalDateTime

/**
 * Author: Vyacheslav Chernyshov
 * Date: 19.07.19
 * Time: 21:37
 * e-mail: v.chernyshov@pflb.ru
 */
open class RegistrationTokenDto : AbstractDto() {

    open var user: Long? = null
    open var token: String? = null
    open var expirationTime: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as RegistrationTokenDto

        if (user != other.user) return false
        if (token != other.token) return false
        if (expirationTime != other.expirationTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (token?.hashCode() ?: 0)
        result = 31 * result + (expirationTime?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "RegistrationTokenDto(user=$user, token=$token, expirationTime=$expirationTime)"
    }
}
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ContactDto

        if (phone != other.phone) return false
        if (email != other.email) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (phone?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (user?.hashCode() ?: 0)
        return result
    }


}
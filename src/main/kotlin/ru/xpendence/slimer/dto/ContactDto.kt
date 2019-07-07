package ru.xpendence.slimer.dto

import ru.xpendence.slimer.dto.validation.Validate
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null
import javax.validation.constraints.Pattern

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-20
 * Time: 20:13
 * e-mail: 2262288@gmail.com
 */
open class ContactDto: AbstractDto() {

    @Pattern(
            regexp = "^[9]\\d{9}$",
            groups = [Validate.Create::class, Validate.Update::class],
            message = "phone field not matches as valid phone number")
    @NotNull(groups = [Validate.Create::class], message = "phone number can not be null")
    open var phone: String? = null

    @Pattern(
            regexp = "^([\\w-_.]*[\\w]){3,15}@[\\w]+\\.[\\w]{2,5}\$",
            groups = [Validate.Create::class, Validate.Update::class],
            message = "email field not matches as valid email"
    )
    @NotNull(groups = [Validate.Create::class], message = "email can not be null")
    open var email: String? = null

    @NotNull
    open var user: Long? = null

    @Null(groups = [Validate.Create::class])
    open var default: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ContactDto

        if (phone != other.phone) return false
        if (email != other.email) return false
        if (user != other.user) return false
        if (default != other.default) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (phone?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + default.hashCode()
        return result
    }

    override fun toString(): String {
        return "ContactDto(phone=$phone, email=$email, user=$user, default=$default)"
    }
}
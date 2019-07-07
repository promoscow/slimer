package ru.xpendence.slimer.dto

import ru.xpendence.slimer.dto.validation.Validate
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null
import javax.validation.constraints.Pattern

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-20
 * Time: 20:13
 * e-mail: 2262288@gmail.com
 */
open class ContactDto : AbstractDto() {

    @Pattern(
            regexp = "^[9]\\d{9}$",
            groups = [Validate.Create::class, Validate.Update::class],
            message = "field not matches as valid"
    )
    @NotNull(groups = [Validate.Create::class], message = "field can not be null")
    open var phone: String? = null

    @Email(
            groups = [Validate.Create::class, Validate.Update::class],
            message = "field not matches as valid"
    )
    @NotNull(groups = [Validate.Create::class], message = "field can not be null")
    open var email: String? = null

    @NotNull(message = "field can not be null")
    open var user: Long? = null

    @Null(groups = [Validate.Create::class], message = "field must be null")
    open var default: Boolean? = null

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
        result = 31 * result + (default?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "ContactDto(phone=$phone, email=$email, user=$user, default=$default)"
    }
}
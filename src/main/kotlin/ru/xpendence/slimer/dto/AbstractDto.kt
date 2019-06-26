package ru.xpendence.slimer.dto

import com.fasterxml.jackson.annotation.JsonInclude
import ru.xpendence.slimer.base.StatusCode
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:14
 * e-mail:
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
open class AbstractDto() : Serializable {

    open var id: Long? = null
    open var created: LocalDateTime? = null
    open var updated: LocalDateTime? = null
    var statusCode: Int = StatusCode.OK.code
    var errorMessage: String? = null

    constructor(code: Int, message: String) : this() {
        this.statusCode = code
        this.errorMessage = message
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractDto

        if (id != other.id) return false
        if (created != other.created) return false
        if (updated != other.updated) return false
        if (statusCode != other.statusCode) return false
        if (errorMessage != other.errorMessage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (created?.hashCode() ?: 0)
        result = 31 * result + (updated?.hashCode() ?: 0)
        result = 31 * result + statusCode
        result = 31 * result + (errorMessage?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "AbstractDto(id=$id, created=$created, updated=$updated, statusCode=$statusCode, " +
                "errorMessage=$errorMessage)"
    }

}
package ru.xpendence.slimer.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:14
 * e-mail: 2262288@gmail.com
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
open class AbstractDto : Serializable {

    open var id: Long? = null
    open var created: LocalDateTime? = null
    open var updated: LocalDateTime? = null
}
package ru.xpendence.slimer.dto.message

import java.io.Serializable

/**
 * Author: Vyacheslav Chernyshov
 * Date: 15.07.19
 * Time: 15:36
 * e-mail: v.chernyshov@pflb.ru
 */
open class MessageDto : Serializable {

    val to: String? = null
    val from: String? = null
    val subject: String? = null
    val text: String? = null
}
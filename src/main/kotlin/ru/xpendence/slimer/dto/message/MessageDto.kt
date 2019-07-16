package ru.xpendence.slimer.dto.message

import java.io.Serializable

/**
 * Author: Vyacheslav Chernyshov
 * Date: 15.07.19
 * Time: 15:36
 * e-mail: v.chernyshov@pflb.ru
 */
open class MessageDto : Serializable {

    var to: String? = null
    var from: String? = null
    var subject: String? = null
    var text: String? = null
}
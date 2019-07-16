package ru.xpendence.slimer.dto.message

/**
 * Author: Vyacheslav Chernyshov
 * Date: 15.07.19
 * Time: 15:44
 * e-mail: v.chernyshov@pflb.ru
 */
class EmailMessageDto : MessageDto()

fun EmailMessageDto.of(to: String, from: String, subject: String, text: String): EmailMessageDto {
    this.to = to
    this.from = from
    this.subject = subject
    this.text = text
    return this
}
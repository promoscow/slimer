package ru.xpendence.slimer.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.xpendence.slimer.dto.AbstractDto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.06.19
 * Time: 20:32
 * e-mail: 2262288@gmail.com
 */
@ControllerAdvice
class SlimerExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [CustomException::class])
    fun handle(exception: CustomException): ResponseEntity<AbstractDto>
            = ResponseEntity.ok(AbstractDto(exception.code, exception.message))
}
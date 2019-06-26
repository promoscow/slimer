package ru.xpendence.slimer.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.xpendence.slimer.base.StatusCode
import ru.xpendence.slimer.dto.AbstractDto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.06.19
 * Time: 20:32
 * e-mail: v.chernyshov@pflb.ru
 */
@ControllerAdvice
class SlimerExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [RuntimeException::class])
    fun handle(exception: RuntimeException): ResponseEntity<AbstractDto>
            = ResponseEntity.ok(AbstractDto(StatusCode.DATABASE_ERROR.code, exception.message!!))
}
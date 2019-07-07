package ru.xpendence.slimer.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.xpendence.slimer.base.StatusCode
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
    fun handle(exception: CustomException): ResponseEntity<AbstractDto> = ResponseEntity.ok(AbstractDto(exception.code, exception.message))

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle400(e: Exception): ResponseEntity<AbstractDto> =
            ResponseEntity(
                    AbstractDto(
                            StatusCode.BAD_REQUEST.name,
                            "not matching fields"
                    ),
                    HttpStatus.OK
            )

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> =
            ResponseEntity(
                    AbstractDto(
                            StatusCode.BAD_REQUEST.name,
                            ex.bindingResult.fieldErrors.map { e -> "${e.field}: ${e.defaultMessage}" }.toString()
                    ),
                    HttpStatus.BAD_REQUEST
            )
}
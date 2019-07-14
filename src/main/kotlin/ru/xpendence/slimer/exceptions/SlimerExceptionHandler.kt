package ru.xpendence.slimer.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
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

    @ExceptionHandler(value = [DataAccessException::class])
    fun handle(exception: DataAccessException): ResponseEntity<AbstractDto> =
            ResponseEntity.ok(AbstractDto(exception.code, exception.message))

    @ExceptionHandler(value = [NoMatchingValueException::class])
    fun handle(exception: NoMatchingValueException): ResponseEntity<AbstractDto> =
            ResponseEntity.ok(AbstractDto(exception.code, exception.message))

    @ExceptionHandler(value = [ApiException::class])
    fun handle(exception: ApiException): ResponseEntity<AbstractDto> =
            ResponseEntity.ok(AbstractDto(exception.code, exception.message))

    @ExceptionHandler(value = [JwtAuthenticationException::class])
    fun handle(exception: JwtAuthenticationException): ResponseEntity<AbstractDto> =
            ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(AbstractDto(StatusCode.UNAUTHORIZED.name, exception.message))

    @ExceptionHandler(value = [AccessDeniedException::class])
    fun handle(exception: AccessDeniedException): ResponseEntity<AbstractDto> =
            ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(AbstractDto(StatusCode.ACCESS_DENIED.name, exception.message!!))

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
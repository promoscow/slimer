package ru.xpendence.slimer.exceptions

import org.springframework.security.core.AuthenticationException

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-09
 * Time: 18:38
 * e-mail: 2262288@gmail.com
 */
open class CustomException(open val code: String, override val message: String) : RuntimeException(message)

class DataAccessException(override val code: String, override val message: String) : CustomException(code, message)

class NoMatchingValueException(override val code: String, override val message: String) : CustomException(code, message)

class ApiException(override val code: String, override val message: String) : CustomException(code, message)

class JwtAuthenticationException(override val message: String) : AuthenticationException(message)
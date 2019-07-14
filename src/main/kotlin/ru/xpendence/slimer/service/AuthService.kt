package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import ru.xpendence.slimer.config.security.jwt.JwtTokenProvider
import ru.xpendence.slimer.dto.AuthRequestDto
import ru.xpendence.slimer.dto.AuthResponseDto
import ru.xpendence.slimer.dto.UserDto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 18:17
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
class AuthService @Autowired constructor(
        private val authManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider,
        private val userService: UserServiceImpl
) {

    fun login(request: AuthRequestDto): AuthResponseDto {
        authManager.authenticate(UsernamePasswordAuthenticationToken(request.login, request.password))
        val userDto: UserDto = userService.findByLogin(request.login)
        return AuthResponseDto(request.login, jwtTokenProvider.createToken(request.login, userDto.roles))
    }
}
package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.xpendence.slimer.dto.AuthRequestDto
import ru.xpendence.slimer.dto.AuthResponseDto
import ru.xpendence.slimer.service.AuthService

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 18:16
 * e-mail: v.chernyshov@pflb.ru
 */
@RestController
@RequestMapping(value = ["/login"])
class AuthController @Autowired constructor(private val service: AuthService) {

    @PostMapping
    fun login(@RequestBody request: AuthRequestDto): ResponseEntity<AuthResponseDto> =
            ResponseEntity.ok(service.login(request))
}
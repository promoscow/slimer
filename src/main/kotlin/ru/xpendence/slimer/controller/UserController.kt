package ru.xpendence.slimer.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.xpendence.slimer.dto.UserDto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-12
 * Time: 09:34
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/user")
class UserController {


    @PostMapping
    fun create(@RequestBody dto: UserDto): ResponseEntity<UserDto> = ResponseEntity.ok(UserDto())
}
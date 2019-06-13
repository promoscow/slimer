package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.service.UserService

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-12
 * Time: 09:34
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(private val service: UserService) {

    @PostMapping
    fun create(@RequestBody dto: UserDto?): ResponseEntity<UserDto?> = ResponseEntity.ok(service.save(dto)!!)

    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<UserDto?> = ResponseEntity.ok(service.get(id)!!)
}
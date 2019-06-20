package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.ContactDto
import ru.xpendence.slimer.service.ContactService

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-20
 * Time: 20:43
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/contact")
class ContactController @Autowired constructor(private val service: ContactService) {

    @PostMapping
    fun create(@RequestBody dto: ContactDto?): ResponseEntity<ContactDto?> = ResponseEntity.ok(service.save(dto)!!)

    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<ContactDto?> = ResponseEntity.ok(service.get(id)!!)
}
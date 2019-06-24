package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.ProgramDto
import ru.xpendence.slimer.service.ProgramService

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 11:27
 * e-mail: v.chernyshov@pflb.ru
 */
@RestController
@RequestMapping("/program")
class ProgramController @Autowired constructor(val service: ProgramService) {

    @PostMapping
    fun create(@RequestBody dto: ProgramDto?): ResponseEntity<ProgramDto?> = ResponseEntity.ok(service.save(dto)!!)

    @PutMapping
    fun update(@RequestBody dto: ProgramDto?): ResponseEntity<ProgramDto?> = ResponseEntity.ok(service.update(dto)!!)

    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<ProgramDto?> = ResponseEntity.ok(service.get(id)!!)

    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<ProgramDto>> = ResponseEntity.ok(service.getAll(pageable))

    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
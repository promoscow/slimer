package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.PortionDto
import ru.xpendence.slimer.service.PortionServiceImpl

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:21
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/portion")
class PortionController @Autowired constructor(val service: PortionServiceImpl) {

    @PostMapping
    fun create(@RequestBody dto: PortionDto?): ResponseEntity<PortionDto?> = ResponseEntity.ok(service.save(dto)!!)

    @PutMapping
    fun update(@RequestBody dto: PortionDto?): ResponseEntity<PortionDto?> = ResponseEntity.ok(service.update(dto)!!)

    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<PortionDto?> = ResponseEntity.ok(service.get(id)!!)

    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<PortionDto>> = ResponseEntity.ok(service.getAll(pageable))

    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.WorkoutDto
import ru.xpendence.slimer.service.WorkoutServiceImpl

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 23:23
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping(name = "/workout")
class WorkoutController @Autowired constructor(private val service: WorkoutServiceImpl) {

    @PostMapping
    fun create(@RequestBody dto: WorkoutDto?): ResponseEntity<WorkoutDto?> = ResponseEntity.ok(service.save(dto)!!)

    @PutMapping
    fun update(@RequestBody dto: WorkoutDto?): ResponseEntity<WorkoutDto?> = ResponseEntity.ok(service.update(dto)!!)

    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<WorkoutDto?> = ResponseEntity.ok(service.get(id)!!)

    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<WorkoutDto>> = ResponseEntity.ok(service.getAll(pageable))

    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
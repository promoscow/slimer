package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.CommonDayStatsDto
import ru.xpendence.slimer.service.CommonDayStatsServiceImpl

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 8:40
 * e-mail: v.chernyshov@pflb.ru
 */
@RestController
@RequestMapping(name = "/stats")
class CommonDayStatsController @Autowired constructor(private val service: CommonDayStatsServiceImpl) {

    @PostMapping
    fun create(@RequestBody dto: CommonDayStatsDto?): ResponseEntity<CommonDayStatsDto?> =
            ResponseEntity.ok(service.save(dto)!!)

    @PutMapping
    fun update(@RequestBody dto: CommonDayStatsDto?): ResponseEntity<CommonDayStatsDto?> =
            ResponseEntity.ok(service.update(dto)!!)

    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<CommonDayStatsDto?> =
            ResponseEntity.ok(service.get(id)!!)

    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<CommonDayStatsDto>> =
            ResponseEntity.ok(service.getAll(pageable))

    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
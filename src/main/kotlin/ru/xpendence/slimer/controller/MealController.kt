package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.DayNutrientsDto
import ru.xpendence.slimer.dto.MealDto
import ru.xpendence.slimer.service.MealServiceImpl
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:50
 * e-mail: v.chernyshov@pflb.ru
 */
@RestController
@RequestMapping("/meal")
class MealController @Autowired constructor(val service: MealServiceImpl) {

    @PostMapping
    fun create(@RequestBody dto: MealDto?): ResponseEntity<MealDto?> = ResponseEntity.ok(service.save(dto)!!)

    @PutMapping
    fun update(@RequestBody dto: MealDto?): ResponseEntity<MealDto?> = ResponseEntity.ok(service.update(dto)!!)

    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<MealDto?> = ResponseEntity.ok(service.get(id)!!)

    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<MealDto>> = ResponseEntity.ok(service.getAll(pageable))

    @GetMapping("/date")
    fun getAllByDateForUser(@RequestParam("id") userId: Long,
                            @RequestParam("date") date: LocalDate?): ResponseEntity<List<MealDto>> =
            ResponseEntity.ok(service.getAllByDateForUser(userId, date ?: LocalDate.now()))

    @GetMapping("/stats")
    fun getStatsByDateForUser(@RequestParam("id") userId: Long,
                              @RequestParam("/date") date: LocalDate): ResponseEntity<DayNutrientsDto> =
            ResponseEntity.ok(service.getStatsByDateForUser(userId, date))

    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
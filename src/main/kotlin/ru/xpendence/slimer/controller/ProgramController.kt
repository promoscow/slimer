package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.ProgramDto
import ru.xpendence.slimer.dto.TodayCaloriesDto
import ru.xpendence.slimer.dto.validation.Validate
import ru.xpendence.slimer.service.ProgramServiceImpl

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 11:27
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/program")
class ProgramController @Autowired constructor(val service: ProgramServiceImpl) {

    @PostMapping
    fun create(@Validated(value = [Validate.Create::class]) @RequestBody dto: ProgramDto?): ResponseEntity<ProgramDto?> =
            ResponseEntity.ok(service.save(dto)!!)

    @PutMapping
    fun update(@Validated(value = [Validate.Update::class]) @RequestBody dto: ProgramDto?): ResponseEntity<ProgramDto?> =
            ResponseEntity.ok(service.update(dto)!!)

    @GetMapping
    fun get(@RequestParam(value = "id") id: Long): ResponseEntity<ProgramDto?> = ResponseEntity.ok(service.get(id)!!)

    @GetMapping("/calculate")
    fun calculate(@RequestParam(value = "id") id: Long,
                  @RequestParam(value = "goal") goalWeight: Double): ResponseEntity<List<ProgramDto>> =
            ResponseEntity.ok(service.calculate(id, goalWeight))

    @GetMapping("/today")
    fun getTodayCalories(@RequestParam(value = "id") id: Long): ResponseEntity<TodayCaloriesDto> =
            ResponseEntity.ok(service.getTodayCalories(id))

    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<ProgramDto>> = ResponseEntity.ok(service.getAll(pageable))

    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
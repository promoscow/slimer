package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.DayNutrientsDto
import ru.xpendence.slimer.dto.MealDto
import ru.xpendence.slimer.service.MealServiceImpl
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:50
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/meal")
class MealController @Autowired constructor(val service: MealServiceImpl) {

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER)"
    )
    @PostMapping
    fun create(@RequestBody dto: MealDto?): ResponseEntity<MealDto?> = ResponseEntity.ok(service.save(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @PutMapping
    fun update(@RequestBody dto: MealDto?): ResponseEntity<MealDto?> = ResponseEntity.ok(service.update(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<MealDto?> = ResponseEntity.ok(service.get(id)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<MealDto>> = ResponseEntity.ok(service.getAll(pageable))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping("/date")
    fun getAllByDateForUser(@RequestParam("id") userId: Long,
                            @RequestParam("date") date: LocalDate?): ResponseEntity<List<MealDto>> =
            ResponseEntity.ok(service.getAllByDateForUser(userId, date ?: LocalDate.now()))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping("/stats")
    fun getStatsByDateForUser(@RequestParam("id") userId: Long,
                              @RequestParam("date") date: LocalDate?): ResponseEntity<DayNutrientsDto> =
            ResponseEntity.ok(service.getStatsByDateForUser(userId, date ?: LocalDate.now()))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
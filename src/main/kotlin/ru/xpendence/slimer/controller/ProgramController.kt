package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.ProgramDto
import ru.xpendence.slimer.dto.TodayCaloriesDto
import ru.xpendence.slimer.dto.validation.Validate
import ru.xpendence.slimer.service.ProgramServiceImpl
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 11:27
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/program")
class ProgramController @Autowired constructor(val service: ProgramServiceImpl) {

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER)"
    )
    @PostMapping
    fun create(@Validated(value = [Validate.Create::class]) @RequestBody dto: ProgramDto?): ResponseEntity<ProgramDto?> =
            ResponseEntity.ok(service.save(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @PutMapping
    fun update(@Validated(value = [Validate.Update::class]) @RequestBody dto: ProgramDto?): ResponseEntity<ProgramDto?> =
            ResponseEntity.ok(service.update(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping
    fun get(@RequestParam(value = "id") id: Long): ResponseEntity<ProgramDto?> = ResponseEntity.ok(service.get(id)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER)"
    )
    @GetMapping("/calculate")
    fun calculate(@RequestParam(value = "id") id: Long,
                  @RequestParam(value = "goal") goalWeight: Double): ResponseEntity<List<ProgramDto>> =
            ResponseEntity.ok(service.calculate(id, goalWeight))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping("/actual")
    fun getActualByUser(@RequestParam(value = "userId") userId: Long): ResponseEntity<ProgramDto> =
            ResponseEntity.ok(service.getActualByUser(userId))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping("/date")
    fun getTodayCalories(@RequestParam(value = "id") id: Long,
                         @RequestParam(value = "date") date: LocalDate): ResponseEntity<TodayCaloriesDto> =
            ResponseEntity.ok(service.getCaloriesByDate(id, date ?: LocalDate.now()))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<ProgramDto>> = ResponseEntity.ok(service.getAll(pageable))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    //todo переделать на Page
    @GetMapping("/user")
    fun getAllByUser(@RequestParam(value = "id") userId: Long): ResponseEntity<List<ProgramDto>> =
            ResponseEntity.ok(service.getAllByUser(userId))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
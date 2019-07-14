package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.CommonDayStatsDto
import ru.xpendence.slimer.service.CommonDayStatsServiceImpl

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 8:40
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/stats")
class CommonDayStatsController @Autowired constructor(private val service: CommonDayStatsServiceImpl) {

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER)"
    )
    @PostMapping
    fun calculateAndSave(@RequestParam(name = "id") userId: Long): ResponseEntity<CommonDayStatsDto> =
            ResponseEntity.ok(service.calculateAndSave(userId))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER)"
    )
    @PutMapping
    fun update(@RequestBody dto: CommonDayStatsDto?): ResponseEntity<CommonDayStatsDto?> =
            ResponseEntity.ok(service.update(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER)"
    )
    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<CommonDayStatsDto?> =
            ResponseEntity.ok(service.get(id)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<CommonDayStatsDto>> =
            ResponseEntity.ok(service.getAll(pageable))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN.name())"
    )
    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER)"
    )
    @PostMapping
    fun create(@RequestBody dto: PortionDto?): ResponseEntity<PortionDto?> = ResponseEntity.ok(service.save(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @PutMapping
    fun update(@RequestBody dto: PortionDto?): ResponseEntity<PortionDto?> = ResponseEntity.ok(service.update(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<PortionDto?> = ResponseEntity.ok(service.get(id)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<PortionDto>> = ResponseEntity.ok(service.getAll(pageable))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
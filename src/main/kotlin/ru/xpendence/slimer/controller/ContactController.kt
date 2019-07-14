package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.ContactDto
import ru.xpendence.slimer.dto.validation.Validate
import ru.xpendence.slimer.service.ContactServiceImpl

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-20
 * Time: 20:43
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/contact")
class ContactController @Autowired constructor(private val service: ContactServiceImpl) {

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER)"
    )
    @PostMapping
    fun create(@Validated(value = [Validate.Create::class]) @RequestBody dto: ContactDto?): ResponseEntity<ContactDto?> =
            ResponseEntity.ok(service.save(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @PutMapping
    fun update(@Validated(value = [Validate.Update::class]) @RequestBody dto: ContactDto?): ResponseEntity<ContactDto?> =
            ResponseEntity.ok(service.update(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<ContactDto?> = ResponseEntity.ok(service.get(id)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<ContactDto>> = ResponseEntity.ok(service.getAll(pageable))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
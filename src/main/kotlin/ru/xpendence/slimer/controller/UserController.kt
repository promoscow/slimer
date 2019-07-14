package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.dto.validation.Validate
import ru.xpendence.slimer.service.UserServiceImpl

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-12
 * Time: 09:34
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(private val service: UserServiceImpl) {

    @PostMapping
    fun create(@Validated(value = [Validate.Create::class]) @RequestBody dto: UserDto?): ResponseEntity<UserDto?> =
            ResponseEntity.ok(service.save(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @PutMapping
    fun update(@Validated(value = [Validate.Update::class]) @RequestBody dto: UserDto?): ResponseEntity<UserDto?> =
            ResponseEntity.ok(service.update(dto)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).USER," +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN)"
    )
    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<UserDto?> =
            ResponseEntity.ok(service.get(id)!!)

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN.name())"
    )
    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<UserDto>> = ResponseEntity.ok(service.getAll(pageable))

    @PreAuthorize("hasAnyAuthority(" +
            "T(ru.xpendence.slimer.base.RoleType).ADMIN.name())"
    )
    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
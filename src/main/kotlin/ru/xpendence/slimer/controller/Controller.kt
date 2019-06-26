package ru.xpendence.slimer.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import ru.xpendence.slimer.dto.AbstractDto
import ru.xpendence.slimer.entity.AbstractEntity
import ru.xpendence.slimer.mapper.AbstractMapper
import ru.xpendence.slimer.repository.CommonRepository
import ru.xpendence.slimer.service.AbstractService

/**
 * Author: Vyacheslav Chernyshov
 * Date: 21.06.19
 * Time: 14:33
 * e-mail: 2262288@gmail.com
 */
//@RestController
//@RequestMapping("/contact")
//@ControllerImpl
//class ContactController : AbstractController<Contact, ContactDto, ContactMapper, ContactRepository, ContactServiceImpl>()
//
//@RestController
//@RequestMapping("/user")
//@ControllerImpl
//class UserController : AbstractController<User, UserDto, UserMapper, UserRepository, UserServiceImpl>()

open class AbstractController<
        E : AbstractEntity,
        D : AbstractDto,
        M : AbstractMapper<E, D>,
        R : CommonRepository<E>,
        S : AbstractService<E, D, M, R>
        > : CommonController<D> {

    private val service: S? = null

    override fun save(dto: D?): ResponseEntity<D> = ResponseEntity.ok(service!!.save(dto)!!)

    override fun update(dto: D): ResponseEntity<D> = ResponseEntity.ok(service!!.update(dto)!!)

    override fun get(id: Long): ResponseEntity<D> = ResponseEntity.ok(service!!.get(id)!!)

    override fun getAll(pageable: Pageable): ResponseEntity<Page<D>> = ResponseEntity.ok(service!!.getAll(pageable))

    override fun delete(id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service!!.delete(id))
}

interface CommonController<D : AbstractDto> {

    @PostMapping
    fun save(dto: D?): ResponseEntity<D>

    @PutMapping
    fun update(dto: D): ResponseEntity<D>

    @GetMapping("/all")
    fun get(id: Long): ResponseEntity<D>

    @GetMapping
    fun getAll(pageable: Pageable): ResponseEntity<Page<D>>

    @DeleteMapping
    fun delete(id: Long): ResponseEntity<Boolean>
}
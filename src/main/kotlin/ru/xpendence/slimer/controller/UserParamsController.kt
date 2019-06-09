package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.UserParamsDto
import ru.xpendence.slimer.service.UserParamsService

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-09
 * Time: 16:41
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping(value = ["/params"])
class UserParamsController @Autowired constructor(private val service: UserParamsService) {

    @PostMapping
    fun save(@RequestBody dto: UserParamsDto): ResponseEntity<UserParamsDto> = ResponseEntity.ok(service.save(dto)!!)

    @PutMapping
    fun update(@RequestBody dto: UserParamsDto): ResponseEntity<UserParamsDto> =
            ResponseEntity.ok(service.update(dto)!!)

    //todo разобраться с нуллабилити. По-моему, подход довольно зыбкий
    @GetMapping
    fun get(@RequestParam id: Long): ResponseEntity<UserParamsDto> = ResponseEntity.ok(service.get(id)!!)

    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<UserParamsDto>> = ResponseEntity.ok(service.getAll(pageable))

    @DeleteMapping
    fun delete(@RequestParam id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
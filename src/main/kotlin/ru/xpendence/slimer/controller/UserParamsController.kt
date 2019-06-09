package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
}
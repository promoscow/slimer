package ru.xpendence.slimer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.xpendence.slimer.dto.ProductDto
import ru.xpendence.slimer.service.ProductServiceImpl

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-27
 * Time: 20:38
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/product")
class ProductController @Autowired constructor(val service: ProductServiceImpl) {

    @PostMapping
    fun create(@RequestBody dto: ProductDto?): ResponseEntity<ProductDto?> = ResponseEntity.ok(service.save(dto)!!)

    @PutMapping
    fun update(@RequestBody dto: ProductDto?): ResponseEntity<ProductDto?> = ResponseEntity.ok(service.update(dto)!!)

    @GetMapping
    fun get(@RequestParam("id") id: Long): ResponseEntity<ProductDto?> = ResponseEntity.ok(service.get(id)!!)

    @GetMapping("/all")
    fun getAll(pageable: Pageable): ResponseEntity<Page<ProductDto>> = ResponseEntity.ok(service.getAll(pageable))

    @DeleteMapping
    fun delete(@RequestParam(value = "id") id: Long): ResponseEntity<Boolean> = ResponseEntity.ok(service.delete(id))
}
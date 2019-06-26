package ru.xpendence.slimer.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import ru.xpendence.slimer.dto.AbstractDto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 22.06.19
 * Time: 15:11
 * e-mail: 2262288@gmail.com
 */
interface CrudService<D : AbstractDto> {

    @Transactional
    fun save(dto: D?): D?

    @Transactional
    fun update(dto: D?): D?

    @Transactional
    fun get(id: Long): D?

    @Transactional
    fun getAll(pageable: Pageable): Page<D>

    fun delete(id: Long): Boolean
}
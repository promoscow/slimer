package ru.xpendence.slimer.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.xpendence.slimer.dto.AbstractDto

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:09
 * e-mail: 2262288@gmail.com
 */
interface CommonService<D : AbstractDto> {

    fun save(dto: D?): D?

    fun update(dto: D): D?

    fun get(id: Long): D?

    fun getAll(pageable: Pageable): Page<D>

    fun delete(id: Long): Boolean
}

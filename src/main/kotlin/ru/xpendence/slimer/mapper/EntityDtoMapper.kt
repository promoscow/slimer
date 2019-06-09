package ru.xpendence.slimer.mapper

import ru.xpendence.slimer.dto.AbstractDto
import ru.xpendence.slimer.entity.AbstractEntity

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:33
 * e-mail: 2262288@gmail.com
 */
interface EntityDtoMapper<E : AbstractEntity, D : AbstractDto> {

    fun toEntity(dto: D?): E?

    fun toEntity(dto: D?, entity: E?): E?

    fun toDto(entity: E?): D?

    fun toDto(entity: E?, dto: D?): D?
}
package ru.xpendence.slimer.mapper

import ru.xpendence.slimer.dto.AbstractDto
import ru.xpendence.slimer.entity.AbstractEntity

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:33
 * e-mail: 2262288@gmail.com
 */
interface AbstractMapper<E : AbstractEntity, D : AbstractDto> {

    fun toEntity(dto: D): E

    fun toDto(entity: E): D
}
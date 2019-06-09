package ru.xpendence.slimer.mapper

import org.modelmapper.Converter
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.slimer.dto.AbstractDto
import ru.xpendence.slimer.entity.AbstractEntity

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 21:11
 * e-mail: 2262288@gmail.com
 */
open class AbstractMapper<E : AbstractEntity, D : AbstractDto> @Autowired constructor(
        protected val mapper: ModelMapper
) : EntityDtoMapper<E, D> {

    private var entityClass: Class<E>? = null
    private var dtoClass: Class<D>? = null

    override fun toEntity(dto: D?): E? = if (dto == null) null else mapper.map(dto, entityClass)

    override fun toEntity(dto: D?, entity: E?): E? {
        if (dto == null) return entity
        if (entity == null) return toEntity(dto)
        mapper.map(dto, entity)
        return entity
    }

    override fun toDto(entity: E?): D? = if (entity == null) null else mapper.map(entity, dtoClass)

    override fun toDto(entity: E?, dto: D?): D? {
        if (entity == null) return dto
        if (dto == null) return toDto(entity)
        mapper.map(entity, dto)
        return dto
    }

    protected fun toEntityConverter(): Converter<D, E> = Converter {
        mapSpecificFields(it.source, it.destination)
        it.destination
    }

    protected fun toDtoConverter(): Converter<E, D> = Converter {
        mapSpecificFields(it.source, it.destination)
        it.destination
    }

    override fun mapSpecificFields(source: E, destination: D) {}

    override fun mapSpecificFields(source: D, destination: E) {}
}
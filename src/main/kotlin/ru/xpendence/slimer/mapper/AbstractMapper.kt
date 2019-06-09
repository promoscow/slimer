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

    override fun toEntity(dto: D?): E? = mapper.map(dto, entityClass)

    override fun toDto(entity: E?): D? = mapper.map(entity, dtoClass)

    protected fun toEntityConverter(): Converter<D, E> = Converter {
        val source: D = it.source
        val destination: E = it.destination
        mapSpecificFields(source, destination)
        it.destination
    }

    protected fun toDtoConverter(): Converter<E, D> = Converter {
        val source: E = it.source
        val destination: D = it.destination
        mapSpecificFields(source, destination)
        it.destination
    }

    protected fun mapSpecificFields(source: D, destination: E) {}

    protected fun mapSpecificFields(source: E, destination: D) {}
}
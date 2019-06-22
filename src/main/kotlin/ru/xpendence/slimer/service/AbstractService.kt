package ru.xpendence.slimer.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import ru.xpendence.slimer.dto.AbstractDto
import ru.xpendence.slimer.entity.AbstractEntity
import ru.xpendence.slimer.exceptions.DataAccessException
import ru.xpendence.slimer.mapper.AbstractMapper
import ru.xpendence.slimer.repository.CommonRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 22.06.19
 * Time: 15:12
 * e-mail: v.chernyshov@pflb.ru
 */
abstract class AbstractService<
        E : AbstractEntity,
        D : AbstractDto,
        M : AbstractMapper<E, D>,
        R : CommonRepository<E>
        >
    : CrudService<D> {

    private val mapper: M? = null
    private val repository: R? = null

    override fun save(dto: D?): D? {
        validate(dto)

        return mapper!!.toDto(repository!!.save(mapper.toEntity(dto)!!))
    }

    override fun update(dto: D?): D? {
        val userParams = repository!!.findById(dto!!.id!!)
                .orElseThrow { DataAccessException("Entity with id ${dto.id} not found.") }
        return mapper!!.toDto(repository.save(mapper.toEntity(dto, userParams)!!))
    }

    override fun get(id: Long): D? = mapper!!.toDto(repository!!.findByIdOrNull(id))

    override fun getAll(pageable: Pageable): Page<D> = repository!!.findAll(pageable).map { mapper!!.toDto(it) }

    override fun delete(id: Long): Boolean {
        repository!!.deleteById(id)
        return !repository.findById(id).isPresent
    }

    open fun validate(dto: D?) {}

    open fun calculate(dto: D?) {}
}
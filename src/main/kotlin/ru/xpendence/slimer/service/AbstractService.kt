package ru.xpendence.slimer.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import ru.xpendence.slimer.base.StatusCode
import ru.xpendence.slimer.dto.AbstractDto
import ru.xpendence.slimer.entity.AbstractEntity
import ru.xpendence.slimer.exceptions.DataAccessException
import ru.xpendence.slimer.mapper.AbstractMapper
import ru.xpendence.slimer.repository.CommonRepository
import ru.xpendence.slimer.util.logger

/**
 * Author: Vyacheslav Chernyshov
 * Date: 22.06.19
 * Time: 15:12
 * e-mail: 2262288@gmail.com
 */
abstract class AbstractService<
        E : AbstractEntity,
        D : AbstractDto,
        M : AbstractMapper<E, D>,
        R : CommonRepository<E>
        >
    : CrudService<D> {

    open val log = logger<AbstractService<*, *, *, *>>()

    protected val mapper: M? = null
    protected val repository: R? = null

    override fun save(dto: D?): D? {
        log.info("---> dto ${dto!!.hashCode()} has arrived to save: $dto")
        validate(dto)
        preCreate(dto)
        return mapper!!.toDto(repository!!.save(mapper.toEntity(dto)!!))
    }

    override fun update(dto: D?): D? {
        preUpdate(dto)
        val userParams = repository!!.findByIdOrNull(dto!!.id!!)
                ?: throw DataAccessException(StatusCode.DATABASE_ERROR.name, "Entity with id ${dto.id} not found.")
        return mapper!!.toDto(repository.save(mapper.toEntity(dto, userParams)!!))
    }

    override fun get(id: Long): D? = mapper!!.toDto(repository!!.findByIdOrNull(id)
            ?: throw DataAccessException(StatusCode.DATABASE_ERROR.name, "Entity with id $id not found."))

    override fun getAll(pageable: Pageable): Page<D> = repository!!.findAll(pageable).map { mapper!!.toDto(it) }

    override fun delete(id: Long): Boolean {
        repository!!.deleteById(id)
        return !repository.findById(id).isPresent
    }

    open fun validate(dto: D?) {}

    open fun preCreate(dto: D?) {}

    open fun preUpdate(dto: D?) {}
}

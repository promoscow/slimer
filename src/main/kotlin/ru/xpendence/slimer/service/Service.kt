package ru.xpendence.slimer.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.AbstractDto
import ru.xpendence.slimer.dto.ContactDto
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.entity.AbstractEntity
import ru.xpendence.slimer.entity.Contact
import ru.xpendence.slimer.entity.User
import ru.xpendence.slimer.exceptions.DataAccessException
import ru.xpendence.slimer.mapper.AbstractMapper
import ru.xpendence.slimer.mapper.impl.ContactMapper
import ru.xpendence.slimer.mapper.impl.UserMapper
import ru.xpendence.slimer.repository.CommonRepository
import ru.xpendence.slimer.repository.ContactRepository
import ru.xpendence.slimer.repository.UserRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:09
 * e-mail: 2262288@gmail.com
 */
@Service
@ServiceImpl
class UserServiceImpl : AbstractService<User, UserDto, UserMapper, UserRepository>()

@Service
@ServiceImpl
class ContactServiceImpl : AbstractService<Contact, ContactDto, ContactMapper, ContactRepository>()

open class AbstractService<
        E : AbstractEntity,
        D : AbstractDto,
        M : AbstractMapper<E, D>,
        R : CommonRepository<E>
        >
    : CommonService<D> {

    private val mapper: M? = null
    private val repository: R? = null

    override fun save(dto: D?): D? = mapper!!.toDto(repository!!.save(mapper.toEntity(dto)!!))

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
}

interface CommonService<D : AbstractDto> {

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
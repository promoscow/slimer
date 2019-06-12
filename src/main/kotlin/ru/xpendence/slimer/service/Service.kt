package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.xpendence.slimer.dto.AbstractDto
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.dto.UserParamsDto
import ru.xpendence.slimer.entity.User
import ru.xpendence.slimer.exceptions.DataAccessException
import ru.xpendence.slimer.mapper.impl.UserParamsMapper
import ru.xpendence.slimer.repository.UserParamsRepository
import ru.xpendence.slimer.repository.UserRepository

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

@Service
class UserParamsService @Autowired constructor(
        private val repository: UserParamsRepository,
        private val mapper: UserParamsMapper
) : CommonService<UserParamsDto> {

    override fun save(dto: UserParamsDto?): UserParamsDto? = mapper.toDto(repository.save(mapper.toEntity(dto)!!))

    override fun update(dto: UserParamsDto): UserParamsDto? {
        val userParams = repository.findById(dto.id!!)
                .orElseThrow { DataAccessException("Entity with id ${dto.id} not found.") }
        return mapper.toDto(repository.save(mapper.toEntity(dto, userParams)!!))
    }

    override fun get(id: Long): UserParamsDto? = mapper.toDto(repository.findByIdOrNull(id))

    override fun getAll(pageable: Pageable): Page<UserParamsDto> = repository.findAll(pageable).map { mapper.toDto(it) }

    override fun delete(id: Long): Boolean {
        repository.deleteById(id)
        return !repository.findById(id).isPresent
    }
}

@Service
class UserService @Autowired constructor(
        private val repository: UserRepository
) : CommonService<UserDto> {

    override fun save(dto: UserDto?): UserDto? {
        repository.save(User())
        return UserDto()
    }

    override fun update(dto: UserDto): UserDto? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: Long): UserDto? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(pageable: Pageable): Page<UserDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
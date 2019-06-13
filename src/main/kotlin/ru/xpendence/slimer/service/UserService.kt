package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.exceptions.DataAccessException
import ru.xpendence.slimer.mapper.impl.UserMapper
import ru.xpendence.slimer.repository.UserRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 6/13/19
 * Time: 4:16 PM
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
class UserService @Autowired constructor(
        private val mapper: UserMapper,
        private val repository: UserRepository
) : CommonService<UserDto> {

    override fun save(dto: UserDto?): UserDto? = mapper.toDto(repository.save(mapper.toEntity(dto)!!))

    override fun update(dto: UserDto): UserDto? {
        val userParams = repository.findById(dto.id!!)
                .orElseThrow { DataAccessException("Entity with id ${dto.id} not found.") }
        return mapper.toDto(repository.save(mapper.toEntity(dto, userParams)!!))
    }

    override fun get(id: Long): UserDto? = mapper.toDto(repository.findByIdOrNull(id))

    override fun getAll(pageable: Pageable): Page<UserDto> = repository.findAll(pageable).map { mapper.toDto(it) }

    override fun delete(id: Long): Boolean {
        repository.deleteById(id)
        return !repository.findById(id).isPresent
    }
}
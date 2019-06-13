package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.xpendence.slimer.dto.UserParamsDto
import ru.xpendence.slimer.exceptions.DataAccessException
import ru.xpendence.slimer.mapper.impl.UserParamsMapper
import ru.xpendence.slimer.repository.UserParamsRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 6/13/19
 * Time: 4:15 PM
 * e-mail: v.chernyshov@pflb.ru
 */
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
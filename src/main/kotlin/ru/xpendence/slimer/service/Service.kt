package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.xpendence.slimer.dto.AbstractDto
import ru.xpendence.slimer.dto.UserParamsDto
import ru.xpendence.slimer.mapper.impl.UserParamsMapper
import ru.xpendence.slimer.repository.UserParamsRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:09
 * e-mail: 2262288@gmail.com
 */
interface CommonService<D : AbstractDto> {

    fun save(dto: D?): D?

    fun update(dto: D): D

    fun get(id: Long): D

    fun getAll(pageable: Pageable): Page<D>

    fun delete(id: Long): Boolean
}

@Service
class UserParamsService @Autowired constructor(
        private val repository: UserParamsRepository,
        private val mapper: UserParamsMapper
) : CommonService<UserParamsDto> {

    override fun save(dto: UserParamsDto?): UserParamsDto? = mapper.toDto(repository.save(mapper.toEntity(dto)!!))

    override fun update(dto: UserParamsDto): UserParamsDto {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: Long): UserParamsDto {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(pageable: Pageable): Page<UserParamsDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
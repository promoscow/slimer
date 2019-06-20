package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.xpendence.slimer.dto.ContactDto
import ru.xpendence.slimer.mapper.impl.ContactMapper
import ru.xpendence.slimer.repository.ContactRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-20
 * Time: 20:40
 * e-mail: 2262288@gmail.com
 */
@Service
class ContactService @Autowired constructor(
        private val mapper: ContactMapper,
        private val repository: ContactRepository
) : CommonService<ContactDto> {

    override fun save(dto: ContactDto?): ContactDto? = mapper.toDto(repository.save(mapper.toEntity(dto)))

    override fun update(dto: ContactDto): ContactDto? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: Long): ContactDto? = mapper.toDto(repository.findByIdOrNull(id))

    override fun getAll(pageable: Pageable): Page<ContactDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
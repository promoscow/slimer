package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.ContactDto
import ru.xpendence.slimer.entity.Contact
import ru.xpendence.slimer.mapper.impl.ContactMapper
import ru.xpendence.slimer.repository.ContactRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 22.06.19
 * Time: 15:10
 * e-mail: 2262288@gmail.com
 */
@Service
@ServiceImpl
class ContactServiceImpl : AbstractService<Contact, ContactDto, ContactMapper, ContactRepository>() {

    override fun preCreate(dto: ContactDto?) {
        val userId = dto!!.user
        if (userId == null) dto.default = true
        else {
            if (repository!!.getAllByUserId(userId).isEmpty()) dto.default = true
        }
    }

    override fun preUpdate(dto: ContactDto?) {
        if (dto!!.default == true) {
            val defaultContact = repository!!.getFirstByUserIdAndDefault(dto.user!!, true) ?: return
            defaultContact.default = false
            repository.save(defaultContact)
        }
    }
}
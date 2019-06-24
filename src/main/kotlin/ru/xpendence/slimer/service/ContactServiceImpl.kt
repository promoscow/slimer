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
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class ContactServiceImpl : AbstractService<Contact, ContactDto, ContactMapper, ContactRepository>()
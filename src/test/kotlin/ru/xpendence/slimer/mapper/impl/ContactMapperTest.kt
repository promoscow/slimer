package ru.xpendence.slimer.mapper.impl

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.slimer.AbstractTest
import ru.xpendence.slimer.dto.ContactDto
import ru.xpendence.slimer.entity.Contact
import ru.xpendence.slimer.repository.ContactRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 12.07.19
 * Time: 18:52
 * e-mail: v.chernyshov@pflb.ru
 */
class ContactMapperTest : AbstractTest() {

    @Autowired
    private lateinit var mapper: ContactMapper

    @Autowired
    private lateinit var repository: ContactRepository

    private var contact: Contact? = null
    private var contactDto: ContactDto? = null

    @Before
    fun init() {
        contact = repository.save(createAndSaveContact())
    }

    private fun createAndSaveContact(): Contact {
        val contact = Contact()
        contact.phone = 9001112233
        contact.email = "my12mail@mail.ru"
        contact.user = createAndSaveUser()
        contact.default = true
        return contact
    }

    @Test
    fun toDto() {
        val result = mapper.toDto(contact)

        Assert.assertEquals(contact!!.id, result.id)
        Assert.assertEquals(contact!!.phone, result.phone!!.toLong())
        Assert.assertEquals(contact!!.email, result.email)
        Assert.assertEquals(contact!!.default, result.default)
    }

    @Test
    fun toEntity() {

    }
}
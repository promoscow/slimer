package ru.xpendence.slimer.repository

import org.springframework.stereotype.Repository
import ru.xpendence.slimer.entity.Contact

/**
 * Author: Vyacheslav Chernyshov
 * Date: 07.07.19
 * Time: 10:06
 * e-mail: v.chernyshov@pflb.ru
 */
@Repository
interface ContactRepository : CommonRepository<Contact> {

    fun getAllByUserId(userId: Long): List<Contact>

    fun getFirstByUserIdAndDefault(userId: Long, default: Boolean): Contact?
}
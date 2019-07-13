package ru.xpendence.slimer.repository

import org.springframework.stereotype.Repository
import ru.xpendence.slimer.entity.User

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 13:13
 * e-mail: v.chernyshov@pflb.ru
 */
@Repository
interface UserRepository : CommonRepository<User> {

    fun findByLogin(login: String): User?
}
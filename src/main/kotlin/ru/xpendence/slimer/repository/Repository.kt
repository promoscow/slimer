package ru.xpendence.slimer.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import ru.xpendence.slimer.entity.AbstractEntity
import ru.xpendence.slimer.entity.Contact
import ru.xpendence.slimer.entity.Program
import ru.xpendence.slimer.entity.User

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 19:39
 * e-mail: 2262288@gmail.com
 */
@NoRepositoryBean
interface CommonRepository<E : AbstractEntity> : JpaRepository<E, Long>

@Repository
interface UserRepository : CommonRepository<User>

@Repository
interface ContactRepository : CommonRepository<Contact>

@Repository
interface ProgramRepository : CommonRepository<Program>
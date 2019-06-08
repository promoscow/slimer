package ru.xpendence.slimer.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import ru.xpendence.slimer.entity.AbstractEntity
import ru.xpendence.slimer.entity.User
import ru.xpendence.slimer.entity.UserParams

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
interface UserParamsRepository : CommonRepository<UserParams>
package ru.xpendence.slimer.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import ru.xpendence.slimer.entity.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 19:39
 * e-mail: 2262288@gmail.com
 */
@NoRepositoryBean
interface CommonRepository<E : AbstractEntity> : JpaRepository<E, Long>

@Repository
interface ProductRepository : CommonRepository<Product>

@Repository
interface PortionRepository : CommonRepository<Portion>

@Repository
interface ActivityRepository : CommonRepository<Activity>

@Repository
interface RegistrationTokenRepository : CommonRepository<RegistrationToken>
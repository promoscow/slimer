package ru.xpendence.slimer.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.xpendence.slimer.base.RoleType
import ru.xpendence.slimer.entity.Role

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 11:40
 * e-mail: v.chernyshov@pflb.ru
 */
@Repository
interface RoleRepository : CommonRepository<Role> {

    fun findByRole(role: RoleType): Role

    @Query(
            value = "select * from roles as r where r.role in :roles",
            nativeQuery = true
    )
    fun findAllByRoleIn(roles: List<String>): List<Role>
}
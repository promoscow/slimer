package ru.xpendence.slimer.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.xpendence.slimer.entity.Program

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 23:39
 * e-mail: 2262288@gmail.com
 */
@Repository
interface ProgramRepository : CommonRepository<Program> {

    @Query(
            value = "select * from programs as p where p.user_id = :userId and p.actual = true order by p.created limit 1",
            nativeQuery = true)
    fun findActualByUserId(userId: Long): Program?

    @Query(value = "select * from programs where user_id = :userId", nativeQuery = true)
    fun findAllByUserId(userId: Long): List<Program>

    @Modifying
    @Query(
            value = "update programs as p set p.actual = false where p.user_id = :userId ;",
            nativeQuery = true
    )
    fun deactivate(userId: Long)

    @Query(
            value = "select * from programs where user_id " +
                    "in (select p.user_id from programs as p where p.id = :id) and actual = true",
            nativeQuery = true
    )
    fun findActualInGroupById(id: Long): Program?

    @Modifying
    @Query(
            value = "update programs set actual = false where user_id " +
                    "in (select p.user_id from programs as p where p.id = :id) and actual = true",
            nativeQuery = true
    )
    fun deactivateAllRelated(id: Long)

    @Query("select p.user_id from programs as p where p.id = :id", nativeQuery = true)
    fun getUserIdForProgram(id: Long): Long?
}
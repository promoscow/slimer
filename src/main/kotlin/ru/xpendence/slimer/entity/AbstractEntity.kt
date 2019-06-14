package ru.xpendence.slimer.entity

import ru.xpendence.slimer.base.Active
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-06
 * Time: 20:45
 * e-mail: 2262288@gmail.com
 */
@MappedSuperclass
open class AbstractEntity : Serializable {

    @Id
    @GeneratedValue
    open var id: Long? = null

    @Column(name = "created", updatable = false, nullable = false)
    open var created: LocalDateTime? = null

    @Column(name = "updated", insertable = false)
    open var updated: LocalDateTime? = null

    @Column(name = "active")
    open var active: Active = Active.ENABLED

    @PrePersist
    fun toCreate() {
        if (created == null) created = LocalDateTime.now()
    }

    @PreUpdate
    fun toUpdate() {
        updated = LocalDateTime.now()
    }
}
package ru.xpendence.slimer.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import ru.xpendence.slimer.base.RoleType
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 13.07.19
 * Time: 10:01
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
class Role(

        @Enumerated(EnumType.STRING)
        @Column(name = "role", unique = true, nullable = false)
        var role: RoleType,

        @LazyCollection(LazyCollectionOption.FALSE)
        @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
        var users: MutableList<User> = ArrayList()
) : AbstractEntity()
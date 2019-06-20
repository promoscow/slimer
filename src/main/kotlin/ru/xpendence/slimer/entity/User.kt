package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import ru.xpendence.slimer.base.Gender
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-06
 * Time: 20:47
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class User(

        @Column(name = "height")
        open var height: Int? = null,

        @Column(name = "weight")
        open var weight: Double? = null,

        @Column(name = "gender")
        open var gender: Gender? = null,

        @Column(name = "birth_date")
        open var birthDate: LocalDateTime? = null,

        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "user")
        open var contacts: MutableList<Contact> = ArrayList()

) : AbstractEntity()
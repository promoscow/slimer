package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import ru.xpendence.slimer.base.Gender
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-06
 * Time: 21:09
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "user_params")
@SQLDelete(sql = "UPDATE user_params SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class UserParams : AbstractEntity() {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    open var user: User? = null

    @Column(name = "height")
    open var height: Int? = null

    @Column(name = "weight")
    open var weight: Double? = null

    @Column(name = "gender")
    open var gender: Gender? = null

    @Column(name = "birth_date")
    open var birthDate: LocalDateTime? = null
}
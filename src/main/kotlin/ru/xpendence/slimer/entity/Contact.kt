package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-20
 * Time: 20:10
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "contacts", indexes = [Index(columnList = "user_id", name = "contact_user_index")])
@SQLDelete(sql = "UPDATE contacts SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class Contact : AbstractEntity() {

    @Column(name = "phone")
    open var phone: Long? = null

    @Column
    open var email: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null

    @Column(name = "is_default")
    open var default: Boolean = false
}
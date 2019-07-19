package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 19.07.19
 * Time: 21:00
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "registration_tokens", indexes = [Index(columnList = "token", name = "registration_token_token_index")])
@SQLDelete(sql = "UPDATE registration_tokens SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class RegistrationToken : AbstractEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    open var user: User? = null

    @Column(name = "token", updatable = false, nullable = false)
    open var token: String? = null

    @Column(name = "expiration_time", nullable = false)
    open var expirationTime: LocalDateTime? = null

    @PrePersist
    fun create() {
        if (token == null) token = UUID.randomUUID().toString()
        if (expirationTime == null) expirationTime = LocalDateTime.now().plusDays(1)
    }
}
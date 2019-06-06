package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import ru.xpendence.slimer.base.Active
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-06
 * Time: 21:09
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
class UserParams(

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "member_id")
        var user: User

): AbstractEntity()
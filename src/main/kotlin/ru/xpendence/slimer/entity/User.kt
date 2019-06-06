package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import ru.xpendence.slimer.base.Active
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
class User : AbstractEntity() {

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, mappedBy = "user")
    var params: UserParams? = null
}
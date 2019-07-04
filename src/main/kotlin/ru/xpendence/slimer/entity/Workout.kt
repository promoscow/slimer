package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDate
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.07.19
 * Time: 11:36
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "workouts")
@SQLDelete(sql = "UPDATE workouts SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class Workout : AbstractEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    open var activity: Activity? = null

    @Column(name = "duration")
    open var duration: Int? = null

    @Column(name = "date")
    open var date: LocalDate? = null
}
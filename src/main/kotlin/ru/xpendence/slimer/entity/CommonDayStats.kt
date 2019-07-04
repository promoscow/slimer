package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDate
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.07.19
 * Time: 16:40
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "common_day_stats")
@SQLDelete(sql = "UPDATE common_day_stats SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class CommonDayStats : AbstractEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    open val user: User? = null

    @Column(name = "date", nullable = false)
    open val date: LocalDate? = null

    @Column(name = "is_goal_reached")
    open val goal: Boolean? = null

    @Column(name = "calories_consumed")
    open val caloriesConsumed: Long? = null

    @Column(name = "calories_burn")
    open val caloriesBurn: Long? = null
}
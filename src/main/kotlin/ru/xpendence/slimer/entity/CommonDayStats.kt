package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDate
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.07.19
 * Time: 16:40
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(
        name = "common_day_stats",
        indexes = [Index(columnList = "user_id", name = "common_day_stats_user_index")],
        uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "date"])])
@SQLDelete(sql = "UPDATE common_day_stats SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class CommonDayStats : AbstractEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null

    @Column(name = "date", nullable = false)
    open var date: LocalDate? = null

    @Column(name = "is_goal_reached")
    open var goal: Boolean? = null

    @Column(name = "calories_consumed")
    open var caloriesConsumed: Long? = null

    @Column(name = "calories_burn")
    open var caloriesBurn: Long? = null
}
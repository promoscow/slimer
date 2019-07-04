package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.Entity
import javax.persistence.Table

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
class Workout : AbstractEntity() {

    open var user: User? = null
    open var activity: Activity? = null
    open var
}
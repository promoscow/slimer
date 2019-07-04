package ru.xpendence.slimer.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import ru.xpendence.slimer.base.ActivityType
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.07.19
 * Time: 11:21
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "activities")
@SQLDelete(sql = "UPDATE activities SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class Activity(

        @Column(name = "name", nullable = false, updatable = false)
        open var name: String,

        @Enumerated(value = EnumType.STRING)
        @Column(name = "type", nullable = false, updatable = false)
        open var type: ActivityType,

        @Column(name = "calories", nullable = false, updatable = false)
        open var calories: Int
) : AbstractEntity() {

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "activity")
    open var workouts: MutableList<Workout> = ArrayList()
}
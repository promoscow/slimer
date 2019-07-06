package ru.xpendence.slimer.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDate
import java.time.ZoneId
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:29
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "meals", indexes = [Index(columnList = "user_id", name = "meal_user_index")])
@SQLDelete(sql = "UPDATE meals SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class Meal : AbstractEntity() {

    @Column(name = "name")
    open var name: String? = null

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "meal")
    open var portions: MutableList<Portion> = ArrayList()

    @Column(name = "date", updatable = false)
    open var date: LocalDate? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null

    @PrePersist
    fun onCreate() {
        if (date == null) date = LocalDate.now(ZoneId.systemDefault())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Meal

        if (name != other.name) return false
        if (portions != other.portions) return false
        if (date != other.date) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + portions.hashCode()
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + (user?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Meal(name=$name, portions=$portions, date=$date, user=$user)"
    }

}
package ru.xpendence.slimer.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:29
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "meals")
@SQLDelete(sql = "UPDATE meals SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class Meal : AbstractEntity() {

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "meal")
    open var portions: MutableList<Portion> = ArrayList()
}
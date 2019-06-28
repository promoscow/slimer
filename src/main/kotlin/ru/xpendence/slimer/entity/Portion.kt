package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:10
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "portions")
@SQLDelete(sql = "UPDATE portions SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class Portion : AbstractEntity() {

    @Column(name = "product")
    open var product: Product? = null

    @Column(name = "weight")
    open var weight: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Portion

        if (product != other.product) return false
        if (weight != other.weight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = product?.hashCode() ?: 0
        result = 31 * result + (weight ?: 0)
        return result
    }

    override fun toString(): String {
        return "Portion(product=$product, weight=$weight)"
    }
}
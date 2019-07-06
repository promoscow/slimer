package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:10
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "portions", indexes = [Index(columnList = "meal_id", name = "portion_meal_index")])
@SQLDelete(sql = "UPDATE portions SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class Portion : AbstractEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    open var product: Product? = null

    @Column(name = "weight")
    open var weight: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    open var meal: Meal? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Portion

        if (product != other.product) return false
        if (weight != other.weight) return false
        if (meal != other.meal) return false

        return true
    }

    override fun hashCode(): Int {
        var result = product?.hashCode() ?: 0
        result = 31 * result + (weight ?: 0)
        result = 31 * result + (meal?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Portion(product=$product, weight=$weight, meal=$meal)"
    }
}
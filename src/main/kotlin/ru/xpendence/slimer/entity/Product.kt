package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-27
 * Time: 20:09
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class Product : AbstractEntity() {

    @Column(name = "name")
    open var name: String? = null

    @Column(name = "proteins")
    open var proteins: Double? = null

    @Column(name = "carbohydrates")
    open var carbohydrates: Double? = null

    @Column(name = "fats")
    open var fats: Double? = null

    @Column(name = "calories")
    open var calories: Double? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (name != other.name) return false
        if (proteins != other.proteins) return false
        if (carbohydrates != other.carbohydrates) return false
        if (fats != other.fats) return false
        if (calories != other.calories) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (proteins?.hashCode() ?: 0)
        result = 31 * result + (carbohydrates?.hashCode() ?: 0)
        result = 31 * result + (fats?.hashCode() ?: 0)
        result = 31 * result + (calories?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Product(name=$name, proteins=$proteins, carbohydrates=$carbohydrates, fats=$fats, calories=$calories)"
    }


}
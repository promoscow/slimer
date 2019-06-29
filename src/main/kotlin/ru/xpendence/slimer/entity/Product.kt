package ru.xpendence.slimer.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

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
    open var calories: Int? = null

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "product")
    open var portions: MutableList<Portion> = ArrayList()
}
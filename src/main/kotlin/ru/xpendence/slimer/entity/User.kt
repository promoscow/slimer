package ru.xpendence.slimer.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import ru.xpendence.slimer.base.BmiCategory
import ru.xpendence.slimer.base.Gender
import ru.xpendence.slimer.base.PhysicalActivityIndex
import java.time.LocalDate
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-06
 * Time: 20:47
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class User : AbstractEntity() {

    @Column(name = "login", nullable = false, updatable = false)
    open var login: String? = null

    @Column(name = "password", nullable = false)
    open var password: String? = null

    @Column(name = "first_name")
    open var firstName: String? = null

    @Column(name = "last_name")
    open var lastName: String? = null

    @Column(name = "height")
    open var height: Int? = null

    @Column(name = "weight")
    open var weight: Double? = null

    @Column(name = "age")
    open var age: Int? = null

    @Column(name = "gender")
    open var gender: Gender? = null

    @Column(name = "birth_date")
    open var birthDate: LocalDate? = null

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    open var contacts: MutableList<Contact> = ArrayList()

    //todo почему Double?
    @Column(name = "dci", nullable = false)
    open var dailyCaloriesIndex: Double? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "pai", nullable = false)
    open var physicalActivityIndex: PhysicalActivityIndex? = null

    @Column(name = "bmi")
    open var bodyMassIndex: Double? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "bmi_category")
    open var bmiCategory: BmiCategory? = null

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    open var programs: MutableList<Program> = ArrayList()

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    open var meals: MutableList<Meal> = ArrayList()

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    open var workouts: MutableList<Workout> = ArrayList()

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    open var roles: MutableList<Role> = ArrayList()
}
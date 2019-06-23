package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Author: Vyacheslav Chernyshov
 * Date: 23.06.19
 * Time: 20:54
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "programs")
@SQLDelete(sql = "UPDATE programs SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
class Program : AbstractEntity() {


}
package ru.xpendence.slimer.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import ru.xpendence.slimer.base.ProgramType
import java.time.LocalDate
import javax.persistence.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 23.06.19
 * Time: 20:54
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "programs", indexes = [Index(columnList = "user_id", name = "program_user_index")])
@SQLDelete(sql = "UPDATE programs SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
open class Program : AbstractEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null

    @Column(name = "is_finished")
    open var finished: Boolean? = null

    @Column(name = "start_weight")
    open var startWeight: Double? = null

    @Column(name = "goal_weight")
    open var goalWeight: Double? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "program_type")
    open var programType: ProgramType? = null

    @Column(name = "estimated_finish")
    open var estimatedFinishDate: LocalDate? = null
    
    @Column(name = "actual")
    open var actual: Boolean? = null
}
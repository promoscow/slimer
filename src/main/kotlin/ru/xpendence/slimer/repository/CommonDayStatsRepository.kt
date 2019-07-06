package ru.xpendence.slimer.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.xpendence.slimer.entity.CommonDayStats
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 23:41
 * e-mail: 2262288@gmail.com
 */

@Repository
interface CommonDayStatsRepository : CommonRepository<CommonDayStats> {

    @Query(
            value = "select * from common_day_stats where user_id = :userId and date = :requestedDate",
            nativeQuery = true
    )
    fun getByUserIdAndDate(userId: Long, requestedDate: LocalDate): CommonDayStats?
}
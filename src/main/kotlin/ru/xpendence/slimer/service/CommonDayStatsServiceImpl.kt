package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.CommonDayStatsDto
import ru.xpendence.slimer.entity.CommonDayStats
import ru.xpendence.slimer.mapper.CommonDayStatsMapper
import ru.xpendence.slimer.repository.CommonDayStatsRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 8:39
 * e-mail: v.chernyshov@pflb.ru
 */
@ServiceImpl
@Service
class CommonDayStatsServiceImpl
    : AbstractService<CommonDayStats, CommonDayStatsDto, CommonDayStatsMapper, CommonDayStatsRepository>()
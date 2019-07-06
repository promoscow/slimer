package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.ActivityDto
import ru.xpendence.slimer.entity.Activity
import ru.xpendence.slimer.mapper.impl.ActivityMapper
import ru.xpendence.slimer.repository.ActivityRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 06.07.19
 * Time: 16:13
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class ActivityServiceImpl : AbstractService<Activity, ActivityDto, ActivityMapper, ActivityRepository>()
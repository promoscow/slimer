package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.PortionDto
import ru.xpendence.slimer.entity.Portion
import ru.xpendence.slimer.mapper.impl.PortionMapper
import ru.xpendence.slimer.repository.PortionRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:19
 * e-mail: 2262288@gmail.com
 */
@Service
@ServiceImpl
class PortionServiceImpl : AbstractService<Portion, PortionDto, PortionMapper, PortionRepository>()
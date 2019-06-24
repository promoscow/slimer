package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.ProgramDto
import ru.xpendence.slimer.entity.Program
import ru.xpendence.slimer.mapper.impl.ProgramMapper
import ru.xpendence.slimer.repository.ProgramRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 11:25
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class ProgramService : AbstractService<Program, ProgramDto, ProgramMapper, ProgramRepository>()
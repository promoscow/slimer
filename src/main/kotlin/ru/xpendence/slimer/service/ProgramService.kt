package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.base.ProgramType
import ru.xpendence.slimer.dto.ProgramDto
import ru.xpendence.slimer.entity.Program
import ru.xpendence.slimer.mapper.impl.ProgramMapper
import ru.xpendence.slimer.repository.ProgramRepository
import ru.xpendence.slimer.util.calculate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 11:25
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class ProgramService @Autowired constructor(
        private val userService: UserServiceImpl
) : AbstractService<Program, ProgramDto, ProgramMapper, ProgramRepository>() {

    fun calculate(id: Long, goalWeight: Double): List<ProgramDto> {
        val userDto = userService.get(id)
        return listOf(
                ProgramDto().calculate(userDto!!, goalWeight, ProgramType.COMFORT),
                ProgramDto().calculate(userDto, goalWeight, ProgramType.HARD),
                ProgramDto().calculate(userDto, goalWeight, ProgramType.BRUTAL)
        )
    }
}
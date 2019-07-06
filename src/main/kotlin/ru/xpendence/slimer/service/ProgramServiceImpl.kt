package ru.xpendence.slimer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.base.ProgramType
import ru.xpendence.slimer.base.StatusCode
import ru.xpendence.slimer.dto.ProgramDto
import ru.xpendence.slimer.dto.TodayCaloriesDto
import ru.xpendence.slimer.dto.UserDto
import ru.xpendence.slimer.entity.Program
import ru.xpendence.slimer.exceptions.DataAccessException
import ru.xpendence.slimer.exceptions.NoMatchingValueException
import ru.xpendence.slimer.mapper.impl.ProgramMapper
import ru.xpendence.slimer.repository.ProgramRepository
import ru.xpendence.slimer.util.calculate
import ru.xpendence.slimer.util.logger
import java.time.LocalDate

/**
 * Author: Vyacheslav Chernyshov
 * Date: 24.06.19
 * Time: 11:25
 * e-mail: 2262288@gmail.com
 */
@Service
@ServiceImpl
class ProgramServiceImpl @Autowired constructor(
        private val userService: UserServiceImpl
) : AbstractService<Program, ProgramDto, ProgramMapper, ProgramRepository>() {
    override val log = logger<ProgramServiceImpl>()

    override fun preCreate(dto: ProgramDto?) {
        val userDto = userService.get(dto!!.user!!)
        val programDto = dto.calculate(userDto!!, dto.goalWeight!!, ProgramType.valueOf(dto.programType!!))

        dto.finished = false
        dto.startWeight = userDto.weight
        dto.estimatedDays = programDto.estimatedDays
        dto.estimatedFinishDate = programDto.estimatedFinishDate

        repository!!.deactivate(dto.user!!)
    }

    override fun preUpdate(dto: ProgramDto?) {
        if (dto!!.actual) repository!!.deactivate(getUserIdForProgram(dto))
    }

    fun calculate(id: Long, goalWeight: Double): List<ProgramDto> {
        val userDto = userService.get(id)
        val programs = listOf(
                ProgramDto().calculate(userDto!!, goalWeight, ProgramType.COMFORT),
                ProgramDto().calculate(userDto, goalWeight, ProgramType.HARD),
                ProgramDto().calculate(userDto, goalWeight, ProgramType.BRUTAL)
        )
        log.info("programs calculated: $programs")
        return programs
    }

    fun getActualByUser(userId: Long): ProgramDto =
            mapper!!.toDto(repository!!.findActualByUserId(userId)
                    ?: throw DataAccessException(StatusCode.DATABASE_ERROR.name, "No programs for user $userId"))

    fun getAllByUser(userId: Long): List<ProgramDto> = repository!!.findAllByUserId(userId).map { mapper!!.toDto(it) }

    fun getCaloriesByDate(userId: Long, date: LocalDate): TodayCaloriesDto =
            TodayCaloriesDto(userId, date, calculateCalories(userService.get(userId)))

    private fun getUserIdForProgram(dto: ProgramDto): Long =
            repository!!.getUserIdForProgram(dto.id!!)
                    ?: throw DataAccessException(
                            StatusCode.DATABASE_ERROR.name,
                            "No active programs for owner of program ${dto.id}"
                    )

    private fun getActualInGroupByProgramId(dto: ProgramDto): Program =
            repository!!.findActualInGroupById(dto.id!!)
                    ?: throw DataAccessException(
                            StatusCode.DATABASE_ERROR.name,
                            "No active programs for owner of program ${dto.id}"
                    )

    private fun calculateCalories(userDto: UserDto?): Int =
            userDto!!.dailyCaloriesIndex!!.times(
                    (
                            ProgramType.valueOf(userDto.programs
                                    .sortedBy { it.created }
                                    .findLast { it.actual }
                                    ?.programType
                                    ?: throw NoMatchingValueException(
                                            StatusCode.DATABASE_ERROR.name,
                                            "Not found actual programs for user ${userDto.id}"
                                    )).percentage)
                            .times(100)
                            .toInt()
            ).div(100)
}
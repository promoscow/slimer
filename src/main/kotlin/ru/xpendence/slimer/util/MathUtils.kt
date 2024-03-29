package ru.xpendence.slimer.util

import ru.xpendence.slimer.base.ProgramType
import ru.xpendence.slimer.dto.ProgramDto
import ru.xpendence.slimer.dto.UserDto
import java.time.LocalDate
import kotlin.math.round

/**
 * Author: Vyacheslav Chernyshov
 * Date: 23.06.19
 * Time: 13:57
 * e-mail: 2262288@gmail.com
 */

const val caloriesInFat: Double = 5.5
const val maxProgramLength: Int = 90

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this.times(multiplier)).div(multiplier)
}

fun ProgramDto.calculate(dto: UserDto, goalWeight: Double, programType: ProgramType): ProgramDto {

    var currentWeight = dto.weight!!
    var expectedDci: Int
    var daysCounter = 0

    val temporalUserDto: UserDto = createTempUSerDto(dto)

    do {
        daysCounter++
        temporalUserDto.dailyCaloriesIndex = temporalUserDto.calculateDci()
        expectedDci = temporalUserDto.dailyCaloriesIndex!!.times(programType.percentage).toInt()

        val differenceCalories = temporalUserDto.dailyCaloriesIndex!!.minus(expectedDci)
        currentWeight -= differenceCalories.div(caloriesInFat).div(1000)
        temporalUserDto.weight = currentWeight

    } while (currentWeight >= goalWeight && daysCounter < maxProgramLength)

    val program = ProgramDto()

    program.startWeight = dto.weight
    program.estimatedFinishDate = LocalDate.now().plusDays(daysCounter.toLong())
    program.estimatedDays = daysCounter
    program.goalWeight = if (currentWeight < goalWeight) goalWeight else currentWeight.round(1)
    program.programType = programType.name
    program.user = dto.id

    return program
}

private fun createTempUSerDto(dto: UserDto): UserDto {
    val temporalUserDto = UserDto()
    temporalUserDto.weight = dto.weight
    temporalUserDto.gender = dto.gender
    temporalUserDto.height = dto.height
    temporalUserDto.age = dto.age
    temporalUserDto.physicalActivityIndex = dto.physicalActivityIndex
    return temporalUserDto
}
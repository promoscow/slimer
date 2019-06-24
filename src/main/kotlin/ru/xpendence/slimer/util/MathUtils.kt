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
 * e-mail: v.chernyshov@pflb.ru
 */

const val caloriesInFat: Double = 5.5
const val maxProgramLength: Int = 90

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this.times(multiplier)).div(multiplier)
}

fun calculate(dto: UserDto, goalWeight: Double, programType: ProgramType): ProgramDto {

    println("|| Start weight: ${dto.weight}, goal weight: $goalWeight")
    println("|| Program type: ${programType.name}, percentage: ${programType.percentage}")
    println("|| Daily calories: ${dto.dailyCaloriesIndex}")
    println()

    var currentWeight = dto.weight!!
    var expectedDci: Int
    var daysCounter = 0

    val temporalUserDto: UserDto = createTempUSerDto(dto)

    do {
        daysCounter++
        temporalUserDto.dailyCaloriesIndex = temporalUserDto.calculateDci()
        expectedDci = temporalUserDto.dailyCaloriesIndex!!.times(programType.percentage).toInt()

        print("expected DCI: $expectedDci. ")

        val differenceCalories = temporalUserDto.dailyCaloriesIndex!!.minus(expectedDci)
        currentWeight -= differenceCalories.div(caloriesInFat).div(1000)
        temporalUserDto.weight = currentWeight

        println("Day $daysCounter: weight: ${temporalUserDto.weight}, DCI: ${temporalUserDto.dailyCaloriesIndex}")

    } while (currentWeight >= goalWeight && daysCounter < maxProgramLength)

    val program = ProgramDto()

    program.startWeight = dto.weight
    program.estimatedFinishDate = LocalDate.now().plusDays(daysCounter.toLong())
    program.estimatedDays = daysCounter
    program.goalWeight = if (currentWeight < goalWeight) goalWeight else currentWeight.round(1)
    program.programType = programType.name
    program.user = dto.id

    println("Compiled program: $program")

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
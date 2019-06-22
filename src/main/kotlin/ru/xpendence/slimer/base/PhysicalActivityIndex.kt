@file: JvmName("PAI")
package ru.xpendence.slimer.base

/**
 * Author: Vyacheslav Chernyshov
 * Date: 22.06.19
 * Time: 16:40
 * e-mail: v.chernyshov@pflb.ru
 */
enum class PhysicalActivityIndex {
    VERY_LOW, LOW, AVERAGE, HIGH
}

val indexes: HashMap<PhysicalActivityIndex, HashMap<Gender, Double>> =
        hashMapOf(
                PhysicalActivityIndex.VERY_LOW to hashMapOf(Gender.FEMALE to 1.3, Gender.MALE to 1.3),
                PhysicalActivityIndex.LOW to hashMapOf(Gender.FEMALE to 1.4, Gender.MALE to 1.4),
                PhysicalActivityIndex.AVERAGE to hashMapOf(Gender.FEMALE to 1.6, Gender.MALE to 1.7),
                PhysicalActivityIndex.HIGH to hashMapOf(Gender.FEMALE to 1.8, Gender.MALE to 1.9)
        )
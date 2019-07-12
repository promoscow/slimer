package ru.xpendence.slimer

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import ru.xpendence.slimer.base.BmiCategory
import ru.xpendence.slimer.base.Gender
import ru.xpendence.slimer.base.PhysicalActivityIndex
import ru.xpendence.slimer.entity.User
import ru.xpendence.slimer.repository.UserRepository
import java.time.LocalDate

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles(profiles = ["h2"])
class AbstractTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun contextLoads() {
    }

    fun createAndSaveUser(): User {
        val user = User()
        user.gender = Gender.MALE
        user.height = 180
        user.weight = 70.0
        user.birthDate = LocalDate.now().minusYears(30).plusMonths(3).minusDays(5)
        user.age = 29
        user.bmiCategory = BmiCategory.NORMAL
        user.bodyMassIndex = 22.3
        user.physicalActivityIndex = PhysicalActivityIndex.AVERAGE
        user.dailyCaloriesIndex = 2000.0
        return userRepository.save(user)
    }

}

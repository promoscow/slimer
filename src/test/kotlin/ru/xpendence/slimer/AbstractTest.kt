package ru.xpendence.slimer

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles(profiles = ["h2"])
class AbstractTest {

    @Test
    fun contextLoads() {
    }

}

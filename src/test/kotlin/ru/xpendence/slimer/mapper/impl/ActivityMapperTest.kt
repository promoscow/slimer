package ru.xpendence.slimer.mapper.impl

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.slimer.AbstractTest
import ru.xpendence.slimer.base.ActivityType
import ru.xpendence.slimer.dto.ActivityDto
import ru.xpendence.slimer.entity.Activity
import ru.xpendence.slimer.repository.ActivityRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-07-08
 * Time: 20:40
 * e-mail: 2262288@gmail.com
 */
class ActivityMapperTest : AbstractTest() {

    @Autowired
    private lateinit var mapper: ActivityMapper

    @Autowired
    private lateinit var repository: ActivityRepository

    private var activity: Activity? = null
    private var activityDto: ActivityDto? = null

    @Before
    fun init() {
        activity = repository.save(Activity("Передвижения боком", ActivityType.ENTERTAINMENT, 100))
        activityDto = ActivityDto("Сидение на дереве", "ENTERTAINMENT", 100)
    }

    @Test
    fun toDto() {
        val result = mapper.toDto(activity)

        Assert.assertEquals(activity!!.name, result.name)
        Assert.assertEquals(activity!!.type.name, result.type)
        Assert.assertEquals(activity!!.calories, result.calories)
    }

    @Test
    fun toEntity() {
        val result = mapper.toEntity(activityDto)

        Assert.assertEquals(activityDto!!.name, result.name)
        Assert.assertEquals(activityDto!!.type, result.type.name)
        Assert.assertEquals(activityDto!!.calories, result.calories)

    }
}
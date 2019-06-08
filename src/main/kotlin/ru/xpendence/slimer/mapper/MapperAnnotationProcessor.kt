package ru.xpendence.slimer.mapper

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import org.springframework.util.ReflectionUtils
import java.util.*
import kotlin.reflect.KClass

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-08
 * Time: 20:36
 * e-mail: 2262288@gmail.com
 */
@Component
class MapperAnnotationProcessor : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? = init(bean)

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? = bean

    private fun init(bean: Any): Any {
        val managedBeanClass: Class<*> = bean.javaClass
        val mapper: Mapper = managedBeanClass.getAnnotation(Mapper::class.java)

        if (Objects.nonNull(mapper)) {
            ReflectionUtils.doWithFields(managedBeanClass) {f ->
                val fieldName = f.name
                if (fieldName!= "entityClass" && fieldName != "dtoClass") return@doWithFields
                ReflectionUtils.makeAccessible(f)
                val targetClass: KClass<*> = if (fieldName == "entityClass") mapper.entity else mapper.dto
                f.set(bean, targetClass)
            }
        }
        return Any()
    }
}
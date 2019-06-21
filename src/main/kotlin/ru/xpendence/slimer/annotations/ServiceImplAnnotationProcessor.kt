package ru.xpendence.slimer.annotations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import org.springframework.util.ReflectionUtils
import org.springframework.web.context.WebApplicationContext
import java.util.*

/**
 * Author: Vyacheslav Chernyshov
 * Date: 21.06.19
 * Time: 13:44
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
class ServiceImplAnnotationProcessor @Autowired constructor(
        private val context: WebApplicationContext
) : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? =
            if (Objects.nonNull(bean)) init(bean) else null

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? = bean

    private fun init(bean: Any): Any {
        val managedBeanClass: Class<*> = bean.javaClass
        val serviceImpl: ServiceImpl? = managedBeanClass.getAnnotation(ServiceImpl::class.java)

        if (Objects.nonNull(serviceImpl)) {
            val simpleName: String = bean.javaClass.simpleName
            ReflectionUtils.doWithFields(managedBeanClass) {
                val fieldName: String = it.name
                if (fieldName == "mapper") {
                    ReflectionUtils.makeAccessible(it)
                    val mapper: String = simpleName.replace("ServiceImpl", "Mapper")
                    val replacedMapper: String = mapper.replaceFirst(
                            mapper.substring(0, 1),
                            mapper.substring(0, 1).toLowerCase()
                    )
                    it.set(bean, context.getBean(replacedMapper))
                } else if (fieldName == "repository") {
                    ReflectionUtils.makeAccessible(it)
                    val repository: String = simpleName.replace("ServiceImpl", "Repository")
                    val replaceRepository: String = repository.replaceFirst(
                            repository.substring(0, 1),
                            repository.substring(0, 1).toLowerCase()
                    )
                    it.set(bean, context.getBean(replaceRepository))
                }
            }
        }
        return bean
    }
}
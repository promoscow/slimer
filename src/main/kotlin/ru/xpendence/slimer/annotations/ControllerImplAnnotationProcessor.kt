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
 * Time: 14:49
 * e-mail: 2262288@gmail.com
 */
@Component
class ControllerImplAnnotationProcessor@Autowired constructor(
        private val context: WebApplicationContext
) : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? =
            if (Objects.nonNull(bean)) init(bean) else null

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? = bean

    private fun init(bean: Any): Any {
        val managedBeanClass: Class<*> = bean.javaClass
        val controllerImpl: ControllerImpl? = managedBeanClass.getAnnotation(ControllerImpl::class.java)

        if (Objects.nonNull(controllerImpl)) {
            val simpleName: String = bean.javaClass.simpleName
            ReflectionUtils.doWithFields(managedBeanClass) {
                val fieldName: String = it.name
                if (fieldName == "service") {
                    ReflectionUtils.makeAccessible(it)
                    val service: String = simpleName.replace("Controller", "ServiceImpl")
                    val replacedMapper: String = service.replaceFirst(
                            service.substring(0, 1),
                            service.substring(0, 1).toLowerCase()
                    )
                    it.set(bean, context.getBean(replacedMapper))
                }
            }
        }
        return bean
    }
}
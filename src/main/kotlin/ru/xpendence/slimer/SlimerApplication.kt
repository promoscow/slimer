package ru.xpendence.slimer

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableScheduling
@PropertySources(
        PropertySource("classpath:security.properties"),
        PropertySource("classpath:path.properties"),
        PropertySource("classpath:text.properties")
)
@EnableAsync
class SlimerApplication {

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()

    @Bean
    fun modelMapper(): ModelMapper {
        val mapper = ModelMapper()
        mapper.configuration
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        return mapper
    }
}

fun main(args: Array<String>) {
    runApplication<SlimerApplication>(*args)
}
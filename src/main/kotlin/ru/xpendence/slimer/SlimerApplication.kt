package ru.xpendence.slimer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SlimerApplication

fun main(args: Array<String>) {
    runApplication<SlimerApplication>(*args)
}

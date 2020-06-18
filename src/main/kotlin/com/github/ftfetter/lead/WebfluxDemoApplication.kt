package com.github.ftfetter.lead

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WebfluxDemoApplication

fun main(args: Array<String>) {
	runApplication<WebfluxDemoApplication>(*args)
}

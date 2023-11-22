package ru.taratonov.audit

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

val logger = KotlinLogging.logger {  }

@SpringBootApplication
class AuditApplication

fun main(args: Array<String>) {
	runApplication<AuditApplication>(*args)
}

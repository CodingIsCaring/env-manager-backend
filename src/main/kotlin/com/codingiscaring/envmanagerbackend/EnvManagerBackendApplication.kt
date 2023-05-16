package com.codingiscaring.envmanagerbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EnvManagerBackendApplication

fun main(args: Array<String>) {

	runApplication<EnvManagerBackendApplication>(*args)
}

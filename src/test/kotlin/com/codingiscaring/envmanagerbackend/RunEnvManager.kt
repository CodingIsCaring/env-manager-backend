package com.codingiscaring.envmanagerbackend

import org.springframework.boot.devtools.restart.RestartScope
import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

fun main(args: Array<String>) {
    fromApplication<EnvManagerBackendApplication>()
            .with(Configuration::class)
            .run(*args)
}

@TestConfiguration(proxyBeanMethods = false)
class Configuration {

    @Bean
    @RestartScope
    @ServiceConnection
    fun mongoContainer(): MongoDBContainer {
        return MongoDBContainer(DockerImageName.parse("mongo:latest"))
                .withReuse(true)
    }
}
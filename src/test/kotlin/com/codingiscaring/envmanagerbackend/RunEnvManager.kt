package com.codingiscaring.envmanagerbackend

import com.github.dockerjava.api.command.InspectContainerResponse
import org.springframework.boot.devtools.restart.RestartScope
import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.wait.strategy.Wait
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
    fun mongoContainer() = StatefulMongoDBContainer(DockerImageName.parse("mongo:6.0.8"))

    class StatefulMongoDBContainer(imageName: DockerImageName) : MongoDBContainer(imageName) {
        override fun configure() {
            waitingFor(Wait.forLogMessage("(?i).*waiting for connections.*", 1));
            withFileSystemBind("./.data", "/data/db", BindMode.READ_WRITE)
            portBindings = listOf("27019:27017")
        }

        override fun containerIsStarted(containerInfo: InspectContainerResponse?, reused: Boolean) {
            // do nothing
        }
    }
}
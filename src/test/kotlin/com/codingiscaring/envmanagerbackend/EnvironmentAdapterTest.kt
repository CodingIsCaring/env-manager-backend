package com.codingiscaring.envmanagerbackend

import com.codingiscaring.envmanagerbackend.models.Environment
import com.codingiscaring.envmanagerbackend.repositories.EnvironmentAdapter
import com.codingiscaring.envmanagerbackend.repositories.EnvironmentRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnvironmentAdapterTest {
    @Autowired
    lateinit var repository: EnvironmentRepository

    private lateinit var environmentAdapter: EnvironmentAdapter

    companion object {
        @ServiceConnection
        @Container
        @JvmStatic
        val mongoContainer = MongoDBContainer(DockerImageName.parse("mongo:latest"))

        init {
            mongoContainer.start()
        }
    }

    @BeforeAll
    fun setup() {
        environmentAdapter = EnvironmentAdapter(repository)
    }

    @Test
    fun shouldStoreAnEnvironment() {
        val environment = Environment("local", "local environment")
        environmentAdapter.create(environment)

        assert(repository.count() == 1L) { "It should have one environment saved" }
    }
}
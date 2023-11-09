package com.codingiscaring.envmanagerbackend.controller

import com.codingiscaring.envmanagerbackend.models.Environment
import com.codingiscaring.envmanagerbackend.repositories.EnvironmentRepository
import com.codingiscaring.envmanagerbackend.repositories.entities.EnvironmentEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class EnvironmentControllerShould {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var repository: EnvironmentRepository

    companion object {
        @ServiceConnection
        @Container
        @JvmStatic
        val mongoContainer = MongoDBContainer(DockerImageName.parse("mongo:latest"))

        init {
            mongoContainer.start()
        }
    }

    @BeforeEach
    fun setup() {
        repository.deleteAll()
    }

    @Test
    fun `create an environment`() {
        mockMvc.perform(
            post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "local", "description": "local environment"}""")
        )
            .andExpect(status().isCreated)
    }

    @Test
    fun `not create an environment when it already exists`() {
        val environment = Environment("local", "local environment")
        val environmentEntity = EnvironmentEntity.mapFrom(environment)
        repository.insert(environmentEntity)

        mockMvc.perform(
            post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "local", "description": "local environment"}""")
        )
            .andExpect(status().isConflict)
    }

    @Test
    fun `not create an environment when name is empty`() {
        mockMvc.perform(
            post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "", "description": "local environment"}""")
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `not create an environment when name is a blank space`() {
        mockMvc.perform(
            post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": " ", "description": "local environment"}""")
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `not create an environment when name is null`() {
        mockMvc.perform(
            post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": null, "description": "local environment"}""")
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `create an environment when description is null`() {
        mockMvc.perform(
            post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "local", "description": null}""")
        )
            .andExpect(status().isCreated)
    }

    @Test
    fun `not create an environment when description is empty`() {
        mockMvc.perform(
            post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "local", "description": ""}""")
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `not create an environment when description is a blank space`() {
        mockMvc.perform(
            post("/environments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "local", "description": " "}""")
        )
            .andExpect(status().isBadRequest)
    }
}
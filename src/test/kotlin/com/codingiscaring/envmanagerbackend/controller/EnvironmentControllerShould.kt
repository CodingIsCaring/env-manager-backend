package com.codingiscaring.envmanagerbackend.controller

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

    companion object {
        @ServiceConnection
        @Container
        @JvmStatic
        val mongoContainer = MongoDBContainer(DockerImageName.parse("mongo:latest"))

        init {
            mongoContainer.start()
        }
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

}
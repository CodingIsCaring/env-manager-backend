package com.codingiscaring.envmanagerbackend.repositories.entities

import com.codingiscaring.envmanagerbackend.models.Environment
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "environments")
class EnvironmentEntity {
    @Id
    lateinit var id: UUID
    lateinit var name: String
    var description: String? = null

    companion object {
        fun mapFrom(environment: Environment): EnvironmentEntity {
            return EnvironmentEntity().apply {
                id = UUID.randomUUID()
                name = environment.name
                description = environment.description
            }
        }
    }
}
package com.codingiscaring.envmanagerbackend.repositories.entities

import com.codingiscaring.envmanagerbackend.models.Environment
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "environments")
class EnvironmentEntity {

    @Indexed(unique = true)
    lateinit var name: String
    var description: String? = null

    companion object {
        fun mapFrom(environment: Environment): EnvironmentEntity {
            return EnvironmentEntity().apply {
                name = environment.name
                description = environment.description
            }
        }
    }
}
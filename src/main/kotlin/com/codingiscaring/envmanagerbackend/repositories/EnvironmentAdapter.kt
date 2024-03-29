package com.codingiscaring.envmanagerbackend.repositories

import com.codingiscaring.envmanagerbackend.models.Environment
import com.codingiscaring.envmanagerbackend.repositories.entities.EnvironmentEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

// ToDo: Rename this class to EnvironmentRepository
@Component
class EnvironmentAdapter(@Autowired val environmentRepository: EnvironmentRepository) {
    fun create(environment: Environment): Result<String> {
        var entity = EnvironmentEntity.mapFrom(environment)
        try {
            environmentRepository.insert(entity)
            return Result.success("")
        } catch (exception: Exception) {
            println(exception.message)
            return Result.failure(exception)
        }
    }
}
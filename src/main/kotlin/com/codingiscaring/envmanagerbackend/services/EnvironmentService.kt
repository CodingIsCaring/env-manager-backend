package com.codingiscaring.envmanagerbackend.services

import com.codingiscaring.envmanagerbackend.models.Environment
import com.codingiscaring.envmanagerbackend.repositories.EnvironmentRepository
import com.codingiscaring.envmanagerbackend.repositories.entities.EnvironmentEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class EnvironmentService (@Autowired val abc: abc) {
    fun create(environment: Environment) {
        abc.create(environment)
    }
}


@Component
class abc (@Autowired val environmentRepository: EnvironmentRepository) {
    fun create(environment: Environment) {
        var entity = EnvironmentEntity.mapFrom(environment)
        environmentRepository.insert(entity)
    }
}
package com.codingiscaring.envmanagerbackend.services

import com.codingiscaring.envmanagerbackend.entities.Environment
import com.codingiscaring.envmanagerbackend.repositories.EnvironmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EnvironmentService @Autowired constructor (var environmentRepository: EnvironmentRepository) {
    fun create(environment: Environment) {
        environmentRepository.save(environment)
    }


}

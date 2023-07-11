package com.codingiscaring.envmanagerbackend.services

import com.codingiscaring.envmanagerbackend.models.Environment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EnvironmentService (@Autowired val environmentRepository: BaseRepository) {
    fun create(environment: Environment) {
        environmentRepository.insert(environment)
    }
}

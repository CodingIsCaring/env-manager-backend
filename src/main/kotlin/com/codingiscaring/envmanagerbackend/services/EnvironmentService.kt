package com.codingiscaring.envmanagerbackend.services

import com.codingiscaring.envmanagerbackend.models.Environment
import com.codingiscaring.envmanagerbackend.repositories.EnvironmentAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EnvironmentService (@Autowired val environmentAdapter: EnvironmentAdapter) {
    fun create(environment: Environment) {
        environmentAdapter.create(environment)
    }
}

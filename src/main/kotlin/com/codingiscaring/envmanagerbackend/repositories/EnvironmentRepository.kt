package com.codingiscaring.envmanagerbackend.repositories

import com.codingiscaring.envmanagerbackend.models.Environment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class EnvironmentRepository (@Autowired val baseRepository: BaseRepository<Environment>) {
    fun save(environment: Environment) {
        baseRepository.save(environment)
    }
}

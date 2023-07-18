package com.codingiscaring.envmanagerbackend.repositories

import com.codingiscaring.envmanagerbackend.repositories.entities.EnvironmentEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EnvironmentRepository : MongoRepository<EnvironmentEntity, String>
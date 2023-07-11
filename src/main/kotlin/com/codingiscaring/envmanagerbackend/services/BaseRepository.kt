package com.codingiscaring.envmanagerbackend.services

import com.codingiscaring.envmanagerbackend.models.Environment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BaseRepository: MongoRepository<Environment, String>
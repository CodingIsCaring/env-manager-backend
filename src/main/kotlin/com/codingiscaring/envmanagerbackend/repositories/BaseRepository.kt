package com.codingiscaring.envmanagerbackend.repositories

import org.springframework.data.mongodb.repository.MongoRepository

interface BaseRepository<T>: MongoRepository<T, String>
package com.codingiscaring.envmanagerbackend.controllers

import com.codingiscaring.envmanagerbackend.controllers.requests.EnvironmentRequest
import com.codingiscaring.envmanagerbackend.models.Environment
import com.codingiscaring.envmanagerbackend.services.EnvironmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EnvironmentController(@Autowired val environmentService: EnvironmentService) {

    @CrossOrigin
    @PostMapping("/environments")
    fun createEnvironment(@RequestBody request: EnvironmentRequest): ResponseEntity<String> {
        assertValid(request)
        val environment = Environment.mapFrom(request)
        val result = environmentService.create(environment)
        if (!result.isFailure) {
            return ResponseEntity("Environment created successfully", HttpStatus.CREATED)
        }
        return ResponseEntity("Environment could not be created", HttpStatus.CONFLICT)
    }

    private fun assertValid(request: EnvironmentRequest) {
        if (request.name.isBlank()) {
            throw IllegalArgumentException("Environment name cannot be blank")
        }
        if (request.description?.isBlank() == true) {
            throw IllegalArgumentException("Environment description cannot be blank")
        }
    }
}
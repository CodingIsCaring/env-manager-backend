package com.codingiscaring.envmanagerbackend.controllers

import com.codingiscaring.envmanagerbackend.controllers.requests.EnvironmentRequest
import com.codingiscaring.envmanagerbackend.models.Environment
import com.codingiscaring.envmanagerbackend.services.EnvironmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.net.http.HttpResponse

@RestController
class EnvironmentController(@Autowired val environmentService: EnvironmentService) {

    @PostMapping("/environments")
    fun createEnvironment(@RequestBody request: EnvironmentRequest): ResponseEntity<String> {
        val environment = Environment.mapFrom(request)
        var result = environmentService.create(environment)
        if (!result.isFailure) {
            return ResponseEntity("Environment created successfully", HttpStatus.CREATED)
        }
        return ResponseEntity("Environment could not be created", HttpStatus.CONFLICT)
    }
}
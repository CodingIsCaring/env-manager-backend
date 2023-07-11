package com.codingiscaring.envmanagerbackend.controllers

import com.codingiscaring.envmanagerbackend.controllers.requests.EnvironmentRequest
import com.codingiscaring.envmanagerbackend.models.Environment
import com.codingiscaring.envmanagerbackend.services.EnvironmentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class EnvironmentController(var environmentService: EnvironmentService) {

    @PostMapping("/environments")
    @ResponseStatus(HttpStatus.CREATED)
    fun createEnvironment(@RequestBody request: EnvironmentRequest) {
        val environment = Environment.mapFrom(request)
        environmentService.create(environment)
    }

}
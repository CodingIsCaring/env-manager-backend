package com.codingiscaring.envmanagerbackend.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun Any.handleRequestException(exception: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
    }
}
package com.codingiscaring.envmanagerbackend.models

import com.codingiscaring.envmanagerbackend.controllers.requests.EnvironmentRequest

data class Environment(var name: String, var description: String?) {

    companion object {
        fun mapFrom(request: EnvironmentRequest): Environment {
            return Environment(request.name, request.description)
        }
    }

}

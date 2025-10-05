package com.example

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Application.configureRouting() {
    routing {
        // Default route
        get("/") {
            call.respondText("Hello World!")
        }

        get("/test") {
            call.respondText("Test route works!")
        }

        // Use the modular creature routes
        creatureRoutes()
    }
}
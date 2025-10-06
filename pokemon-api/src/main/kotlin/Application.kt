package com.example

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*

fun main(args: Array<String>) {
    // Initialize the database
    DatabaseFactory.init()
    
    // Handle PORT environment variable for Render deployment
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    val newArgs = args.toMutableList()
    
    // Add port argument if not already specified
    if (!args.any { it.contains("port") }) {
        newArgs.add("-port=$port")
    }
    
    io.ktor.server.netty.EngineMain.main(newArgs.toTypedArray())
}

// This is the module referenced in application.yaml
fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureRouting()
}


// 1️⃣ Install JSON serialization


// 2️⃣ Configure routing


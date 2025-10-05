package com.example

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

// This is the module referenced in application.yaml
fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureRouting()
}


// 1️⃣ Install JSON serialization


// 2️⃣ Configure routing


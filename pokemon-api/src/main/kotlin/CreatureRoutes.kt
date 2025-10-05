package com.example

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*

fun Route.creatureRoutes() {

    route("/creatures") {

        get {
            call.respond(CreatureRepository.getAll())
        }

        post {
            val creature = call.receive<Creature>()
            val created = CreatureRepository.add(creature)
            call.respond(HttpStatusCode.Created, created)
        }
    }
}

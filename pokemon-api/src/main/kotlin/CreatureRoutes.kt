package com.example

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*

fun Route.creatureRoutes() {

    route("/creatures") {

        // Get all creatures
        get {
            call.respond(CreatureRepository.getAll())
        }

        // Create new creature
        post {
            val creature = call.receive<Creature>()
            val created = CreatureRepository.add(creature)
            call.respond(HttpStatusCode.Created, created)
        }
        
        // Get creature by ID
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid creature ID")
                return@get
            }
            
            val creature = CreatureRepository.findById(id)
            if (creature == null) {
                call.respond(HttpStatusCode.NotFound, "Creature not found")
            } else {
                call.respond(creature)
            }
        }
        
        // Delete creature by ID
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid creature ID")
                return@delete
            }
            
            val deleted = CreatureRepository.delete(id)
            if (deleted) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound, "Creature not found")
            }
        }
    }
}

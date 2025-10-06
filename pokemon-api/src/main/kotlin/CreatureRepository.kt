package com.example

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object CreatureRepository {
    
    fun getAll(): List<Creature> = transaction {
        Creatures.selectAll().map { row ->
            Creature(
                id = row[Creatures.id],
                name = row[Creatures.name],
                sprite = row[Creatures.sprite]
            )
        }
    }

    fun add(creature: Creature): Creature = transaction {
        val insertedId = Creatures.insert {
            it[name] = creature.name
            it[sprite] = creature.sprite
        } get Creatures.id
        
        creature.copy(id = insertedId)
    }
    
    fun findById(id: Int): Creature? = transaction {
        Creatures.select { Creatures.id eq id }
            .map { row ->
                Creature(
                    id = row[Creatures.id],
                    name = row[Creatures.name],
                    sprite = row[Creatures.sprite]
                )
            }
            .singleOrNull()
    }
    
    fun delete(id: Int): Boolean = transaction {
        Creatures.deleteWhere { Creatures.id eq id } > 0
    }
}

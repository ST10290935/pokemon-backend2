package com.example

object CreatureRepository {
    private val creatures = mutableListOf<Creature>()
    private var nextId = 1

    fun getAll(): List<Creature> = creatures.toList()

    fun add(creature: Creature): Creature {
        val newCreature = creature.copy(id = nextId++)
        creatures.add(newCreature)
        return newCreature
    }
}

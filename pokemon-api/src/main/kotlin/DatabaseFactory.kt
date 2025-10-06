package com.example

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.sqlite.JDBC"
        val jdbcURL = "jdbc:sqlite:pokemon.db"
        
        val database = Database.connect(jdbcURL, driverClassName)
        
        transaction(database) {
            SchemaUtils.create(Creatures)
        }
    }
}

// Define the Creatures table schema
object Creatures : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val sprite = integer("sprite")
    
    override val primaryKey = PrimaryKey(id)
}
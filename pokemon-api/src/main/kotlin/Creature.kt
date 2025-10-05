package com.example

import kotlinx.serialization.Serializable

@Serializable
data class Creature(
    val id: Int = 0,
    val name: String,
    val sprite: Int
)

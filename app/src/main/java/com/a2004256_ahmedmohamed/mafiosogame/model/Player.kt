package com.a2004256_ahmedmohamed.mafiosogame.model

data class Player(
    val name: String,
    var role: Role,
    val job: String,
    val location: String,
    var motive: String,
    val secret: String,
    val suspiciousInfo: String,
    var isEliminated: Boolean = false
)
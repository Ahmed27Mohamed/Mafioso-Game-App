package com.a2004256_ahmedmohamed.mafiosogame.model

data class GameCase(
    val place: String,
    val crime: String,
    val victim: String,
    val jobs: List<String>,
    val locations: List<String>,
    val motives: List<String>,
    val secrets: List<String>,
    val suspiciousInfos: List<String>,
    val clues: List<String>
)
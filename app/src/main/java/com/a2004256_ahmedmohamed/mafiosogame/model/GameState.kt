package com.a2004256_ahmedmohamed.mafiosogame.model

data class GameState(
    val players: List<Player>,
    var clues: MutableList<Clue> = mutableListOf(),
    var currentRound: Int = 1
)
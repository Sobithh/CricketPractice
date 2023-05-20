package com.mindorks.framework.taskpracticecricket.model2

data class PlayerList(
    val Name_Full: String,
    val Name_Short: String,
    val Players: HashMap<String,Player>
)
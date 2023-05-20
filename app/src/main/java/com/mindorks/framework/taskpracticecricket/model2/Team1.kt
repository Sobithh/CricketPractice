package com.mindorks.framework.taskpracticecricket.model2

data class Team1(
    val Innings: List<Inning>,
    val Matchdetail: Matchdetail,
    val Notes: Notes,
    val Nuggets: List<String>,
    val Teams: Map<String,PlayerList>
)
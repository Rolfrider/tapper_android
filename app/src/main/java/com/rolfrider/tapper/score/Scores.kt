package com.rolfrider.tapper.score

interface Scores {

    fun add(score: Score): Boolean

    fun get(): List<Score>

}
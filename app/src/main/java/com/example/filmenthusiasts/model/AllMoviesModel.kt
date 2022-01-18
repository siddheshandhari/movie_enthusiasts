package com.example.filmenthusiasts.model

class AllMoviesModel {
    var Id: Int = -1
    var Rank: Int = -1
    lateinit var Name: String
    lateinit var Duration: String
    lateinit var Description: String
    lateinit var Director: String
    lateinit var Genres: Array<String>
    lateinit var Actors: Array<String>
}
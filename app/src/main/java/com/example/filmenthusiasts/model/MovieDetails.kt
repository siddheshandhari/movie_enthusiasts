package com.example.filmenthusiasts.model

import java.io.Serializable

class MovieDetails : Serializable {
    var Id: Int = -1
    lateinit var Name: String
    lateinit var Duration: String
    lateinit var Description: String
    lateinit var Director: String
    lateinit var Genres: Array<String>
    lateinit var Actors: Array<String>
}
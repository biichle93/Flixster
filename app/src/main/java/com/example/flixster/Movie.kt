package com.example.flixster

import org.json.JSONArray

data class Movie (
    val movieID: Int,
    val overview: String,
    private val posterPath: String,
    val title: String,
){
    val posterImageURL = "https://image.tmdb.org/t/p/w342/$posterPath"
    companion object{
        fun fromJsonArray(jsonArray: JSONArray): List<Movie>{
            val movies = mutableListOf<Movie>()
            for(i in 0 until jsonArray.length()){
                val moviejson = jsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        moviejson.getInt("id"),
                        moviejson.getString("overview"),
                        moviejson.getString("poster_path"),
                        moviejson.getString("title")
                    )
                )
            }
            return movies
        }
    }
}
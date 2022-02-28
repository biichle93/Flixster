package com.example.flixster

import android.content.res.Configuration
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class Movie (
    val movieID: Int,
    val overview: String,
    val voteAverage: Double,
    private val posterPath: String,
    val title: String,
    private val posterPathL: String
): Parcelable{
    @IgnoredOnParcel
    val posterImageURL = "https://image.tmdb.org/t/p/w342/$posterPath"
    @IgnoredOnParcel
    val posterImageLURL = "https://image.tmdb.org/t/p/w342/$posterPathL"
    companion object{
        fun fromJsonArray(jsonArray: JSONArray): List<Movie>{
            val movies = mutableListOf<Movie>()
            for(i in 0 until jsonArray.length()){
                val moviejson = jsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        moviejson.getInt("id"),
                        moviejson.getString("overview"),
                        moviejson.getDouble("vote_average"),
                        moviejson.getString("poster_path"),
                        moviejson.getString("title"),
                        moviejson.getString("backdrop_path")
                    )
                )
            }
            return movies
        }
    }
}
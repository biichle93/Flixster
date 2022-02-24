package com.example.flixster

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val orientation = context.resources.configuration.orientation
        return if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            val viewL = LayoutInflater.from(context).inflate(R.layout.item_movie_landscape, parent, false)
            ViewHolder(viewL)
        }else{
            val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
            ViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        private val ivPosterL = itemView.findViewById<ImageView>(R.id.ivPosterL)
        private val tvOverviewL = itemView.findViewById<TextView>(R.id.tvOverviewL)
        private val tvTitleL = itemView.findViewById<TextView>(R.id.tvTitleL)

        fun bind(movie: Movie){
            val orientation = context.resources.configuration.orientation
            if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                tvTitleL.text = movie.title
                tvOverviewL.text = movie.overview
                Glide.with(context).load(movie.posterImageLURL).into(ivPosterL)
            }else {
                tvTitle.text = movie.title
                tvOverview.text = movie.overview
                Glide.with(context).load(movie.posterImageURL).into(ivPoster)
            }
        }
    }
    }


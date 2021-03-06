package com.example.flixster

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


const val MOVIE_EXTRA = "MOVIE_EXTRA"

class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>,
)
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitleDetail)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverviewDetail)
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val ivPosterL = itemView.findViewById<ImageView>(R.id.ivPosterL)
        private val tvOverviewL = itemView.findViewById<TextView>(R.id.tvOverviewL)
        private val tvTitleL = itemView.findViewById<TextView>(R.id.tvTitleL)

        init{
            itemView.setOnClickListener(this)
        }

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

        override fun onClick(v: View?) {
            // get notified for which movie was clicked
            val movieClicked = movies[adapterPosition]
            // use intents to launch new activity
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movieClicked)
            val p1 = androidx.core.util.Pair(tvOverview as View, "overview")
            val p2 = androidx.core.util.Pair(tvTitle as View, "title")
            val p3 = androidx.core.util.Pair(ivPoster as View, "poster")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as MainActivity, p1,p2,p3)
            context.startActivity(intent, options.toBundle())
        }
    }
    }


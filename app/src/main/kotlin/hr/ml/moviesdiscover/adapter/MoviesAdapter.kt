package hr.ml.moviesdiscover.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.ml.moviesdiscover.R
import hr.ml.moviesdiscover.listener.OnMovieSelectedListener
import hr.ml.moviesdiscover.model.Movie
import hr.ml.moviesdiscover.util.TmdbImageUrl

class MoviesAdapter(val listener: OnMovieSelectedListener)
    : ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)

        holder.movieTitle.text = movie.title
        holder.movieDate.text = movie.releaseDate
        holder.movieDescription.text = movie.overview

        if (movie.posterPath.isNotEmpty()) {
            val imageUrl = TmdbImageUrl
                    .generatePosterUrl(movie.posterPath, TmdbImageUrl.PosterWidth.W185)
            Picasso.get().load(imageUrl).into(holder.movieCover)
        }
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieCover: ImageView = itemView.findViewById(R.id.item_movie_cover)
        val movieTitle: TextView = itemView.findViewById(R.id.item_movie_title)
        val movieDate: TextView = itemView.findViewById(R.id.item_movie_date)
        val movieDescription: TextView = itemView.findViewById(R.id.item_movie_description)

        init {
            itemView.setOnClickListener {
                listener.navigateToMovieSelected(getItem(adapterPosition))
            }
        }
    }

    class MoviesDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
    }
}
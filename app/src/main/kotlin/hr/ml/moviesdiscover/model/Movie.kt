package hr.ml.moviesdiscover.model

data class Movie(val id: Int,
            val title: String,
            val originalLanguage: String,
            val releaseDate: String,
            val posterPath: String,
            val voteAverage: Float,
            val voteCount: Int,
            val overview: String)
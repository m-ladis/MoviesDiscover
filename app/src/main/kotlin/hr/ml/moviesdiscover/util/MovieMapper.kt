package hr.ml.moviesdiscover.util

import hr.ml.moviesdiscover.model.Movie
import hr.ml.moviesdiscover.rest.model.MovieFromRetrofit

class MovieMapper {

    companion object {

        fun fromMovieOverviewToMovie(moviesFromRetrofit: List<MovieFromRetrofit>?): List<Movie> {
            val movies = mutableListOf<Movie>()

            val it = moviesFromRetrofit?.iterator()
            while (it?.hasNext() == true) {
                val movieFromRetrofit = it.next()
                movies.add(Movie(movieFromRetrofit.id,
                        movieFromRetrofit.title.orEmpty(),
                        movieFromRetrofit.originalLanguage.orEmpty(),
                        movieFromRetrofit.releaseDate.orEmpty(),
                        movieFromRetrofit.posterPath.orEmpty(),
                        movieFromRetrofit.voteAverage,
                        movieFromRetrofit.voteCount,
                        movieFromRetrofit.overview.orEmpty()))
            }

            return movies
        }

    }

}
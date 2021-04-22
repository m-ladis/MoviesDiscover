package hr.ml.moviesdiscover.util

import hr.ml.moviesdiscover.model.Movie
import hr.ml.moviesdiscover.rest.model.MovieFromRetrofit

class MovieMapper {

    companion object {

        fun fromMovieOverviewToMovie(moviesFromRetrofit: List<MovieFromRetrofit>): List<Movie> {
            val movies = mutableListOf<Movie>()

            for (movieFromRetrofit in moviesFromRetrofit) {

                movies.add(Movie(movieFromRetrofit.id, movieFromRetrofit.title,
                        movieFromRetrofit.originalLanguage, movieFromRetrofit.releaseDate,
                        movieFromRetrofit.posterPath, movieFromRetrofit.voteAverage,
                        movieFromRetrofit.voteCount, movieFromRetrofit.overview))
            }

            return movies
        }

    }

}
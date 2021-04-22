package hr.ml.moviesdiscover.listener

import hr.ml.moviesdiscover.model.Movie

interface OnMovieSelectedListener {

    fun navigateToMovieSelected(movie: Movie)

}
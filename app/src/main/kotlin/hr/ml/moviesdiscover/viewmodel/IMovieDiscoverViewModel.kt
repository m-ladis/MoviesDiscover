package hr.ml.moviesdiscover.viewmodel

import hr.ml.moviesdiscover.model.Movie

interface IMovieDiscoverViewModel {

    fun fetchMovies(discoveredMovies: List<Movie>)

}
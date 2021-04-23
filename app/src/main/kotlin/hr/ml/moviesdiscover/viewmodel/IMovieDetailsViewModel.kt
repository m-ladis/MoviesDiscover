package hr.ml.moviesdiscover.viewmodel

import hr.ml.moviesdiscover.rest.model.CastFromRetrofit
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit

interface IMovieDetailsViewModel {

    fun fetchMovieDetails(movieDetails: MovieDetailsFromRetrofit)

    fun movieDetailsRequestFailed();

    fun fetchMovieCast(movieCast: List<CastFromRetrofit>)

}
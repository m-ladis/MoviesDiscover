package hr.ml.moviesdiscover.repository

interface IMovieDetailsRepository {

    fun requestMovieDetails(movieId: Int)

    fun requestMovieCast(movieId: Int)

}
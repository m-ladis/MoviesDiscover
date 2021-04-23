package hr.ml.moviesdiscover.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.ml.moviesdiscover.repository.IMovieDetailsRepository
import hr.ml.moviesdiscover.repository.MovieDetailsRepository
import hr.ml.moviesdiscover.rest.model.CastFromRetrofit
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit

private const val TAG = "MovieDetailsViewModel"

class MovieDetailsViewModel : ViewModel(), IMovieDetailsViewModel {

    val movieDetails = MutableLiveData<MovieDetailsFromRetrofit>()
    val movieCast = MutableLiveData<List<CastFromRetrofit>>()

    private val repository: IMovieDetailsRepository

    init {
        Log.d(TAG, "created")
        repository = MovieDetailsRepository(this)
    }

    fun requestMovieDetails(movieId: Int) {
        repository.requestMovieDetails(movieId)
    }

    fun requestMovieCast(movieId: Int) {
        repository.requestMovieCast(movieId)
    }

    override fun fetchMovieDetails(movieDetails: MovieDetailsFromRetrofit) {
        this.movieDetails.value = movieDetails
    }

    override fun movieDetailsRequestFailed() {
        //nothing smart, just notifying observers to change UI
        movieDetails.value = movieDetails.value
    }

    override fun fetchMovieCast(movieCast: List<CastFromRetrofit>) {
        this.movieCast.value = movieCast
    }
}
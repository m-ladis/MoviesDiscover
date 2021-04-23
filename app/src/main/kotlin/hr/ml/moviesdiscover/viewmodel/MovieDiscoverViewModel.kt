package hr.ml.moviesdiscover.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.ml.moviesdiscover.model.Movie
import hr.ml.moviesdiscover.repository.IMoviesRepository
import hr.ml.moviesdiscover.repository.MoviesRepository

private const val TAG = "MovieDiscoverViewModel"

class MovieDiscoverViewModel : ViewModel(), IMovieDiscoverViewModel {

    val movies = MutableLiveData<List<Movie>>()

    private val repository: IMoviesRepository

    init {
        Log.d(TAG, "created")
        repository = MoviesRepository(this)
        repository.requestMovies()
    }

    fun requestMovies() {
        repository.requestMovies()
    }

    override fun fetchMovies(discoveredMovies: List<Movie>) {
        movies.value = discoveredMovies
    }
}
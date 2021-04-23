package hr.ml.moviesdiscover.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.ml.moviesdiscover.model.Movie

private const val TAG = "SharedViewModel"

class SharedDataViewModel : ViewModel() {

    val selectedMovie = MutableLiveData<Movie>()

    init {
        Log.d(TAG, "created")
    }

}
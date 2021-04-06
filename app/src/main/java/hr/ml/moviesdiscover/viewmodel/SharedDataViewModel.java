package hr.ml.moviesdiscover.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hr.ml.moviesdiscover.model.Movie;

public class SharedDataViewModel extends ViewModel {
    private static final String TAG = "SharedDataViewModel";

    public MutableLiveData<Movie> selectedMovie = new MutableLiveData<>();

    public SharedDataViewModel() {
        Log.d(TAG, "SharedDataViewModel: created");
    }

    public void setSelectedMovieId(Movie movie) {
        selectedMovie.setValue(movie);
    }

}

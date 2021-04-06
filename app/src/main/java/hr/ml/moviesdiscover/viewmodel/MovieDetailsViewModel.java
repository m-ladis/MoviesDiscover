package hr.ml.moviesdiscover.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import hr.ml.moviesdiscover.repository.IMovieDetailsRepository;
import hr.ml.moviesdiscover.repository.MovieDetailsRepository;
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit;

public class MovieDetailsViewModel extends AndroidViewModel implements IMovieDetailsViewModel {
    private static final String TAG = "MovieDetailsViewModel";

    public MutableLiveData<MovieDetailsFromRetrofit> movieDetails = new MutableLiveData<>();

    private IMovieDetailsRepository repository;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);

        Log.d(TAG, "MovieDetailsViewModel: created");

        repository = new MovieDetailsRepository(this);
    }

    @Override
    public void fetchMovieDetails(MovieDetailsFromRetrofit movieDetails) {
        this.movieDetails.setValue(movieDetails);
    }

    public void requestMoviesDetails(int movieId) {
        repository.requestMovieDetails(movieId);
    }
}

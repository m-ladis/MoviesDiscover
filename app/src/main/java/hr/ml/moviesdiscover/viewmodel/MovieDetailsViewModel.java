package hr.ml.moviesdiscover.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import hr.ml.moviesdiscover.repository.IMovieDetailsRepository;
import hr.ml.moviesdiscover.repository.MovieDetailsRepository;
import hr.ml.moviesdiscover.rest.model.CastFromRetrofit;
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit;

public class MovieDetailsViewModel extends AndroidViewModel implements IMovieDetailsViewModel {
    private static final String TAG = "MovieDetailsViewModel";

    public MutableLiveData<MovieDetailsFromRetrofit> movieDetails = new MutableLiveData<>();
    public MutableLiveData<List<CastFromRetrofit>> movieCast = new MutableLiveData<>();

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

    @Override
    public void fetchMovieCast(List<CastFromRetrofit> movieCast) {
        this.movieCast.setValue(movieCast);
    }

    public void requestMoviesDetails(int movieId) {
        repository.requestMovieDetails(movieId);
    }

    public void requestMovieCast(int movieId) { repository.requestMovieCast(movieId); }
}

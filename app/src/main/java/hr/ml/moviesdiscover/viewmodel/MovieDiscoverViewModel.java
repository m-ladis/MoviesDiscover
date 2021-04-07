package hr.ml.moviesdiscover.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import hr.ml.moviesdiscover.model.Movie;
import hr.ml.moviesdiscover.repository.MoviesRepository;

public class MovieDiscoverViewModel extends AndroidViewModel implements IMovieDiscoverViewModel {
    private static final String TAG = "MovieDiscoverViewModel";

    public MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    private MoviesRepository repository;

    public MovieDiscoverViewModel(@NonNull Application application) {
        super(application);

        Log.d(TAG, "MovieDiscoverViewModel: created");

        repository = new MoviesRepository(this);
        repository.requestMovies();
    }

    @Override
    public void fetchMovies(List<Movie> discoveredMovies) {
        movies.setValue(discoveredMovies);
    }

    public void requestMovie() {
        repository.requestMovies();
    }

}

package hr.ml.moviesdiscover.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import hr.ml.moviesdiscover.model.Movie;
import hr.ml.moviesdiscover.repository.MoviesRepository;

public class MovieDiscoverViewModel extends AndroidViewModel implements IMovieDiscoverViewModel {

    public enum RequestStatus {REQUEST_OK, REQUEST_CANCELLED, REQUEST_FAILED}

    public MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    public MutableLiveData<RequestStatus> requestStatus = new MutableLiveData<>();

    public MovieDiscoverViewModel(@NonNull Application application) {
        super(application);

        MoviesRepository repository = new MoviesRepository(this);
        repository.requestMovies();
    }

    @Override
    public void fetchMovies(List<Movie> discoveredMovies) {
        movies.setValue(discoveredMovies);
        requestStatus.setValue(RequestStatus.REQUEST_OK);
    }

    @Override
    public void requestFailed() {
        requestStatus.setValue(RequestStatus.REQUEST_FAILED);
    }

    @Override
    public void requestCanceled() {
        requestStatus.setValue(RequestStatus.REQUEST_CANCELLED);
    }
}

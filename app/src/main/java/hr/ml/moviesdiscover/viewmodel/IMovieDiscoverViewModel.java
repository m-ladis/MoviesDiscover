package hr.ml.moviesdiscover.viewmodel;

import java.util.List;

import hr.ml.moviesdiscover.model.Movie;

public interface IMovieDiscoverViewModel {

    void fetchMovies(List<Movie> discoveredMovies);

}

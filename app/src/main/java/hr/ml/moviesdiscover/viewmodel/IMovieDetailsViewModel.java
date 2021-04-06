package hr.ml.moviesdiscover.viewmodel;

import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit;

public interface IMovieDetailsViewModel {

    void fetchMovieDetails(MovieDetailsFromRetrofit movieDetails);

}

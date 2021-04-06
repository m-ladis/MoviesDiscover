package hr.ml.moviesdiscover.viewmodel;

import java.util.List;

import hr.ml.moviesdiscover.rest.model.CastFromRetrofit;
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit;

public interface IMovieDetailsViewModel {

    void fetchMovieDetails(MovieDetailsFromRetrofit movieDetails);

    void fetchMovieCast(List<CastFromRetrofit> movieCast);

}

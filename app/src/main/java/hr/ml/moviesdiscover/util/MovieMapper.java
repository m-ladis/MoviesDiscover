package hr.ml.moviesdiscover.util;

import java.util.ArrayList;
import java.util.List;

import hr.ml.moviesdiscover.model.Movie;
import hr.ml.moviesdiscover.rest.model.MovieFromRetrofit;

public class MovieMapper {

    public static List<Movie> fromMovieOverviewToMovie(List<MovieFromRetrofit> moviesFromRetrofit) {
        List<Movie> movies = new ArrayList<>();

        for (MovieFromRetrofit movieFromRetrofit :
                moviesFromRetrofit) {

            movies.add(new Movie(movieFromRetrofit.getId(), movieFromRetrofit.getTitle(),
                    movieFromRetrofit.getOriginalLanguage(), movieFromRetrofit.getReleaseDate(),
                    movieFromRetrofit.getPosterPath(), movieFromRetrofit.getVoteAverage(),
                    movieFromRetrofit.getVoteCount()));

        }

        return movies;
    }

}

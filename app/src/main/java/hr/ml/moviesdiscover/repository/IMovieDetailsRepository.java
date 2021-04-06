package hr.ml.moviesdiscover.repository;

public interface IMovieDetailsRepository {

    void requestMovieDetails(int movieId);

    void requestMovieCast(int movieId);

}

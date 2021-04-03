package hr.ml.moviesdiscover.repository;

import java.util.List;

import hr.ml.moviesdiscover.BuildConfig;
import hr.ml.moviesdiscover.model.Movie;
import hr.ml.moviesdiscover.rest.TmdbApi;
import hr.ml.moviesdiscover.rest.TmdbRetrofitService;
import hr.ml.moviesdiscover.rest.model.DiscoveredMovies;
import hr.ml.moviesdiscover.rest.model.MovieFromRetrofit;
import hr.ml.moviesdiscover.util.MovieMapper;
import hr.ml.moviesdiscover.viewmodel.IMovieDiscoverViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository implements IMoviesRepository, Callback<DiscoveredMovies> {

    private TmdbApi tmdbApi;

    private IMovieDiscoverViewModel viewModel;

    public MoviesRepository(IMovieDiscoverViewModel viewModel) {
        this.viewModel = viewModel;
        tmdbApi = TmdbRetrofitService.getInstance().create(TmdbApi.class);
    }

    public void requestMovies() {
        Call<DiscoveredMovies> discoveredMoviesFromApi = tmdbApi
                .getDiscoveredMovies(BuildConfig.API_KEY, "popularity.desc",
                        false, false, 1);

        discoveredMoviesFromApi.enqueue(this);
    }

    @Override
    public void onResponse(Call<DiscoveredMovies> call, Response<DiscoveredMovies> response) {
        if (call.isExecuted()) {
            if(response.body() != null) {
                List<MovieFromRetrofit> moviesFromRetrofit = response.body().getResults();
                List<Movie> movies = MovieMapper.fromMovieOverviewToMovie(moviesFromRetrofit);
                viewModel.fetchMovies(movies);
            }
        } else if (call.isCanceled()) {
            viewModel.requestCanceled();
        }
    }

    @Override
    public void onFailure(Call<DiscoveredMovies> call, Throwable t) {
        viewModel.requestFailed();
    }
}

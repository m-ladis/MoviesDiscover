package hr.ml.moviesdiscover.repository;

import hr.ml.moviesdiscover.BuildConfig;
import hr.ml.moviesdiscover.rest.TmdbApi;
import hr.ml.moviesdiscover.rest.TmdbRetrofitService;
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit;
import hr.ml.moviesdiscover.viewmodel.IMovieDetailsViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsRepository
        implements IMovieDetailsRepository, Callback<MovieDetailsFromRetrofit> {

    private TmdbApi tmdbApi;

    private IMovieDetailsViewModel viewModel;

    public MovieDetailsRepository(IMovieDetailsViewModel viewModel) {
        this.viewModel = viewModel;
        tmdbApi = TmdbRetrofitService.getInstance().create(TmdbApi.class);
    }

    @Override
    public void requestMovieDetails(int movieId) {
        Call<MovieDetailsFromRetrofit> call = tmdbApi.getMovieDetails(movieId, BuildConfig.API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieDetailsFromRetrofit> call, Response<MovieDetailsFromRetrofit> response) {
        if (call.isExecuted()) {
            if (response.body() != null) {
                viewModel.fetchMovieDetails(response.body());
            }
        } else if (call.isCanceled()) {
            viewModel.fetchMovieDetails(null);
        }
    }

    @Override
    public void onFailure(Call<MovieDetailsFromRetrofit> call, Throwable t) {
        viewModel.fetchMovieDetails(null);
    }
}

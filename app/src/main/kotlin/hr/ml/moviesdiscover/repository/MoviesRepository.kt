package hr.ml.moviesdiscover.repository

import hr.ml.moviesdiscover.BuildConfig
import hr.ml.moviesdiscover.rest.TmdbApi
import hr.ml.moviesdiscover.rest.TmdbRetrofitService
import hr.ml.moviesdiscover.rest.model.DiscoveredMoviesFromRetrofit
import hr.ml.moviesdiscover.util.MovieMapper
import hr.ml.moviesdiscover.viewmodel.IMovieDiscoverViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository(private val viewModel: IMovieDiscoverViewModel) :
        IMoviesRepository, Callback<DiscoveredMoviesFromRetrofit> {

    private val tmdbApi: TmdbApi = TmdbRetrofitService.getInstance().create(TmdbApi::class.java)

    override fun requestMovies() {
        val restCall = tmdbApi.getDiscoveredMovies(BuildConfig.API_KEY,
                "popularity.desc", includeAdult = false, includeVideo = false, page = 1)

        restCall.enqueue(this)
    }

    override fun onResponse(call: Call<DiscoveredMoviesFromRetrofit>,
                            response: Response<DiscoveredMoviesFromRetrofit>) {

        if (response.isSuccessful) {
            val moviesFromRetrofit = response.body()?.results
            val movies = MovieMapper.fromMovieOverviewToMovie(moviesFromRetrofit)
            viewModel.fetchMovies(movies)
        } else viewModel.fetchMovies(emptyList())
    }

    override fun onFailure(call: Call<DiscoveredMoviesFromRetrofit>, t: Throwable) {
        viewModel.fetchMovies(emptyList())
    }
}
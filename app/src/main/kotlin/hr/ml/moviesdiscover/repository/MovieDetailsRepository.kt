package hr.ml.moviesdiscover.repository

import hr.ml.moviesdiscover.BuildConfig
import hr.ml.moviesdiscover.rest.TmdbApi
import hr.ml.moviesdiscover.rest.TmdbRetrofitService
import hr.ml.moviesdiscover.rest.model.MovieCreditsFromRetrofit
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit
import hr.ml.moviesdiscover.viewmodel.IMovieDetailsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsRepository(private val viewModel: IMovieDetailsViewModel)
    : IMovieDetailsRepository {

    private val tmdbApi = TmdbRetrofitService.getInstance().create(TmdbApi::class.java)

    override fun requestMovieDetails(movieId: Int) {
        val callFromRest = tmdbApi.getMovieDetails(movieId, BuildConfig.API_KEY)

        callFromRest.enqueue(object : Callback<MovieDetailsFromRetrofit> {
            override fun onResponse(call: Call<MovieDetailsFromRetrofit>,
                                    response: Response<MovieDetailsFromRetrofit>) {

                if (response.isSuccessful) response.body()?.let { viewModel.fetchMovieDetails(it) }
            }

            override fun onFailure(call: Call<MovieDetailsFromRetrofit>, t: Throwable) {
                viewModel.movieDetailsRequestFailed()
            }
        })
    }

    override fun requestMovieCast(movieId: Int) {
        val callFromRest = tmdbApi.getMovieCredits(movieId, BuildConfig.API_KEY)

        callFromRest.enqueue(object : Callback<MovieCreditsFromRetrofit> {
            override fun onResponse(call: Call<MovieCreditsFromRetrofit>,
                                    response: Response<MovieCreditsFromRetrofit>) {

                if (response.isSuccessful) {
                    val cast = response.body()?.cast
                    viewModel.fetchMovieCast(cast.orEmpty())
                }
            }

            override fun onFailure(call: Call<MovieCreditsFromRetrofit>, t: Throwable) {
                viewModel.fetchMovieCast(emptyList())
            }
        })
    }
}
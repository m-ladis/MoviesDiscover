package hr.ml.moviesdiscover.rest

import hr.ml.moviesdiscover.rest.model.DiscoveredMoviesFromRetrofit
import hr.ml.moviesdiscover.rest.model.MovieCreditsFromRetrofit
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("discover/movie")
    fun getDiscoveredMovies(@Query("api_key") API_KEY: String,
                            @Query("sort_by") sortBy: String,
                            @Query("include_adult") includeAdult: Boolean,
                            @Query("include_video") includeVideo: Boolean,
                            @Query("page") page: Int): Call<DiscoveredMoviesFromRetrofit>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") movieId: Int,
                        @Query("api_key") API_KEY: String): Call<MovieDetailsFromRetrofit>

    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") movieId: Int,
                        @Query("api_key") API_KEY: String): Call<MovieCreditsFromRetrofit>

}
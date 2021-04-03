package hr.ml.moviesdiscover.rest;

import hr.ml.moviesdiscover.rest.model.DiscoveredMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbApi {

    @GET("discover/movie")
    Call<DiscoveredMovies> getDiscoveredMovies(@Query("api_key") String API_KEY,
                                               @Query("sort_by") String sortBy,
                                               @Query("include_adult") boolean includeAdult,
                                               @Query("include_video") boolean includeVideo,
                                               @Query("page") int page);

}

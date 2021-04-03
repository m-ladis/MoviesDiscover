package hr.ml.moviesdiscover.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbRetrofitService {
    public static final String baseUrl = "https://api.themoviedb.org/3/";

    private static Retrofit instance;

    private TmdbRetrofitService() {}

    public static synchronized Retrofit getInstance() {
        if (instance != null) return instance;

        build();
        return instance;
    }

    private static void build() {
        instance = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

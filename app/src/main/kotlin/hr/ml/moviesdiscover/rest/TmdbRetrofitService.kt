package hr.ml.moviesdiscover.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TmdbRetrofitService {

    companion object {
        private const val baseUrl = "https://api.themoviedb.org/3/"

        private lateinit var retrofitInstance: Retrofit

        fun getInstance(): Retrofit {
            synchronized(this) {
                if(! this::retrofitInstance.isInitialized) build()
                return retrofitInstance
            }
        }

        private fun build() {
            retrofitInstance = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}
package hr.ml.moviesdiscover.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DiscoveredMoviesFromRetrofit(

        @SerializedName("page")
        @Expose
        val page: Int?,

        @SerializedName("results")
        @Expose
        val results: List<MovieFromRetrofit>?,

        @SerializedName("total_pages")
        @Expose
        val totalPages: Int?,

        @SerializedName("total_results")
        @Expose
        val totalResults: Int?

)

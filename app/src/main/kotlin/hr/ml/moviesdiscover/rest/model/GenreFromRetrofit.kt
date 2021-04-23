package hr.ml.moviesdiscover.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreFromRetrofit(

        @SerializedName("id")
        @Expose
        val id: Int,

        @SerializedName("name")
        @Expose
        val name: String?

)
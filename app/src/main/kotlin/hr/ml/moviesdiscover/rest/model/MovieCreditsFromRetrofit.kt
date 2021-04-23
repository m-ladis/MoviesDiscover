package hr.ml.moviesdiscover.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieCreditsFromRetrofit(

        @SerializedName("id")
        @Expose
        val id: Int,

        @SerializedName("cast")
        @Expose
        val cast: List<CastFromRetrofit>?,

        @SerializedName("crew")
        @Expose
        val crew: List<CrewFromRetrofit>?

)

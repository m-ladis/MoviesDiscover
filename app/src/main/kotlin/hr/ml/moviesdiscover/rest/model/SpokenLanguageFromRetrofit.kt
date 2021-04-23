package hr.ml.moviesdiscover.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpokenLanguageFromRetrofit(

        @SerializedName("english_name")
        @Expose
        val englishName: String?,

        @SerializedName("iso_639_1")
        @Expose
        val iso6391: String?,

        @SerializedName("name")
        @Expose
        val name: String?

)

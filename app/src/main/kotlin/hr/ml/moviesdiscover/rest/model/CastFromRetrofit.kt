package hr.ml.moviesdiscover.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CastFromRetrofit(

        @SerializedName("adult")
        @Expose
        val adult: Boolean,

        @SerializedName("gender")
        @Expose
        val gender: Int,

        @SerializedName("id")
        @Expose
        val id: Int,

        @SerializedName("known_for_department")
        @Expose
        val knownForDepartment: String?,

        @SerializedName("name")
        @Expose
        val name: String?,

        @SerializedName("original_name")
        @Expose
        val originalName: String?,

        @SerializedName("popularity")
        @Expose
        val popularity: Float,

        @SerializedName("profile_path")
        @Expose
        val profilePath: String?,

        @SerializedName("cast_id")
        @Expose
        val castId: Int,

        @SerializedName("character")
        @Expose
        val character: String?,

        @SerializedName("credit_id")
        @Expose
        val creditId: String?,

        @SerializedName("order")
        @Expose
        val order: Int

)

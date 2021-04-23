package hr.ml.moviesdiscover.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieFromRetrofit(

        @SerializedName("adult")
        @Expose
        val adult: Boolean,

        @SerializedName("backdrop_path")
        @Expose
        val backdropPath: String?,

        @SerializedName("genre_ids")
        @Expose
        val genreIds: List<Int>?,

        @SerializedName("id")
        @Expose
        val id: Int,

        @SerializedName("original_language")
        @Expose
        val originalLanguage: String?,

        @SerializedName("original_title")
        @Expose
        val originalTitle: String?,

        @SerializedName("overview")
        @Expose
        val overview: String?,

        @SerializedName("popularity")
        @Expose
        val popularity: Float,

        @SerializedName("poster_path")
        @Expose
        val posterPath: String?,

        @SerializedName("release_date")
        @Expose
        val releaseDate: String?,

        @SerializedName("title")
        @Expose
        val title: String?,

        @SerializedName("video")
        @Expose
        val video: Boolean,

        @SerializedName("vote_average")
        @Expose
        val voteAverage: Float,

        @SerializedName("vote_count")
        @Expose
        val voteCount: Int

)

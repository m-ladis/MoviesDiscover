package hr.ml.moviesdiscover.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetailsFromRetrofit(

        @SerializedName("adult")
        @Expose
        val adult: Boolean,

        @SerializedName("backdrop_path")
        @Expose
        val backdropPath: String?,

        @SerializedName("belongs_to_collection")
        @Expose
        val belongsToCollection: Any?,

        @SerializedName("budget")
        @Expose
        val budget: Int,

        @SerializedName("genres")
        @Expose
        val genres: List<GenreFromRetrofit>?,

        @SerializedName("homepage")
        @Expose
        val homepage: String?,

        @SerializedName("id")
        @Expose
        val id: Int,

        @SerializedName("imdb_id")
        @Expose
        val imdbId: String?,

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

        @SerializedName("production_companies")
        @Expose
        val productionCompanies: List<ProductionCompanyFromRetrofit>?,

        @SerializedName("production_countries")
        @Expose
        val productionCountries: List<ProductionCompanyFromRetrofit>?,

        @SerializedName("release_date")
        @Expose
        val releaseDate: String?,

        @SerializedName("revenue")
        @Expose
        val revenue: Int,

        @SerializedName("runtime")
        @Expose
        val runtime: Int,

        @SerializedName("spoken_languages")
        @Expose
        val spokenLanguages: List<SpokenLanguageFromRetrofit>?,

        @SerializedName("status")
        @Expose
        val status: String?,

        @SerializedName("tagline")
        @Expose
        val tagline: String?,

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
        val voteCount: Int,

)
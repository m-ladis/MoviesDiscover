package hr.ml.moviesdiscover.util

import hr.ml.moviesdiscover.rest.model.GenreFromRetrofit

class UiListToString {

    companion object {
        private const val separator = " | "

        fun genresToString(genres: List<GenreFromRetrofit>): String {
            val builder = StringBuilder()

            builder.append(separator)

            for (genre in genres) {
                builder.append(genre.name + ", ")
            }

            val genresOutput = builder.toString()

            // trim ", " at the end
            return genresOutput.substring(0, genresOutput.length - 2)
        }

    }

}
package hr.ml.moviesdiscover.util

class TmdbImageUrl {
    enum class PosterWidth { W92, W154, W185, W342, W500, W780, ORIGINAL }
    enum class BackdropWidth { W300, W780, W1280, ORIGINAL }
    enum class ProfileWidth { W45, W185, W632, ORIGINAL }

    companion object {
        private const val imageBaseUrl = "https://image.tmdb.org/t/p/"

        fun generatePosterUrl(path: String, width: PosterWidth): String {
            var url = imageBaseUrl

            url += when (width) {
                PosterWidth.W92 -> "w92"
                PosterWidth.W154 -> "w154"
                PosterWidth.W185 -> "w185"
                PosterWidth.W342 -> "w342"
                PosterWidth.W500 -> "w500"
                PosterWidth.W780 -> "w780"
                PosterWidth.ORIGINAL -> "original"
            }

            return url + path
        }

        fun generateBackdropUrl(path: String, width: BackdropWidth): String {
            var url = imageBaseUrl

            url += when (width) {
                BackdropWidth.W300 -> "w300"
                BackdropWidth.W780 -> "w780"
                BackdropWidth.W1280 -> "w1280"
                BackdropWidth.ORIGINAL -> "original"
            }

            return url + path
        }

        fun generateProfileUrl(path: String, width: ProfileWidth): String {
            var url = imageBaseUrl

            url += when (width) {
                ProfileWidth.W45 -> "w45"
                ProfileWidth.W185 -> "w185"
                ProfileWidth.W632 -> "w632"
                ProfileWidth.ORIGINAL -> "original"
            }

            return url + path
        }

    }

}
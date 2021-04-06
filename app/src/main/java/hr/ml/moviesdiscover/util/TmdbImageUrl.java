package hr.ml.moviesdiscover.util;

public class TmdbImageUrl {
    private static final String imageBaseUrl = "https://image.tmdb.org/t/p/";

    public enum PosterWidth {w92,w154,w185,w342,w500,w780,original}
    enum BackdropWidth {w300,w780,w1280,original}

    public static String generatePosterUrl(String path, PosterWidth width) {
        String url = imageBaseUrl;

        switch (width){
            case w92:
                url += "w92/";
                break;
            case w154:
                url += "w154/";
                break;
            case w185:
                url += "w185/";
                break;
            case w342:
                url += "w342/";
                break;
            case w500:
                url += "w500/";
                break;
            case w780:
                url += "w780/";
                break;
            case original:
                url += "original/";
                break;
        }

        return (url + path);
    }

    public static String generateBackdropUrl(String path, BackdropWidth width) {
        String url = imageBaseUrl;

        switch (width){
            case w300:
                url += "w300/";
                break;
            case w780:
                url += "w780/";
                break;
            case w1280:
                url += "w1280/";
                break;
            case original:
                url += "original/";
                break;
        }

        return (url + path);
    }

}

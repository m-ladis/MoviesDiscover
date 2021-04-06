package hr.ml.moviesdiscover.util;

import java.util.List;

import hr.ml.moviesdiscover.rest.model.GenreFromRetrofit;

public class UiListToString {

    public static String genresToString(List<GenreFromRetrofit> genres) {
        StringBuilder builder = new StringBuilder();
        builder.append(" | ");
        for (GenreFromRetrofit genre : genres) {
            builder.append(genre.getName());
            builder.append(", ");
        }
        String genresStr = builder.toString();

        return genresStr.substring(0, genresStr.length()-2);
    }

}

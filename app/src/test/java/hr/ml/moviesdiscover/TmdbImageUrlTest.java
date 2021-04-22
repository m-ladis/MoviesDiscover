package hr.ml.moviesdiscover;

import org.junit.Test;

import hr.ml.moviesdiscover.util.TmdbImageUrl;

import static org.junit.Assert.assertEquals;

public class TmdbImageUrlTest {

    String profileUrl = "https://image.tmdb.org/t/p/w45/oTB9vGIBacH5aQNS0pUM74QSWuf.jpg";
    String posterUrl = "https://image.tmdb.org/t/p/w185/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg";
    String backdropUrl = "https://image.tmdb.org/t/p/w300/jMWkd0fuwbG39eJpzycJzPWMCww.jpg";

    @Test
    public void profileImageUrl_isCorrect() {
        String url = TmdbImageUrl.Companion.generateProfileUrl(
                "/oTB9vGIBacH5aQNS0pUM74QSWuf.jpg", TmdbImageUrl.ProfileWidth.W45);
        assertEquals(profileUrl, url);
    }

    @Test
    public void posterImageUrl_isCorrect() {
        String url = TmdbImageUrl.Companion.generatePosterUrl(
                "/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg", TmdbImageUrl.PosterWidth.W185);
        assertEquals(posterUrl, url);
    }

    @Test
    public void backdropImageUrl_isCorrect() {
        String url = TmdbImageUrl.Companion.generateBackdropUrl(
                "/jMWkd0fuwbG39eJpzycJzPWMCww.jpg", TmdbImageUrl.BackdropWidth.W300);
        assertEquals(backdropUrl, url);
    }
}

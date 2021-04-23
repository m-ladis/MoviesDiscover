package hr.ml.moviesdiscover.util;


import org.junit.Assert;
import org.junit.Test;

public class MovieMapperTest {

    @Test
    public void passing_null_expecting_empty_list() {
        Assert.assertTrue(MovieMapper.Companion
                .fromMovieOverviewToMovie(null)
                .isEmpty());
    }

}
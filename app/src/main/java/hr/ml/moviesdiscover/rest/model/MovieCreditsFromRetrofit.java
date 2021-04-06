package hr.ml.moviesdiscover.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCreditsFromRetrofit {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("cast")
    @Expose
    private List<CastFromRetrofit> cast;
    @SerializedName("crew")
    @Expose
    private List<CrewFromRetrofit> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CastFromRetrofit> getCast() {
        return cast;
    }

    public void setCast(List<CastFromRetrofit> cast) {
        this.cast = cast;
    }

    public List<CrewFromRetrofit> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewFromRetrofit> crew) {
        this.crew = crew;
    }
}

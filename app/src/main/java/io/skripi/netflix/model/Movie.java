package moviesearch.netflix.model;


import com.google.gson.annotations.SerializedName;

public class Movie {

    String title;
    @SerializedName("poster_path")
    String moviePoster;
    @SerializedName("backdrop_path")
    String backdropImage;

    public String getTitle() {
        return title;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public String getBackdropImage() {
        return backdropImage;
    }
}

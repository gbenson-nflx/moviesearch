package moviesearch.netflix.model.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import moviesearch.netflix.model.Movie;

public class MovieResults {

    int page;
    List<Movie> results;

    @SerializedName("total_results")
    int totalResults;

    @SerializedName("total_pages")
    int totalPages;

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

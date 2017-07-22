package moviesearch.netflix.api;

import io.reactivex.Observable;
import moviesearch.netflix.model.network.MovieResults;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListApi {

    @GET("search/movie")
    Observable<MovieResults> getMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("page") int page);

}

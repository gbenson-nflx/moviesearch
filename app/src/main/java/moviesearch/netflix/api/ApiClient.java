package moviesearch.netflix.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import moviesearch.netflix.model.Movie;
import moviesearch.netflix.model.network.MovieResults;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String API_KEY = "6efc30f1fdcbe7425ab08503f07e2762";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w%s%s";

    private MovieListApi movieListApi;

    public ApiClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit movieListRetrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.movieListApi = movieListRetrofit.create(MovieListApi.class);
    }

    public Observable<List<Movie>> requestMoreMovies(String query, int page) {
        return movieListApi.getMovies(API_KEY, query, page)
                .subscribeOn(Schedulers.io())
                .map(new Function<MovieResults, List<Movie>>() {
                    @Override
                    public List<Movie> apply(@NonNull MovieResults movieResults) throws Exception {
                        return movieResults.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static String getImageUrl(int width, String imagePath) {
        return String.format(BASE_IMAGE_URL, width, imagePath);
    }

}

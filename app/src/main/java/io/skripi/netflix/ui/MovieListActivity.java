package moviesearch.netflix.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import moviesearch.netflix.R;
import moviesearch.netflix.api.ApiClient;
import moviesearch.netflix.model.Movie;

public class MovieListActivity extends AppCompatActivity {

    @BindView(R.id.search) EditText searchEditText;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private ApiClient apiClient;
    private MovieListAdapter movieListAdapter = new MovieListAdapter();

    private boolean isLoading = false;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        recyclerView.setAdapter(movieListAdapter);

        initThings();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (isLoading)
                    return;

                LinearLayoutManager layoutManager =
                        (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    requestMovies();
                }
            }
        });
    }

    @OnClick(R.id.search_button)
    public void onClickSearch() {
        this.page = 1;
        movieListAdapter.clearMovies();
        requestMovies();
    }

    public void requestMovies() {
        String queryString = getSearchString();
        if (queryString == null) {
            return;
        }
        isLoading = true;
        apiClient.requestMoreMovies(queryString, page).subscribe(new Consumer<List<Movie>>() {
            @Override
            public void accept(@NonNull List<Movie> movies) throws Exception {
                movieListAdapter.addMovies(movies);
                isLoading = false;
                page++;
            }
        });
    }

    private String getSearchString() {
        String queryString = searchEditText.getEditableText().toString();

        if (queryString == null || queryString.isEmpty()) {
            searchEditText.setError("Cannot be empty");
            return null;
        }

        return queryString;
    }

    private void initThings() {


        this.apiClient = new ApiClient();
    }


}

package moviesearch.netflix.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import moviesearch.netflix.model.Movie;
import moviesearch.netflix.ui.MovieListItemView.MovieListItemViewHolder;

class MovieListAdapter extends RecyclerView.Adapter {

    private List<Movie> movies;

    void clearMovies() {
        this.movies = null;
        notifyDataSetChanged();
    }

    void addMovies(List<Movie> movies) {
        if (this.movies == null) {
            this.movies = new ArrayList<>();
        }
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieListItemViewHolder(MovieListItemView.inflate(parent));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MovieListItemView item = ((MovieListItemViewHolder) holder).getItemView();

        final Movie movie = getMovie(position);
        item.bindMovie(movie);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(item.getContext(), movie.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    private Movie getMovie(int position) {
        return movies.get(position);
    }
}

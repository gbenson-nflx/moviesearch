package moviesearch.netflix.ui;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import moviesearch.netflix.model.Movie;
import moviesearch.netflix.ui.MovieListItemView.MovieListItemViewHolder;

public class MovieListAdapter extends RecyclerView.Adapter {

    private List<Movie> movies;

    public void clearMovies() {
        this.movies = null;
        notifyDataSetChanged();
    }

    public void addMovies(List<Movie> movies) {
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
        ((MovieListItemViewHolder) holder).getItemView().bindMovie(getMovie(position));
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    private Movie getMovie(int position) {
        return movies.get(position);
    }
}

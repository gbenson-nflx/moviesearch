package moviesearch.netflix.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import moviesearch.netflix.R;
import moviesearch.netflix.UiUtils;
import moviesearch.netflix.api.ApiClient;
import moviesearch.netflix.model.Movie;

public class MovieListItemView extends RelativeLayout {

    @BindView(R.id.backdrop) ImageView backdrop;
    @BindView(R.id.cover) ImageView cover;
    @BindView(R.id.title) TextView title;

    public MovieListItemView(Context context) {
        this(context, null);
    }

    public MovieListItemView(Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieListItemView(Context context,
            @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static MovieListItemView inflate(ViewGroup parent) {
        return (MovieListItemView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_movie_list_item, parent, false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindMovie(Movie movie) {
        this.title.setText(movie.getTitle());

        int width = Math.round(UiUtils.dpToPx(getResources(), 150));

        Picasso.with(getContext())
                .load(ApiClient.getImageUrl(500, movie.getMoviePoster()))
                .resize(width, width)
                .centerInside()
                .into(this.cover);

        Picasso.with(getContext())
                .load(ApiClient.getImageUrl(500, movie.getBackdropImage()))
                .resize(UiUtils.getScreenWidth(), width)
                .centerCrop()
                .into(this.backdrop);
    }

    public static class MovieListItemViewHolder extends RecyclerView.ViewHolder {

        private MovieListItemView itemView;

        public MovieListItemViewHolder(MovieListItemView itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public MovieListItemView getItemView() {
            return itemView;
        }
    }
}

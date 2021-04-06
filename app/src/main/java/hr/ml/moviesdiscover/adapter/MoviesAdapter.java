package hr.ml.moviesdiscover.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import hr.ml.moviesdiscover.R;
import hr.ml.moviesdiscover.listener.OnMovieSelectedListener;
import hr.ml.moviesdiscover.model.Movie;
import hr.ml.moviesdiscover.util.TmdbImageUrl;

public class MoviesAdapter
        extends ListAdapter<Movie, MoviesAdapter.MovieViewHolder> {

    OnMovieSelectedListener listener;

    public MoviesAdapter(OnMovieSelectedListener listener) {
        super(new MovieDiffCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movieAtPosition = getItem(position);
        if(movieAtPosition != null) {
            holder.movieTitle.setText(movieAtPosition.getTitle());

            String imageUrl = TmdbImageUrl
                    .generatePosterUrl(movieAtPosition.getPosterPath(), TmdbImageUrl.PosterWidth.w185);
            Picasso.get().load(imageUrl).into(holder.movieCover);
        }
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView movieCover;
        private final TextView movieTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieCover = itemView.findViewById(R.id.item_movie_cover);
            movieTitle = itemView.findViewById(R.id.item_movie_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.navigateToMovieSelected(getItem(getAdapterPosition()));
                }
            });
        }
    }

    private static class MovieDiffCallback extends DiffUtil.ItemCallback<Movie> {

        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {

            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getReleaseDate().equals(newItem.getReleaseDate()) &&
                    oldItem.getPosterPath().equals(newItem.getPosterPath());
        }
    }
}

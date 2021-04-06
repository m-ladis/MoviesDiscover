package hr.ml.moviesdiscover.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hr.ml.moviesdiscover.R;
import hr.ml.moviesdiscover.adapter.MovieCastAdapter;
import hr.ml.moviesdiscover.rest.model.CastFromRetrofit;
import hr.ml.moviesdiscover.util.TmdbImageUrl;
import hr.ml.moviesdiscover.viewmodel.MovieDetailsViewModel;
import hr.ml.moviesdiscover.viewmodel.SharedDataViewModel;

public class MovieDetailsFragment extends Fragment {
    private static final String TAG = "MovieDetailsFragment";

    private ImageView movieImage;
    private TextView movieTitle;
    private TextView movieYear;
    private TextView movieRating;
    private TextView movieDescription;
    private RecyclerView castRecyclerView;

    private SharedDataViewModel sharedViewModel;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedDataViewModel.class);

        MovieDetailsViewModel viewModel = new ViewModelProvider(this)
                .get(MovieDetailsViewModel.class);

        movieImage = view.findViewById(R.id.movie_image);
        movieTitle = view.findViewById(R.id.movie_title);
        movieYear = view.findViewById(R.id.movie_year);
        movieRating = view.findViewById(R.id.movie_rating);
        movieDescription = view.findViewById(R.id.movie_description);
        castRecyclerView = view.findViewById(R.id.recycler_view_cast);

        MovieCastAdapter castAdapter = new MovieCastAdapter();
        castRecyclerView.setAdapter(castAdapter);

        sharedViewModel.selectedMovie.observe(requireActivity(), movie -> {
            viewModel.requestMoviesDetails(movie.getId());
            viewModel.requestMovieCast(movie.getId());
            movieTitle.setText(String.valueOf(movie.getTitle()));
            movieYear.setText("(" + movie.getReleaseDate() + ")");
            movieRating.setText(movie.getVoteAverage() + "/10\nVotes: " + movie.getVoteCount());
            String imageUrl = TmdbImageUrl
                    .generatePosterUrl(movie.getPosterPath(), TmdbImageUrl.PosterWidth.w342);
            Picasso.get().load(imageUrl).into(movieImage);
        });

        viewModel.movieDetails.observe(getViewLifecycleOwner(), movieDetailsFromRetrofit -> {
            if(movieDetailsFromRetrofit != null) {
                movieDescription.setText(String.valueOf(movieDetailsFromRetrofit.getOverview()));
            } else Log.d(TAG, "request_failed");
        });

        viewModel.movieCast.observe(getViewLifecycleOwner(), new Observer<List<CastFromRetrofit>>() {
            @Override
            public void onChanged(List<CastFromRetrofit> castFromRetrofits) {
                if(castFromRetrofits != null) castAdapter.submitList(castFromRetrofits);
                else Log.d(TAG, "request_failed");
            }
        });
    }
}
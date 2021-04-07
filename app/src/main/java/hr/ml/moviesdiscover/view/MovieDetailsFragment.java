package hr.ml.moviesdiscover.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hr.ml.moviesdiscover.R;
import hr.ml.moviesdiscover.adapter.MovieCastAdapter;
import hr.ml.moviesdiscover.model.Movie;
import hr.ml.moviesdiscover.rest.model.CastFromRetrofit;
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit;
import hr.ml.moviesdiscover.util.TmdbImageUrl;
import hr.ml.moviesdiscover.util.UiListToString;
import hr.ml.moviesdiscover.viewmodel.MovieDetailsViewModel;
import hr.ml.moviesdiscover.viewmodel.SharedDataViewModel;

public class MovieDetailsFragment extends Fragment {
    private static final String TAG = "MovieDetailsFragment";

    private ProgressBar progressBarActors;
    private ProgressBar progressBarBoxOffice;
    private ImageView movieImage;
    private TextView movieTitle;
    private TextView movieYear;
    private TextView movieGenres;
    private TextView movieRating;
    private TextView movieDescription;
    private TextView movieRevenueLabel;
    private TextView movieRevenue;
    private TextView movieBudgetLabel;
    private TextView movieBudget;
    private RecyclerView castRecyclerView;

    private SharedDataViewModel sharedViewModel;
    private MovieDetailsViewModel viewModel;

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

        // fragment changes appbar
        setHasOptionsMenu(true);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedDataViewModel.class);

        viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        progressBarActors = view.findViewById(R.id.progress_actors);
        progressBarBoxOffice = view.findViewById(R.id.progress_box_office);
        movieImage = view.findViewById(R.id.movie_image);
        movieTitle = view.findViewById(R.id.movie_title);
        movieYear = view.findViewById(R.id.movie_year);
        movieGenres = view.findViewById(R.id.movie_genres);
        movieRating = view.findViewById(R.id.movie_rating);
        movieDescription = view.findViewById(R.id.movie_description);
        castRecyclerView = view.findViewById(R.id.recycler_view_cast);
        movieRevenueLabel = view.findViewById(R.id.movie_revenue_label);
        movieRevenue = view.findViewById(R.id.movie_revenue);
        movieBudgetLabel = view.findViewById(R.id.movie_budget_label);
        movieBudget = view.findViewById(R.id.movie_budget);

        MovieCastAdapter castAdapter = new MovieCastAdapter();
        castRecyclerView.setAdapter(castAdapter);

        sharedViewModel.selectedMovie.observe(requireActivity(), movie -> {
            updateUiWith(movie);
        });

        viewModel.movieDetails.observe(getViewLifecycleOwner(), movieDetailsFromRetrofit -> {
            progressBarBoxOffice.setVisibility(View.GONE);

            if(movieDetailsFromRetrofit != null) {
                updateUiWith(movieDetailsFromRetrofit);
            } else Log.d(TAG, "request_failed");
        });

        viewModel.movieCast.observe(getViewLifecycleOwner(), new Observer<List<CastFromRetrofit>>() {
            @Override
            public void onChanged(List<CastFromRetrofit> castFromRetrofits) {
                progressBarActors.setVisibility(View.GONE);

                if(castFromRetrofits != null) castAdapter.submitList(castFromRetrofits);
                else Log.d(TAG, "request_failed");
            }
        });
    }

    private void updateUiWith(Movie movie) {
        viewModel.requestMoviesDetails(movie.getId());
        viewModel.requestMovieCast(movie.getId());
        movieTitle.setText(String.valueOf(movie.getTitle()));
        movieYear.setText("(" + movie.getReleaseDate() + ")");
        movieRating.setText(movie.getVoteAverage() + "/10\nVotes: " + movie.getVoteCount());
        movieDescription.setText(movie.getOverview());
        String imageUrl = TmdbImageUrl
                .generatePosterUrl(movie.getPosterPath(), TmdbImageUrl.PosterWidth.w342);
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_outline_image)
                .into(movieImage);
    }

    private void updateUiWith(MovieDetailsFromRetrofit movieDetailsFromRetrofit) {
        movieGenres.setText(UiListToString.genresToString(movieDetailsFromRetrofit.getGenres()));

        if(movieDetailsFromRetrofit.getBudget() > 0) {
            movieBudgetLabel.setVisibility(View.VISIBLE);
            movieBudget.setText("$" + movieDetailsFromRetrofit.getBudget());
        }

        if(movieDetailsFromRetrofit.getRevenue() > 0) {
            movieRevenueLabel.setVisibility(View.VISIBLE);
            movieRevenue.setText("$" + movieDetailsFromRetrofit.getRevenue());
        }
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_movie:
                shareMovie();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareMovie() {
        Movie movieToShare = sharedViewModel.selectedMovie.getValue();

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Check out movie: " + movieToShare.getTitle()
                + " (" + movieToShare.getReleaseDate() + ")!";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Discover movie");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
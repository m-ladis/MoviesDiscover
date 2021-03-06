package hr.ml.moviesdiscover.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import hr.ml.moviesdiscover.R;
import hr.ml.moviesdiscover.adapter.MoviesAdapter;
import hr.ml.moviesdiscover.listener.OnMovieSelectedListener;
import hr.ml.moviesdiscover.model.Movie;
import hr.ml.moviesdiscover.viewmodel.MovieDiscoverViewModel;
import hr.ml.moviesdiscover.viewmodel.SharedDataViewModel;

public class MovieDiscoverFragment extends Fragment implements OnMovieSelectedListener {
    private static final String TAG = "MovieDiscoverFragment";

    private RecyclerView moviesRecyclerView;
    private ProgressBar progressBar;

    private SharedDataViewModel sharedViewModel;
    private MovieDiscoverViewModel viewModel;

    public MovieDiscoverFragment() {
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
        return inflater.inflate(R.layout.fragment_movie_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Referencing views
        moviesRecyclerView = view.findViewById(R.id.recycler_view_movies);
        progressBar = view.findViewById(R.id.progress_circular);

        sharedViewModel = new ViewModelProvider(requireActivity())
                .get(SharedDataViewModel.class);

        viewModel = new ViewModelProvider(this)
                .get(MovieDiscoverViewModel.class);

        MoviesAdapter adapter = new MoviesAdapter(this);
        moviesRecyclerView.setAdapter(adapter);

        viewModel.movies.observe(getViewLifecycleOwner(), movies -> {

            if(movies != null){
                progressBar.setVisibility(View.GONE);
                adapter.submitList(movies);
            } else showRequestFailedAlertDialog();
        });
    }

    @Override
    public void navigateToMovieSelected(Movie movie) {
        sharedViewModel.setSelectedMovieId(movie);

        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_movieDiscoverFragment_to_movieDetailsFragment);
    }

    private void showRequestFailedAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.failure_alert_dialog_title);
        builder.setMessage(R.string.failure_alert_dialog_message);
        builder.setPositiveButton(R.string.failure_alert_dialog_button_text, (dialog, which) -> {
            dialog.dismiss();
            viewModel.requestMovie();
        });
        builder.create().show();
    }
}
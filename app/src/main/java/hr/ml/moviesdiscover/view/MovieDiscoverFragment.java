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

import hr.ml.moviesdiscover.R;
import hr.ml.moviesdiscover.adapter.MoviesAdapter;
import hr.ml.moviesdiscover.viewmodel.MovieDiscoverViewModel;

public class MovieDiscoverFragment extends Fragment {
    private static final String TAG = "MovieDiscoverFragment";

    private RecyclerView moviesRecyclerView;

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

        MovieDiscoverViewModel viewModel = new ViewModelProvider(this)
                .get(MovieDiscoverViewModel.class);

        MoviesAdapter adapter = new MoviesAdapter();
        moviesRecyclerView.setAdapter(adapter);

        viewModel.movies.observe(getViewLifecycleOwner(), movies -> {
            adapter.submitList(movies);
        });

        viewModel.requestStatus.observe(getViewLifecycleOwner(), requestStatus -> {
            switch (requestStatus){
                case REQUEST_OK:
                    Log.d(TAG, "request_ok");
                    break;
                case REQUEST_FAILED:
                    Log.d(TAG, "request_failed");
                    break;
                case REQUEST_CANCELLED:
                    Log.d(TAG, "request_cancelled");
                    break;
            }
        });
    }
}
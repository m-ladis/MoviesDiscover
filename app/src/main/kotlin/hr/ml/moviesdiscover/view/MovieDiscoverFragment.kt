package hr.ml.moviesdiscover.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import hr.ml.moviesdiscover.R
import hr.ml.moviesdiscover.adapter.MoviesAdapter
import hr.ml.moviesdiscover.listener.OnMovieSelectedListener
import hr.ml.moviesdiscover.model.Movie
import hr.ml.moviesdiscover.viewmodel.MovieDiscoverViewModel
import hr.ml.moviesdiscover.viewmodel.SharedDataViewModel

class MovieDiscoverFragment : Fragment(), OnMovieSelectedListener {

    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var sharedViewModel: SharedDataViewModel
    private lateinit var viewModel: MovieDiscoverViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movie_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesRecyclerView = view.findViewById(R.id.recycler_view_movies)
        progressBar = view.findViewById(R.id.progress_circular)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedDataViewModel::class.java)
        viewModel = ViewModelProvider(this).get(MovieDiscoverViewModel::class.java)

        val adapter = MoviesAdapter(this)
        moviesRecyclerView.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner, { movies ->
            if (movies.isNotEmpty()) {
                progressBar.visibility = View.GONE
                adapter.submitList(movies)
            } else showRequestFailedAlertDialog()
        })
    }

    override fun navigateToMovieSelected(movie: Movie) {
        sharedViewModel.selectedMovie.value = movie

        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.action_movieDiscoverFragment_to_movieDetailsFragment)
    }

    private fun showRequestFailedAlertDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.failure_alert_dialog_title)
        builder.setMessage(R.string.failure_alert_dialog_message)
        builder.setPositiveButton(R.string.failure_alert_dialog_button_text) { dialog, _ ->
            dialog.dismiss()
            viewModel.requestMovies()
        }
        builder.create().show()
    }
}
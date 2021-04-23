package hr.ml.moviesdiscover.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.ml.moviesdiscover.R
import hr.ml.moviesdiscover.adapter.MovieCastAdapter
import hr.ml.moviesdiscover.model.Movie
import hr.ml.moviesdiscover.rest.model.MovieDetailsFromRetrofit
import hr.ml.moviesdiscover.util.TmdbImageUrl
import hr.ml.moviesdiscover.util.TmdbImageUrl.Companion.generatePosterUrl
import hr.ml.moviesdiscover.util.UiListToString
import hr.ml.moviesdiscover.viewmodel.MovieDetailsViewModel
import hr.ml.moviesdiscover.viewmodel.SharedDataViewModel

class MovieDetailsFragment : Fragment() {

    private lateinit var progressBarActors: ProgressBar
    private lateinit var progressBarBoxOffice: ProgressBar
    private lateinit var movieImage: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieYear: TextView
    private lateinit var movieGenres: TextView
    private lateinit var movieRating: TextView
    private lateinit var movieDescription: TextView
    private lateinit var movieRevenueLabel: TextView
    private lateinit var movieRevenue: TextView
    private lateinit var movieBudgetLabel: TextView
    private lateinit var movieBudget: TextView
    private lateinit var castRecyclerView: RecyclerView

    private lateinit var sharedViewModel: SharedDataViewModel
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedDataViewModel::class.java)
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        progressBarActors = view.findViewById(R.id.progress_actors)
        progressBarBoxOffice = view.findViewById(R.id.progress_box_office)
        movieImage = view.findViewById(R.id.movie_image)
        movieTitle = view.findViewById(R.id.movie_title)
        movieYear = view.findViewById(R.id.movie_year)
        movieGenres = view.findViewById(R.id.movie_genres)
        movieRating = view.findViewById(R.id.movie_rating)
        movieDescription = view.findViewById(R.id.movie_description)
        castRecyclerView = view.findViewById(R.id.recycler_view_cast)
        movieRevenueLabel = view.findViewById(R.id.movie_revenue_label)
        movieRevenue = view.findViewById(R.id.movie_revenue)
        movieBudgetLabel = view.findViewById(R.id.movie_budget_label)
        movieBudget = view.findViewById(R.id.movie_budget)

        val adapter = MovieCastAdapter()
        castRecyclerView.adapter = adapter

        sharedViewModel.selectedMovie.observe(requireActivity(), { movie ->
            updateUIWith(movie)
        })

        viewModel.movieDetails.observe(viewLifecycleOwner, {
            progressBarBoxOffice.visibility = View.GONE
            if (it != null) updateUiWithDetails(it)
        })

        viewModel.movieCast.observe(viewLifecycleOwner, {
            progressBarActors.visibility = View.GONE
            adapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_movie_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share_movie -> {
                shareMovie()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateUIWith(movie: Movie) {
        viewModel.requestMovieCast(movie.id)
        viewModel.requestMovieDetails(movie.id)
        movieTitle.text = movie.title
        movieYear.text = "(${movie.releaseDate})"
        movieRating.text = "${movie.voteAverage}/10\nVotes: ${movie.voteCount}"
        movieDescription.text = movie.overview

        val imageUrl = generatePosterUrl(movie.posterPath, TmdbImageUrl.PosterWidth.W342)
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_outline_image)
                .into(movieImage)
    }

    private fun updateUiWithDetails(movieDetailsFromRetrofit: MovieDetailsFromRetrofit) {
        movieGenres.text = UiListToString.genresToString(movieDetailsFromRetrofit.genres.orEmpty())

        if (movieDetailsFromRetrofit.budget > 0) {
            movieBudgetLabel.visibility = View.VISIBLE
            movieBudget.text = "\$${movieDetailsFromRetrofit.budget}"
        }

        if (movieDetailsFromRetrofit.revenue > 0) {
            movieRevenueLabel.visibility = View.VISIBLE
            movieRevenue.text = "\$${movieDetailsFromRetrofit.revenue}"
        }
    }

    private fun shareMovie() {
        val movieToShare = sharedViewModel.selectedMovie.value

        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody = "Check out movie: ${movieToShare?.title} (${movieToShare?.releaseDate})!"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Discover movie")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }
}
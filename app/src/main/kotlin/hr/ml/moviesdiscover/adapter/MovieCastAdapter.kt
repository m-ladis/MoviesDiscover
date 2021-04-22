package hr.ml.moviesdiscover.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.ml.moviesdiscover.R
import hr.ml.moviesdiscover.rest.model.CastFromRetrofit
import hr.ml.moviesdiscover.util.TmdbImageUrl
import hr.ml.moviesdiscover.adapter.MovieCastAdapter.*

class MovieCastAdapter :
        ListAdapter<CastFromRetrofit, MovieCastViewHolder>(MovieCastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)

        return MovieCastViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        val cast = getItem(position)
        val imageUrl = TmdbImageUrl.generateProfileUrl(
                cast.profilePath, TmdbImageUrl.ProfileWidth.W185)

        holder.castName.setText(cast.name)
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_baseline_portrait)
                .into(holder.castImage)
    }

    class MovieCastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val castImage: ImageView = itemView.findViewById(R.id.item_cast_image)
        val castName: TextView = itemView.findViewById(R.id.item_cast_name)
    }

    class MovieCastDiffCallback : DiffUtil.ItemCallback<CastFromRetrofit>() {
        override fun areItemsTheSame(oldItem: CastFromRetrofit, newItem: CastFromRetrofit) =
                oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CastFromRetrofit, newItem: CastFromRetrofit) =
                oldItem.name == newItem.name
    }
}
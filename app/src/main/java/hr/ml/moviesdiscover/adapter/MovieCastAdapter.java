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
import hr.ml.moviesdiscover.rest.model.CastFromRetrofit;
import hr.ml.moviesdiscover.util.TmdbImageUrl;

public class MovieCastAdapter extends
        ListAdapter<CastFromRetrofit, MovieCastAdapter.MovieCastViewHolder> {

    public MovieCastAdapter() {
        super(new MovieCastDiffCallback());
    }

    @NonNull
    @Override
    public MovieCastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cast, parent, false);
        return new MovieCastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCastViewHolder holder, int position) {
        CastFromRetrofit cast  = getItem(position);
        String imageUrl = TmdbImageUrl
                .generateProfileUrl(cast.getProfilePath(), TmdbImageUrl.ProfileWidth.w185);

        holder.cast_name.setText(cast.getName());
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_baseline_portrait_24)
                .into(holder.cast_image);
    }

    public static class MovieCastViewHolder extends RecyclerView.ViewHolder {

        private ImageView cast_image;
        private TextView cast_name;

        public MovieCastViewHolder(@NonNull View itemView) {
            super(itemView);

            cast_image = itemView.findViewById(R.id.item_cast_image);
            cast_name = itemView.findViewById(R.id.item_cast_name);
        }
    }

    public static class MovieCastDiffCallback extends DiffUtil.ItemCallback<CastFromRetrofit> {

        @Override
        public boolean areItemsTheSame(@NonNull CastFromRetrofit oldItem,
                                       @NonNull CastFromRetrofit newItem) {

            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CastFromRetrofit oldItem,
                                          @NonNull CastFromRetrofit newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getOriginalName().equals(newItem.getOriginalName()) &&
                    oldItem.getGender() == newItem.getGender();
        }
    }
}

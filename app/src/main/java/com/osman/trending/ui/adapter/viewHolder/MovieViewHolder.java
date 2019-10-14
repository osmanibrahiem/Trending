package com.osman.trending.ui.adapter.viewHolder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.osman.trending.R;
import com.osman.trending.databinding.ItemRowMovieBinding;
import com.osman.trending.helper.Constants;
import com.osman.trending.helper.GlideApp;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public ItemRowMovieBinding binding;

    public MovieViewHolder(ItemRowMovieBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @BindingAdapter("movieImage")
    public static void setImageUrl(AppCompatImageView view, String url) {
        if (url != null) {
//            binding.shimmerViewContainer.startShimmerAnimation();
            GlideApp.with(view.getContext())
                    .load(Constants.SMALL_IMAGE_URL + url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            binding.shimmerViewContainer.stopShimmerAnimation();
//                            binding.shimmerViewContainer.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            binding.shimmerViewContainer.stopShimmerAnimation();
//                            binding.shimmerViewContainer.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .apply(RequestOptions.option(Option.memory(Constants.GLIDE_TIMEOUT), 0))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.error_placeholder)
                    .placeholder(R.drawable.placeholder)
                    .thumbnail(0.1f)
                    .into(view);

        }
    }
}

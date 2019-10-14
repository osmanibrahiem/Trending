package com.osman.trending.ui.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.osman.trending.R;
import com.osman.trending.databinding.FragmentMovieDetailsBinding;
import com.osman.trending.helper.Constants;
import com.osman.trending.helper.GlideApp;
import com.osman.trending.ui.viewmodel.MovieDetailsViewModel;

public class MovieDetailsFragment extends Fragment {

    private MovieDetailsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMovieDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.movieToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(getActivity()).get(MovieDetailsViewModel.class);
        View view = binding.getRoot();
        viewModel.getMovie().observe(this, binding::setMovie);

        binding.movieToolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        return view;
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        if (url != null) {
            GlideApp.with(view.getContext())
                    .load(Constants.ORIGINAL_IMAGE_URL + url)
                    .apply(RequestOptions.option(Option.memory(Constants.GLIDE_TIMEOUT), 0))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.error_placeholder)
                    .placeholder(R.drawable.placeholder)
                    .thumbnail(0.1f)
                    .into(view);
        }
    }
}

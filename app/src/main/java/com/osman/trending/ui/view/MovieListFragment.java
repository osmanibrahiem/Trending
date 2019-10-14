package com.osman.trending.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osman.trending.R;
import com.osman.trending.helper.RecyclerDividerItemDecoration;
import com.osman.trending.model.Movie;
import com.osman.trending.model.NetworkState;
import com.osman.trending.ui.adapter.MoviesAdapter;
import com.osman.trending.ui.viewmodel.MovieDetailsViewModel;
import com.osman.trending.ui.viewmodel.MoviesListViewModel;

public class MovieListFragment extends Fragment {

    private static final String ARG_MEDIA_TYPE = "media_type";

    protected MoviesListViewModel viewModel;
    private MovieDetailsViewModel detailsViewModel;

    protected RecyclerView recyclerView;
    private MoviesAdapter adapter;

    public static MovieListFragment newInstance(@StringRes int mediaType) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_MEDIA_TYPE, mediaType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);
        int mediaType = R.string.tab_text_1;
        if (getArguments() != null) {
            mediaType = getArguments().getInt(ARG_MEDIA_TYPE);
        }
        viewModel.loadMedia(getString(mediaType));
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending_movies_list, container, false);
        adapter = new MoviesAdapter(getActivity());
        recyclerView = view.findViewById(R.id.moviesRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickLister(this::onMovieClick);
        observersRegisters();
        return view;
    }

    private void observersRegisters() {
        viewModel.getNetworkState().observe(this, networkState -> {
            if (adapter != null)
                if (networkState.getStatus() == NetworkState.Status.RUNNING) {
                    adapter.showLoading();
                } else {
                    adapter.hideLoading();
                }
        });

        viewModel.getMovies().observe(this, movies -> {
            if (movies != null)
                adapter.appendData(movies);
//            else adapter.clearList();
        });

        detailsViewModel = ViewModelProviders.of(getActivity()).get(MovieDetailsViewModel.class);
    }


    private void onMovieClick(Movie movie) {
        detailsViewModel.getMovie().postValue(movie);
        if (!detailsViewModel.getMovie().hasActiveObservers()) {
            MovieDetailsFragment detailsFragment = new MovieDetailsFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentsContainer, detailsFragment);
            transaction.setCustomAnimations(
                    R.anim.fragment_slide_left_enter,
                    R.anim.fragment_slide_left_exit,
                    R.anim.fragment_slide_right_enter,
                    R.anim.fragment_slide_right_exit);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
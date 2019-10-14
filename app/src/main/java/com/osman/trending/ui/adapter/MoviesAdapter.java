package com.osman.trending.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.osman.trending.R;
import com.osman.trending.databinding.ItemRowMovieBinding;
import com.osman.trending.model.Movie;
import com.osman.trending.ui.adapter.viewHolder.LoadingViewHolder;
import com.osman.trending.ui.adapter.viewHolder.MovieViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter {

    public static final int DATA_VIEW = 17;
    public static final int LOADING_VIEW = 19;

    private Context context;
    private List<Movie> dataList;
    private LayoutInflater inflater;

    private MovieClickListener listener;

    public MoviesAdapter(Context context) {
        this.context = context;
        this.dataList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<Movie> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void showLoading() {
        if (dataList.size() > 0) {
            this.dataList.add(null);
            notifyItemInserted(this.dataList.size() - 1);
        } else {
            this.dataList.add(null);
            this.dataList.add(null);
            this.dataList.add(null);
            notifyItemRangeInserted(0, dataList.size());
        }
    }

    public void appendData(List<Movie> dataList) {
        int rangeStart = this.dataList.size();
        this.dataList.addAll(dataList);
        int rangeEnd = this.dataList.size();
        notifyItemRangeInserted(rangeStart, rangeEnd);
    }

    public void hideLoading() {
        if (dataList.size() == 3 && dataList.get(0) == null && dataList.get(1) == null && dataList.get(2) == null) {
            dataList.clear();
            notifyItemRangeRemoved(0, 3);
        } else {
            if (dataList.size() > 0 && dataList.get(dataList.size() - 1) == null) {
                this.dataList.remove(dataList.size() - 1);
                notifyItemRemoved(dataList.size() - 1);
            }
        }
    }

    public void clearList() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickLister(MovieClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position) == null) {
            return LOADING_VIEW;
        }
        return DATA_VIEW;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LOADING_VIEW) {
            View view = inflater.inflate(R.layout.item_row_loading, parent, false);
            return new LoadingViewHolder(view);
        } else {
            ItemRowMovieBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_row_movie, parent, false);
            return new MovieViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (dataList.get(position) != null) {
            ((MovieViewHolder) holder).binding.setMovie(dataList.get(position));
            ((MovieViewHolder) holder).itemView.setOnClickListener(v -> {
                if (listener != null)
                    listener.onClick(dataList.get(position));
            });
        } else {
            if (((LoadingViewHolder) holder).mShimmerViewContainer != null) {
                ((LoadingViewHolder) holder).mShimmerViewContainer.startShimmerAnimation();
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

}

package com.osman.trending.ui.adapter.viewHolder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.osman.trending.R;

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ShimmerFrameLayout mShimmerViewContainer;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        mShimmerViewContainer = itemView.findViewById(R.id.shimmer_view_container);
    }
}

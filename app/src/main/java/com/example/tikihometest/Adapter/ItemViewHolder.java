package com.example.tikihometest.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tikihometest.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitle=(TextView) itemView.findViewById(R.id.tv_title);
    }
}

package com.example.tikihometest.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tikihometest.Common.Common;
import com.example.tikihometest.ProcessData;
import com.example.tikihometest.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mData;

    public ItemAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
    }

    public void setData(List<String> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.recycler_item_layout, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {

        itemViewHolder.txtTitle.setText(ProcessData.formatData(mData.get(i)));
        itemViewHolder.txtTitle.setBackgroundColor(Color.parseColor(getRandomColor(i)));
    }


    private String getRandomColor(int position){
        return Common.COLORS[position % 8].getValue();
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }


}

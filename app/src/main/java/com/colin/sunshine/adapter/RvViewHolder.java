package com.colin.sunshine.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.colin.sunshine.R;


class RvViewHolder extends RecyclerView.ViewHolder {
////    private SparseArray<View> mViews;
////    private View mConvertView;
//
////    private RvViewHolder(View itemView) {
////        super(itemView);
////        mConvertView = itemView;
////        mViews = new SparseArray<>();
////    }
//
    public RvViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.moyu_list_item, parent, false));
    }

//    public static RvViewHolder create(Context context, int layoutId, ViewGroup parent) {
//        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
//        return new RvViewHolder(itemView);
//    }

//    public static ViewHolder create(View itemView) {
//        return new ViewHolder(itemView);
//    }



}
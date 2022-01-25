package com.colin.sunshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.sunshine.R;
import com.colin.sunshine.model.MoyuListBean;

import java.util.List;

/**
 * Date:2022-01-25
 * Time:10:23
 * author:colin
 * 加载摸鱼模块的列表中九宫格图片
 */
public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ImgViewHolder> {

    private Context mContext;
    private List<String> images;

    public ImgAdapter(Context mContext, List<String> images) {
        this.mContext = mContext;
        this.images = images;
    }


    @NonNull
    @Override
    public ImgViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.moyu_list_item_img, null);
        ImgViewHolder holder = new ImgViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImgViewHolder holder, int position) {

        Glide.with(mContext)
                .load(images.get(position))
                // 将头像剪裁成圆形
//                .circleCrop()
                .error(R.mipmap.ic_default_avatar)
                // 不使用内存缓存头像
                .skipMemoryCache(true)
                // 不使用磁盘缓存头像
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.simple_grid_rvlayout_img);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ImgViewHolder extends RecyclerView.ViewHolder{


        private ImageView simple_grid_rvlayout_img;

        public ImgViewHolder(View itemView) {
            super(itemView);
            simple_grid_rvlayout_img = itemView.findViewById(R.id.simple_grid_rvlayout_img);


        }
    }
}


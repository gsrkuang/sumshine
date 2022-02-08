package com.colin.sunshine.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.colin.sunshine.ui.activity.ImagePreviewActivity;

import java.util.ArrayList;
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
        int tag = position;
        Glide.with(mContext)
                .load(images.get(position))
                // 将头像剪裁成圆形 ,图片已经剪成了圆形，所以下面不需要重新处理了
//                .circleCrop()
                .error(R.mipmap.ic_default_avatar)
                // 不使用内存缓存头像
                .skipMemoryCache(true)
                // 不使用磁盘缓存头像
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.simple_grid_rvlayout_img);

        holder.simple_grid_rvlayout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //打开图片查看器，并传入images,还有告诉图片查看器显示第几张图片
//                Intent intent = new Intent(mContext, ImagePreviewActivity.class);
//                Bundle bundle = new Bundle();
                ArrayList<String> arrayList = new ArrayList<String>();

                for (int i =0 ; i <images.size();i++){
                    arrayList.add(images.get(i));
                }
//
//                bundle.putStringArrayList("images",arrayList);
//                bundle.putInt("index",tag);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);


//                PictureConfig config = new PictureConfig.Builder()
//                        .setListData(arrayList)	//图片数据List<String> list
//                        .setPosition(tag)	//图片下标（从第position张图片开始浏览）
//                        .setDownloadPath("pictureviewer")	//图片下载文件夹地址
//                        .setIsShowNumber(true)//是否显示数字下标
//                        .needDownload(true)	//是否支持图片下载
//                        .setPlacrHolder(R.mipmap.ic_launcher)	//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
//                        .build();
//
//                ImagePagerActivity.startActivity(mContext,config);

            }
        });

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


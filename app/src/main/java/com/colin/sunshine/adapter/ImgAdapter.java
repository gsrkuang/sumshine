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

import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.bean.ImageInfo;

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

                ImageInfo imageInfo;
                final List<ImageInfo> imageInfoList = new ArrayList<>();
                for (String image : images) {


                    imageInfo = new ImageInfo();
                    imageInfo.setOriginUrl(image);// 原图url
                    imageInfo.setThumbnailUrl(image);// 缩略图url
                    imageInfoList.add(imageInfo);
                }

                // 最简单的调用，即可实现大部分需求，如需定制，可参考下一步的自定义代码：

                ImagePreview
                        .getInstance()
                        // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                        .setContext(mContext)

                        // 设置从第几张开始看（索引从0开始）
                        .setIndex(tag)

                        //=================================================================================================
                        // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                        // 1：第一步生成的imageInfo List
                        .setImageInfoList(imageInfoList)

                        // 2：直接传url List
                        //.setImageList(List<String> imageList)

                        // 3：只有一张图片的情况，可以直接传入这张图片的url
                        //.setImage(String image)
                        //=================================================================================================

                        // 开启预览
                        .start();

                // 默认配置为：
                //      显示顶部进度指示器、
                //      显示右侧下载按钮、
                //      隐藏左侧关闭按钮、
                //      开启点击图片关闭、
                //      关闭下拉图片关闭、
                //      加载方式为手动模式
                //      加载原图的百分比在底部
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


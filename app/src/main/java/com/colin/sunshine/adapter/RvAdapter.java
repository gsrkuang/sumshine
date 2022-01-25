package com.colin.sunshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.sunshine.R;
import com.colin.sunshine.model.MoyuListBean;

import java.util.List;


public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private Context mContext;
    private List<MoyuListBean> mDatas;

    public RvAdapter(Context context, List<MoyuListBean> datas){
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.moyu_list_item, null);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_moyu_item_nick_name.setText(mDatas.get(position).nickname);
        holder.tv_moyu_item_content.setText(mDatas.get(position).content);
        holder.tv_moyu_item_desc.setText("来自" +mDatas.get(position).company + "  " + mDatas.get(position).createTime);

        Glide.with(mContext)
                .load(mDatas.get(position).avatar)
                // 将头像剪裁成圆形
                .circleCrop()
                .error(R.mipmap.ic_default_avatar)
                // 不使用内存缓存头像
                .skipMemoryCache(true)
                // 不使用磁盘缓存头像
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.iv_moyu_item_avatar);

        ImgAdapter imgAdapter = new ImgAdapter(mContext,mDatas.get(position).images);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
        //给RecyclerView设置布局管理器
        holder.simple_grid_rvlayout.setLayoutManager(gridLayoutManager);
        //创建适配器，并且设置
        holder.simple_grid_rvlayout.setAdapter(imgAdapter);

        if (mDatas.get(position).topicName == null){
//            holder.tv_moyu_topic_label_layout.setVisibility(View.INVISIBLE);
            holder.tv_moyu_topic_label.setVisibility(View.GONE);
        }else {

            holder.tv_moyu_topic_label.setText(mDatas.get(position).topicName);
        }

        if (mDatas.get(position).commentCount != 0){
            holder.tv_moyu_comment.setText(mDatas.get(position).commentCount + "");
        }
        if (mDatas.get(position).thumbUpCount != 0){
            holder.tv_moyu_great.setText(mDatas.get(position).thumbUpCount + "");
        }

//        holder.iv_moyu_share.setOnClickListener();

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_moyu_item_nick_name;//昵称

        private TextView tv_moyu_item_desc;//描述

        private TextView tv_moyu_item_content;//内容
        private TextView tv_moyu_topic_label;//内容标签
        private TextView tv_moyu_topic_label_layout;//内容标签

        private RecyclerView simple_grid_rvlayout;//九宫格图片
        private ImageView iv_moyu_item_avatar;//头像


        private TextView tv_moyu_comment;//评论
        private TextView tv_moyu_great;//点赞
        private ImageView iv_moyu_share;//转发

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_moyu_item_nick_name = itemView.findViewById(R.id.tv_moyu_item_nick_name);

            tv_moyu_item_desc = itemView.findViewById(R.id.tv_moyu_item_desc);

            tv_moyu_item_content = itemView.findViewById(R.id.tv_moyu_item_content);

            simple_grid_rvlayout = itemView.findViewById(R.id.simple_grid_rvlayout);
            iv_moyu_item_avatar = itemView.findViewById(R.id.iv_moyu_item_avatar);
            tv_moyu_topic_label = itemView.findViewById(R.id.tv_moyu_topic_label);

            tv_moyu_comment = itemView.findViewById(R.id.tv_moyu_comment);
            tv_moyu_great = itemView.findViewById(R.id.tv_moyu_great);
            iv_moyu_share = itemView.findViewById(R.id.iv_moyu_share);



        }
    }
}

package com.colin.sunshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.sunshine.R;
import com.colin.sunshine.model.MoyuListBean;
import com.colin.sunshine.ui.view.CircleImageView;
import com.colin.sunshine.viewholder.FooterViewHolder;

import java.util.List;


public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MoyuListBean> mDatas;

    private static final int TYPE_ITEM =0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //顶部FootView

    public RvAdapter(Context context,List<MoyuListBean> datas){
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == 0) {
            //你的item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.moyu_list_item, viewGroup, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        } else {

            //底部“加载更多”item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_footview, viewGroup, false);

            FooterViewHolder footerViewHolder = new FooterViewHolder(view);
            return footerViewHolder;
        }

//        View view = LayoutInflater.from(mContext).inflate(R.layout.moyu_list_item, null);
//        MyViewHolder holder = new MyViewHolder(view);
//        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof MyViewHolder){
            MyViewHolder holder = (MyViewHolder) viewHolder;
//            holder.itemView.setBackgroundColor(Color.YELLOW);

            holder.tv_moyu_item_nick_name.setText(mDatas.get(position).nickname);
            //设置vip昵称颜色
            if (mDatas.get(position).vip){
                //设置用户头像VIP
                holder.iv_moyu_item_avatar.setVip(true);
                //设置用户昵称VIP
                holder.tv_moyu_item_nick_name.setTextColor(mContext.getResources().getColor(R.color.pink));
            }else {
                holder.iv_moyu_item_avatar.setVip(false);
                holder.tv_moyu_item_nick_name.setTextColor(mContext.getResources().getColor(R.color.black));
            }

//            holder.tv_moyu_item_content.setText(mDatas.get(position).content);

            holder.tv_moyu_item_content.setText(HtmlCompat.fromHtml(mDatas.get(position).content,HtmlCompat.FROM_HTML_MODE_LEGACY));

            holder.tv_moyu_item_desc.setText("来自" +mDatas.get(position).company + " @" +mDatas.get(position).position +" " +mDatas.get(position).createTime);

            holder.tv_moyu_item_content.setTextIsSelectable(true); //设置可选

            Glide.with(holder.itemView)
                    .load(mDatas.get(position).avatar)
                    // 将头像剪裁成圆形
//                .circleCrop()
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

        }else if (viewHolder instanceof FooterViewHolder){

            FooterViewHolder holder = (FooterViewHolder) viewHolder;
        }

//
    }




    @Override
    public int getItemCount() {
//        int count = 0;
//        for (int i = 0 ; i < moyuBeans.size();i++){
//            count = count + moyuBeans.get(i).list.size();
//        }
//        return count;
        return mDatas.size() +1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() -1){
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;

    }

    public void addRefreshDataToBean(List<MoyuListBean> newDatas) {
        mDatas.addAll(newDatas);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_moyu_item_nick_name;//昵称

        private TextView tv_moyu_item_desc;//描述

        private TextView tv_moyu_item_content;//内容
        private TextView tv_moyu_topic_label;//内容标签
//        private TextView tv_moyu_topic_label_layout;//内容标签

        private RecyclerView simple_grid_rvlayout;//九宫格图片
        private CircleImageView iv_moyu_item_avatar;//头像


        private TextView tv_moyu_comment;//评论
        private TextView tv_moyu_great;//点赞
        private ImageView iv_moyu_share;//转发

        private FrameLayout fl_avatar_container;
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

            fl_avatar_container = itemView.findViewById(R.id.fl_avatar_container);




        }
    }


}

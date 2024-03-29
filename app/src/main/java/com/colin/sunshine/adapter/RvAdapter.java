package com.colin.sunshine.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.sunshine.R;
import com.colin.sunshine.adapter.viewholder.MoyuViewHolder;
import com.colin.sunshine.model.MoyuListBean;
import com.colin.sunshine.ui.activity.MoyuDetailActivity;
import com.colin.sunshine.adapter.viewholder.FooterViewHolder;

import java.util.List;


public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MoyuListBean> mDatas;

    private static final int TYPE_ITEM =0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView

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
            MoyuViewHolder viewHolder = new MoyuViewHolder(view);
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

        int position_tag = position;

        if (viewHolder instanceof MoyuViewHolder){
            MoyuViewHolder holder = (MoyuViewHolder) viewHolder;
//            holder.itemView.setBackgroundColor(Color.YELLOW);

            holder.tv_moyu_item_nick_name.setText(mDatas.get(position).nickname);
            //设置vip昵称颜色
            if (mDatas.get(position).vip){
                //设置用户头像VIP
                holder.iv_moyu_item_avatar.setVip(true);
                //设置用户昵称VIP
                holder.tv_moyu_item_nick_name.setTextColor(mContext.getResources().getColor(R.color.colorVip));
            }else {
                holder.iv_moyu_item_avatar.setVip(false);
                holder.tv_moyu_item_nick_name.setTextColor(mContext.getResources().getColor(R.color.black));
            }

//            holder.tv_moyu_item_content.setText(mDatas.get(position).content);

            String textStr = mDatas.get(position).content;


            holder.tv_moyu_item_content.setText(Html.fromHtml(textStr));

            holder.tv_moyu_item_desc.setText("来自" +mDatas.get(position).company + " @" +mDatas.get(position).position +" " +mDatas.get(position).createTime);

//            holder.tv_moyu_item_content.setTextIsSelectable(true); //设置可选

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


//            holder.tv_moyu_item_content.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //跳转到摸鱼详情页Activity
//                    Intent intent = new Intent(mContext, MoyuDetailActivity.class);
//                    intent.putExtra("MoyuListBean",mDatas.get(position_tag));
//                    mContext.startActivity(intent);
//                }
//            });

            holder.ll_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到摸鱼详情页Activity
                    Intent intent = new Intent(mContext, MoyuDetailActivity.class);
                    intent.putExtra("MoyuListBean",mDatas.get(position_tag));
                    mContext.startActivity(intent);
                }
            });
            holder.simple_grid_rvlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到摸鱼详情页Activity
                    Intent intent = new Intent(mContext, MoyuDetailActivity.class);
                    intent.putExtra("MoyuListBean",mDatas.get(position_tag));
                    mContext.startActivity(intent);
                }
            });
            holder.simple_grid_rvlayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        holder.simple_grid_rvlayout.performClick();//模拟父控件的点击

                    }
                    return false;

                }
            });

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




}

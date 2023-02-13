package com.colin.sunshine.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.sunshine.R;
import com.colin.sunshine.adapter.viewholder.MoyuDetailCommentViewHolder;
import com.colin.sunshine.adapter.viewholder.MoyuViewHolder;
import com.colin.sunshine.model.MoyuDetailBean;
import com.colin.sunshine.model.MoyuListBean;
import com.colin.sunshine.ui.activity.MoyuDetailCommentActivity;

import java.util.List;

/**
 * Date:2022-02-10
 * Time:14:29
 * author:colin
 */
public class MoyuDetailRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TOP =0;  //顶部 VIEW
    private static final int TYPE_ITEM = 1;  //评论 View

    private Context mContext;
    private MoyuListBean beanData;

    private List<MoyuDetailBean.MoyuDetailListBean> mDatas;

    public MoyuDetailRvAdapter(Context mContext,MoyuListBean beanData,List<MoyuDetailBean.MoyuDetailListBean> mDatas){
        this.mContext = mContext;
        this.beanData = beanData;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == 0){
            //加载顶部布局，详情模块
            //你的item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.moyu_list_item, viewGroup, false);
            MoyuViewHolder viewHolder = new MoyuViewHolder(view);

            return viewHolder;

        }else {
            //加载底部布局，评论模块

            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.moyu_detail_comment_item, viewGroup, false);

            MoyuDetailCommentViewHolder moyuDetailCommentViewHolder = new MoyuDetailCommentViewHolder(view);
            return moyuDetailCommentViewHolder;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        int position_tag = position;

        if (viewHolder instanceof MoyuViewHolder){
            MoyuViewHolder holder = (MoyuViewHolder) viewHolder;
//            holder.itemView.setBackgroundColor(Color.YELLOW);

            holder.tv_moyu_item_nick_name.setText(beanData.nickname);
            //设置vip昵称颜色
            if (beanData.vip){
                //设置用户头像VIP
                holder.iv_moyu_item_avatar.setVip(true);
                //设置用户昵称VIP
                holder.tv_moyu_item_nick_name.setTextColor(mContext.getResources().getColor(R.color.pink));
            }else {
                holder.iv_moyu_item_avatar.setVip(false);
                holder.tv_moyu_item_nick_name.setTextColor(mContext.getResources().getColor(R.color.black));
            }

//            holder.tv_moyu_item_content.setText(mDatas.get(position).content);

            holder.tv_moyu_item_content.setText(HtmlCompat.fromHtml(beanData.content,HtmlCompat.FROM_HTML_MODE_LEGACY));

            holder.tv_moyu_item_desc.setText("来自" +beanData.company + " @" +beanData.position +" " +beanData.createTime);

            holder.tv_moyu_item_content.setTextIsSelectable(true); //设置可选

            holder.tv_moyu_item_content.setMaxLines(100);
            Glide.with(holder.itemView)
                    .load(beanData.avatar)
                    // 将头像剪裁成圆形
//                .circleCrop()
                    .error(R.mipmap.ic_default_avatar)
                    // 不使用内存缓存头像
                    .skipMemoryCache(true)
                    // 不使用磁盘缓存头像
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.iv_moyu_item_avatar);

            ImgAdapter imgAdapter = new ImgAdapter(mContext,beanData.images);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
            //给RecyclerView设置布局管理器
            holder.simple_grid_rvlayout.setLayoutManager(gridLayoutManager);
            //创建适配器，并且设置
            holder.simple_grid_rvlayout.setAdapter(imgAdapter);

            if (beanData.topicName == null){
                holder.tv_moyu_topic_label.setVisibility(View.GONE);
            }else {
                holder.tv_moyu_topic_label.setText(beanData.topicName);
            }

            if (beanData.commentCount != 0){
                holder.tv_moyu_comment.setText(beanData.commentCount + "");
            }
            if (beanData.thumbUpCount != 0){
                holder.tv_moyu_great.setText(beanData.thumbUpCount + "");
            }


            holder.tv_moyu_item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到摸鱼详情页Activity
//                    Intent intent = new Intent(mContext, MoyuDetailActivity.class);
//                    intent.putExtra("MoyuListBean",mDatas.get(position_tag));
//                    mContext.startActivity(intent);
                }
            });


        }else if (viewHolder instanceof MoyuDetailCommentViewHolder){
            //---------------设置detailBean.

            int postionComment = position -1;
            MoyuDetailCommentViewHolder holder = (MoyuDetailCommentViewHolder) viewHolder;
            Glide.with(holder.itemView)
                    .load(mDatas.get(postionComment).avatar)
                    // 将头像剪裁成圆形
//                .circleCrop()
                    .error(R.mipmap.ic_default_avatar)
                    // 不使用内存缓存头像
                    .skipMemoryCache(true)
                    // 不使用磁盘缓存头像
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.iv_moyu_detail_avatar);

            holder.cb_iv_moyu_detail_nick_name.setText(mDatas.get(postionComment).nickname);
            if (mDatas.get(postionComment).vip){
                holder.cb_iv_moyu_detail_nick_name.setTextColor(mContext.getResources().getColor(R.color.colorVip));
                holder.iv_moyu_detail_avatar.setVip(true);
            }


            holder.tv_moyu_detail_desc.setText(mDatas.get(postionComment).company + " " + mDatas.get(postionComment).createTime);
            holder.tv_reply_msg.setText(mDatas.get(postionComment).content);

            int CommentsSize = mDatas.get(postionComment).subComments.size();

            if (CommentsSize == 0){
                holder.tv_build_reply_msg_container.setVisibility(View.GONE);
            }

            if (CommentsSize > 0){
                if (CommentsSize == 1){
                    holder.tv_child_reply_msg1.setVisibility(View.GONE);
                    holder.tv_child_reply_msg_all.setVisibility(View.GONE);
                }
                String textStr = "<font color=\"blue\">" +
                        mDatas.get(postionComment).subComments.get(0).nickname +  "</font>"  +
                        " 回复 " +
                        "<font color=\"blue\">" +
                        mDatas.get(postionComment).subComments.get(0).targetUserNickname +  "</font>"+
                        " : "+
                        mDatas.get(postionComment).subComments.get(0).content;//文字只能单独拿出来 不能直接写入Html.fromHtml()中,不然会达不到变色的效果

                holder.tv_child_reply_msg.setText(Html.fromHtml(textStr));
            }
            if (CommentsSize > 1){
                if (CommentsSize == 2){
                    holder.tv_child_reply_msg_all.setVisibility(View.GONE);
                }

                String textStr = "<font color=\"blue\">" +
                        mDatas.get(postionComment).subComments.get(1).nickname +  "</font>"  +
                        " 回复 " +
                        "<font color=\"blue\">" +
                        mDatas.get(postionComment).subComments.get(1).targetUserNickname +  "</font>"+
                        " : "+
                        mDatas.get(postionComment).subComments.get(1).content;//文字只能单独拿出来 不能直接写入Html.fromHtml()中,不然会达不到变色的效果


                holder.tv_child_reply_msg1.setText(Html.fromHtml(textStr));


            }

            if (CommentsSize > 2){

                holder.tv_child_reply_msg_all.setText("查看全部" +mDatas.get(postionComment).subComments.size()+ "条回复");
//                holder.tv_child_reply_msg_all.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //跳转到所有评论页面
//
//                    }
//                });
            }

            holder.cl_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到所有评论界面
                    Intent intent = new Intent(mContext, MoyuDetailCommentActivity.class);
                    MoyuDetailBean.MoyuDetailListBean moyuDetailListBean = mDatas.get(postionComment);
                    intent.putExtra("MoyuDetailListBean",moyuDetailListBean);
                    mContext.startActivity(intent);
                }
            });




        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    //用于根据不同的item而使用不同的布局
    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_TOP;
        }else {
            return TYPE_ITEM;
        }
    }
}

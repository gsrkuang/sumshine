package com.colin.sunshine.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.sunshine.R;
import com.colin.sunshine.adapter.viewholder.MoyuDetailCommentViewHolder;
import com.colin.sunshine.adapter.viewholder.MoyuViewHolder;
import com.colin.sunshine.model.MoyuDetailBean;
import com.colin.sunshine.ui.activity.MoyuDetailCommentActivity;

/**
 * Date:2022-02-15
 * Time:17:39
 * author:colin
 * 查看评论所有评论的adapter
 */
public class MoyuDetailCommentRvAdapter extends RecyclerView.Adapter {

    private int ITEM_TYPE_TOP = 0;
    private int ITEM_TYPE_NROMAL = 1;
    private MoyuDetailBean.MoyuDetailListBean moyuDetailListBean;
    private Context mContext;
    public MoyuDetailCommentRvAdapter(Context mContext , MoyuDetailBean.MoyuDetailListBean moyuDetailListBean){
        this.mContext = mContext;
        this.moyuDetailListBean = moyuDetailListBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_TYPE_TOP){
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.moyu_detail_comment_item, viewGroup, false);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(0,0,0,100);

            MoyuDetailCommentViewHolder moyuDetailCommentViewHolder = new MoyuDetailCommentViewHolder(view);
            return moyuDetailCommentViewHolder;

        }else{

            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.moyu_detail_comment_item, viewGroup, false);

            MoyuDetailCommentViewHolder moyuDetailCommentViewHolder = new MoyuDetailCommentViewHolder(view);
            return moyuDetailCommentViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (position == 0) {

            MoyuDetailCommentViewHolder holder = (MoyuDetailCommentViewHolder) viewHolder;
            Glide.with(holder.itemView)
                    .load(moyuDetailListBean.avatar)
                    // 将头像剪裁成圆形
//                .circleCrop()
                    .error(R.mipmap.ic_default_avatar)
                    // 不使用内存缓存头像
                    .skipMemoryCache(true)
                    // 不使用磁盘缓存头像
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.iv_moyu_detail_avatar);

            holder.cb_iv_moyu_detail_nick_name.setText(moyuDetailListBean.nickname);
            if (moyuDetailListBean.vip) {
                holder.cb_iv_moyu_detail_nick_name.setTextColor(mContext.getResources().getColor(R.color.pink));
            }

            holder.tv_moyu_detail_desc.setText(moyuDetailListBean.company + " " + moyuDetailListBean.createTime);
            holder.tv_reply_msg.setText(moyuDetailListBean.content);

            holder.tv_build_reply_msg_container.setVisibility(View.GONE);
//            int CommentsSize = moyuDetailListBean.subComments.size();
//
//            if (CommentsSize == 0){
//                holder.tv_build_reply_msg_container.setVisibility(View.GONE);
//            }
//
//            if (CommentsSize > 0){
//                if (CommentsSize == 1){
//                    holder.tv_child_reply_msg1.setVisibility(View.GONE);
//                    holder.tv_child_reply_msg_all.setVisibility(View.GONE);
//                }
//                String textStr = "<font color=\"blue\">" +
//                        mDatas.get(postionComment).subComments.get(0).nickname +  "</font>"  +
//                        " 回复 " +
//                        "<font color=\"blue\">" +
//                        mDatas.get(postionComment).subComments.get(0).targetUserNickname +  "</font>"+
//                        " : "+
//                        mDatas.get(postionComment).subComments.get(0).content;//文字只能单独拿出来 不能直接写入Html.fromHtml()中,不然会达不到变色的效果
//
//                holder.tv_child_reply_msg.setText(Html.fromHtml(textStr));
//            }
//
//            if (CommentsSize > 1){
//                if (CommentsSize == 2){
//                    holder.tv_child_reply_msg_all.setVisibility(View.GONE);
//                }
//
//                String textStr = "<font color=\"blue\">" +
//                        mDatas.get(postionComment).subComments.get(1).nickname +  "</font>"  +
//                        " 回复 " +
//                        "<font color=\"blue\">" +
//                        mDatas.get(postionComment).subComments.get(1).targetUserNickname +  "</font>"+
//                        " : "+
//                        mDatas.get(postionComment).subComments.get(1).content;//文字只能单独拿出来 不能直接写入Html.fromHtml()中,不然会达不到变色的效果
//
//
//                holder.tv_child_reply_msg1.setText(Html.fromHtml(textStr));
//
//
//            }
//
//            if (CommentsSize > 2){
//
//                holder.tv_child_reply_msg_all.setText("查看全部" +mDatas.get(postionComment).subComments.size()+ "条回复");
////                holder.tv_child_reply_msg_all.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        //跳转到所有评论页面
////
////                    }
////                });
//            }

//            holder.cl_container.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //跳转到所有评论界面
//                    Intent intent = new Intent(mContext, MoyuDetailCommentActivity.class);
//                    intent.putExtra("MoyuDetailListBean",mDatas.get(postionComment));
//                    mContext.startActivity(intent);
//                }
//            });
        }
        else {


            int subCommentPosition = position - 1;
            MoyuDetailBean.MoyuDetailListBean.MoyuDetailListSubCommentsBean subCommentsBean = moyuDetailListBean.subComments.get(subCommentPosition);
            MoyuDetailCommentViewHolder holder = (MoyuDetailCommentViewHolder) viewHolder;
            Glide.with(holder.itemView)
                    .load(subCommentsBean.avatar)
                    // 将头像剪裁成圆形
//                .circleCrop()
                    .error(R.mipmap.ic_default_avatar)
                    // 不使用内存缓存头像
                    .skipMemoryCache(true)
                    // 不使用磁盘缓存头像
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.iv_moyu_detail_avatar);

            holder.cb_iv_moyu_detail_nick_name.setText(subCommentsBean.nickname);
            if (subCommentsBean.vip) {
                holder.cb_iv_moyu_detail_nick_name.setTextColor(mContext.getResources().getColor(R.color.pink));
            }

            holder.tv_moyu_detail_desc.setText(subCommentsBean.company + " " + subCommentsBean.createTime);


            holder.tv_build_reply_msg_container.setVisibility(View.GONE);
            //隐藏第二评论，这个界面用不到
//            holder.tv_child_reply_msg1.setVisibility(View.GONE);
            //隐藏查看所有评论，这个界面用不到
//            holder.tv_child_reply_msg_all.setVisibility(View.GONE);

            String textStr = " 回复 " +"<font color=\"blue\">" +
                    subCommentsBean.targetUserNickname +  "</font>"  +
                    " : "+
                    subCommentsBean.content;//文字只能单独拿出来 不能直接写入Html.fromHtml()中,不然会达不到变色的效果

            holder.tv_reply_msg.setText(Html.fromHtml(textStr));

        }
    }

    @Override
    public int getItemCount() {
        return moyuDetailListBean.subComments.size() +1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ITEM_TYPE_TOP;
        }else {
            return ITEM_TYPE_NROMAL;
        }
    }
}

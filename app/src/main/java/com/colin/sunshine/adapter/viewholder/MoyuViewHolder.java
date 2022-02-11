package com.colin.sunshine.adapter.viewholder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.colin.sunshine.R;
import com.colin.sunshine.ui.view.CircleImageView;

/**
 * Date:2022-02-10
 * Time:14:46
 * author:colin
 * desc 摸鱼模块，贴子的ViewHolder ，用于首页和摸鱼详情
 */

public class MoyuViewHolder extends RecyclerView.ViewHolder{

    public TextView tv_moyu_item_nick_name;//昵称

    public TextView tv_moyu_item_desc;//描述

    public TextView tv_moyu_item_content;//内容
    public TextView tv_moyu_topic_label;//内容标签
//        private TextView tv_moyu_topic_label_layout;//内容标签

    public RecyclerView simple_grid_rvlayout;//九宫格图片
    public CircleImageView iv_moyu_item_avatar;//头像


    public TextView tv_moyu_comment;//评论
    public TextView tv_moyu_great;//点赞
    public ImageView iv_moyu_share;//转发

    public FrameLayout fl_avatar_container;
    public MoyuViewHolder(View itemView) {
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

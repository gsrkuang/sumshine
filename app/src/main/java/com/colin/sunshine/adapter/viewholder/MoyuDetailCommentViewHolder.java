package com.colin.sunshine.adapter.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.sunshine.R;

import org.w3c.dom.Text;

/**
 * Date:2022-02-11
 * Time:17:28
 * author:colin
 */
public class MoyuDetailCommentViewHolder extends RecyclerView.ViewHolder {

    //评论用户头像
    public ImageView iv_moyu_detail_avatar;
    //评论用户昵称
    public CheckBox cb_iv_moyu_detail_nick_name;
    //评论用户描述
    public TextView tv_moyu_detail_desc;
    //用户评论的消息
    public TextView tv_reply_msg;

    //楼主 回复 评论用户 ：
    public TextView tv_child_reply_msg;
    //评论用户 回复 楼主 ：
    public TextView tv_child_reply_msg1;
    //查看所有评论
    public TextView tv_child_reply_msg_all;

    public LinearLayout tv_build_reply_msg_container;

    public MoyuDetailCommentViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_moyu_detail_avatar = itemView.findViewById(R.id.iv_moyu_detail_avatar);
        cb_iv_moyu_detail_nick_name = itemView.findViewById(R.id.cb_iv_moyu_detail_nick_name);
        tv_moyu_detail_desc = itemView.findViewById(R.id.tv_moyu_detail_desc);
        tv_reply_msg = itemView.findViewById(R.id.tv_reply_msg);
        tv_child_reply_msg = itemView.findViewById(R.id.tv_child_reply_msg);
        tv_child_reply_msg1 = itemView.findViewById(R.id.tv_child_reply_msg1);
        tv_child_reply_msg_all = itemView.findViewById(R.id.tv_child_reply_msg_all);
        tv_build_reply_msg_container = itemView.findViewById(R.id.tv_build_reply_msg_container);




    }
}

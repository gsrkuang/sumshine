package com.colin.sunshine.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Date:2022-02-10
 * Time:17:04
 * author:colin
 * desc:摸鱼详情评论Bean
 */
public class MoyuDetailBean implements Serializable{

    @SerializedName("currentPage")
    public int currentPage;
    @SerializedName("total")
    public int total;
    @SerializedName("pageSize")
    public int pageSize;
    @SerializedName("hasNext")
    public boolean hasNext;
    @SerializedName("hasPre")
    public boolean hasPre;
    @SerializedName("totalPage")
    public int totalPage;
    @SerializedName("list")
    public List<MoyuDetailBean.MoyuDetailListBean> list;


    @Override
    public String toString() {
        return "MoyuDetailBean{" +
                "currentPage=" + currentPage +
                ", total=" + total +
                ", pageSize=" + pageSize +
                ", hasNext=" + hasNext +
                ", hasPre=" + hasPre +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }

    public static class MoyuDetailListBean implements Serializable {

        @SerializedName("id")
        public String id;
        @SerializedName("userId")
        public String userId;

        @SerializedName("company")
        public String company;

        @SerializedName("position")
        public String position;

        @SerializedName("avatar")
        public String avatar;

        @SerializedName("nickname")
        public String nickname;

        @SerializedName("createTime")
        public String createTime;

        @SerializedName("content")
        public String content;

        public @SerializedName("thumbUpList")
        List thumbUpList;

        @SerializedName("thumbUp")
        public int thumbUp;

        @SerializedName("momentId")
        public String momentId;

        @SerializedName("subComments")
        public List<MoyuDetailListSubCommentsBean> subComments;

        @SerializedName("vip")
        public boolean vip = false;

        @Override
        public String toString() {
            return "MoyuDetailListBean{" +
                    "id='" + id + '\'' +
                    ", userId='" + userId + '\'' +
                    ", company='" + company + '\'' +
                    ", position='" + position + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", content='" + content + '\'' +
                    ", thumbUpList=" + thumbUpList +
                    ", thumbUp=" + thumbUp +
                    ", momentId='" + momentId + '\'' +
                    ", subComments=" + subComments +
                    ", vip=" + vip +
                    '}';
        }

        public static class MoyuDetailListSubCommentsBean implements Serializable {

            @SerializedName("id")
            public String id;

            @SerializedName("userId")
            public String userId;

            @SerializedName("avatar")
            public String avatar;

            @SerializedName("position")
            public String position;

            @SerializedName("company")
            public String company;

            @SerializedName("nickname")
            public String nickname;

            @SerializedName("targetUserId")
            public String targetUserId;

            @SerializedName("targetUserNickname")
            public String targetUserNickname;

            @SerializedName("targetUserIsVip")
            public boolean targetUserIsVip;

            @SerializedName("createTime")
            public String createTime;

            @SerializedName("content")
            public String content;

            @SerializedName("thumbUpList")
            public List thumbUpList;

            @SerializedName("commentId")
            public String commentId;

            @SerializedName("vip")
            public boolean vip = false;


        }
    }


}

package com.colin.sunshine.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoyuListBean {
    @SerializedName("id")
    public String id;
    @SerializedName("userId")
    public String userId;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("company")
    public String company;
    @SerializedName("position")
    public String position;
    @SerializedName("content")
    public String content;
    @SerializedName("linkCover")
    public String linkCover;
    @SerializedName("linkTitle")
    public String linkTitle;
    @SerializedName("linkUrl")
    public String linkUrl;
    @SerializedName("commentCount")
    public int commentCount;
    @SerializedName("thumbUpCount")
    public int thumbUpCount;
    @SerializedName("images")
    public List<String> images;
    @SerializedName("topicName")
    public String topicName;
    @SerializedName("topicId")
    public String topicId;
    @SerializedName("createTime")
    public String createTime;
    @SerializedName("hasThumbUp")
    public boolean hasThumbUp;
    @SerializedName("thumbUpList")
    public List<String> thumbUpList;
    @SerializedName("vip")
    public String vip;

    @Override
    public String toString() {
        return "MoyuListBean{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", content='" + content + '\'' +
                ", linkCover='" + linkCover + '\'' +
                ", linkTitle='" + linkTitle + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", commentCount=" + commentCount +
                ", thumbUpCount=" + thumbUpCount +
                ", images=" + images +
                ", topicName='" + topicName + '\'' +
                ", topicId='" + topicId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", hasThumbUp=" + hasThumbUp +
                ", thumbUpList=" + thumbUpList +
                ", vip='" + vip + '\'' +
                '}';
    }
}



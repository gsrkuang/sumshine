package com.colin.sunshine.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoyuBean {

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
    public List<MoyuListBean> list;

    @Override
    public String toString() {
        return "MoyuBean{" +
                "currentPage=" + currentPage +
                ", total=" + total +
                ", pageSize=" + pageSize +
                ", hasNext=" + hasNext +
                ", hasPre=" + hasPre +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}



package com.colin.sunshine;

/**
 * Date:2021-12-01
 * Time:16:54
 * author:colin
 */
public class Constants {
    //API访问路径
    public static String api_main = "https://api.sunofbeach.net/shop";

    //获取发现页的分类
    public static String api_discovery = api_main + "/discovery/categories";


    //根据发现页分类ID获取分类内容 接口：/discovery/{materialId}/{page}
    public static String api_materialId(String materialId ,String page){
        return api_main + "/discovery?materialId=" + materialId +"&page=" + page;

    }

}

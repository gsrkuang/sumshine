package com.colin.sunshine;

/**
 * Date:2021-12-01
 * Time:16:54
 * author:colin
 */
public class Constants {
    //API访问路径
    public static String api_main = "https://api.sunofbeach.net";
    //图灵验证码
    public static String api_yzm = api_main + "/uc/ut/captcha?code=随机数";
    //登录 请求方式：Post query参数：captcha 前面图片验证码内容
    public static String api_login = api_main + "/uc/user/login/";

}

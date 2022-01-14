package com.colin.sunshine.utils;

import android.os.Handler;
import android.os.Looper;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Date:2021-12-06
 * Time:15:08
 * author:colin
 */
public class OkHttpHelper {
    /**
     * 采取单例模式应用OkHttpClient
     */
    private static OkHttpHelper mOkHttpHelperInstance;
    private static OkHttpClient mClientInstance;
    private Handler mHandler;

//    private Gson mGson;

    /**
     * 单例模式，私有构造函数，构造函数琅绫擎进行一些初始化
     */
    private OkHttpHelper() {
        mClientInstance.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mClientInstance.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        mClientInstance.newBuilder().writeTimeout(30, TimeUnit.SECONDS);


//        mGson = new Gson();

        mHandler = new Handler(Looper.getMainLooper());
    }


    /**
     * 获取实例
     *
     * @return
     */
    public static OkHttpHelper getinstance() {
        if (mOkHttpHelperInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (mOkHttpHelperInstance == null) {
                    mOkHttpHelperInstance = new OkHttpHelper();
                }
            }
        }
        return mOkHttpHelperInstance;
    }


    /**
     * 封装一个request办法，不管post或者get办法中都邑用到
     */
    public void request(final Request request, final BaseCallback callback) {
        //在请求之前所做的事，比如弹出对话框等
//        callback.onRequestBefore();


        mClientInstance.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbackFailure(request, callback, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {                    //返回成功回调
                    String resString = response.body().string();
                    if (callback.mType == String.class) {                        //如不雅我们须要返回String类型
                        callbackSuccess(response, resString, callback);
                    } else {                        //如不雅返回的是其他类型，则应用Gson去解析

//                        try {
//                            Object o = mGson.fromJson(resString, callback.mType);
//                            callbackSuccess(response, o, callback);
//                        } catch (JsonParseException e) {
//                            e.printStackTrace();
//                            callbackError(response, callback, e);
//                        }
                    }

                } else {                    //返回缺点
                    callbackError(response, callback, null);
                }
            }


        });
    }


    /**
     * 在主线程中履行的回调
     *
     * @param response
     * @param callback
     */
    private void callbackSuccess(final Response response, final Object o, final BaseCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response, o);
            }
        });
    }

    /**
     * 在主线程中履行的回调
     *
     * @param response
     * @param callback
     * @param e
     */
    private void callbackError(final Response response, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, response.code(), e);
            }
        });
    }

    /**
     * 在主线程中履行的回调
     *
     * @param request
     * @param callback
     * @param e
     */
    private void callbackFailure(final Request request, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(request, e);
            }
        });
    }

    /**
     * 对外公开的get办法
     *
     * @param url
     * @param callback
     */
    public void get(String url, BaseCallback callback) {
        Request request = buildRequest(url, null, HttpMethodType.GET);
        request(request, callback);
    }

    /**
     * 对外公开的post办法
     * * @param url
     * * @param params
     * * @param callback
     */
    public void post(String url, Map<String, String> params, BaseCallback callback) {
        Request request = buildRequest(url, params, HttpMethodType.POST);
        request(request, callback);
    }

    /**
     * 构建请求对象
     *
     * @param url
     * @param params
     * @param type
     * @return
     */
    private Request buildRequest(String url, Map<String, String> params, HttpMethodType type) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (type == HttpMethodType.GET) {
            builder.get();
        } else if (type == HttpMethodType.POST) {
            builder.post(buildRequestBody(params));
        }
        return builder.build();
    }

    /**
     * 经由过程Map的键值对构建请求对象的body
     *
     * @param params
     * @return
     */
    private RequestBody buildRequestBody(Map<String, String> params) {

//        FormEncodingBuilder builder = new FormEncodingBuilder();
//        if (params != null) {
//            for (Map.Entry<String, String> entity : params.entrySet()) {
//                builder.add(entity.getKey(), entity.getValue());
//            }
//        }
//        return builder.build();

        return null;
    }

    /**
     * 这个列举用于指明是哪一种提交方法
     */
    enum HttpMethodType {
        GET,
        POST
    }

 }

package com.colin.sunshine.utils;

/**
 * Date:2021-12-06
 * Time:15:22
 * author:colin
 */


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 根本的回调
 */
public abstract class BaseCallback<T> {
    /**
     * type用于便利JSON的解析
     */
    public Type mType;

    /**
     * 把type转换查对应的类，这里不消看明白也行。
     *
     * @param subclass
     * @return
     */
//    static Type getSuperclassTypeParameter(Class<?> subclass) {
//        Type superclass = subclass.getGenericSuperclass();
//        if (superclass instanceof Class) {
//            throw new RuntimeException("Missing type parameter.");
//        }
//        ParameterizedType parameterized = (ParameterizedType) superclass;
//        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
//
//    }

    /**
     * 构造的时刻获得type的class
     */
    public BaseCallback() {
//        mType = getSuperclassTypeParameter(getClass());

    }

    /**
     * 请求之前调用
     */
    public abstract void onRequestBefore();

    /**
     * 请求掉败调用（收集问题）
     *
     * @param request
     * @param e
     */
    public abstract void onFailure(Request request, Exception e);

    /**
     * 请求成功并且没出缺点的时刻调用
     *
     * @param response
     * @param t
     */
    public abstract void onSuccess(Response response, T t);

    /**
     * 请求成功然则出缺点的时刻调用，例如Gson解析缺点等
     *
     * @param response
     * @param errorCode
     * @param e
     */
    public abstract void onError(Response response, int errorCode, Exception e);

}
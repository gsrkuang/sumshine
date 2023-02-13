package com.colin.sunshine.model

/**
 * author : colin
 * desc   : 网络请求的数据基类
 */
interface IApiResponse<T> {

    fun getCode(): Int

    fun isSuccess(): Boolean

    fun getMessage(): String

    fun getData(): T
}
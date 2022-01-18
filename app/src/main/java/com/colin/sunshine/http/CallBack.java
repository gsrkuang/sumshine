package com.colin.sunshine.http;

public interface CallBack {
    void onSuccess(String s);
    void onFailed(Exception e);
}

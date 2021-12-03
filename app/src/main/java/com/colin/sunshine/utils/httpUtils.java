package com.colin.sunshine.utils;

import android.util.Log;

import com.colin.sunshine.Constants;
import com.colin.sunshine.model.CategoriesBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Date:2021-12-01
 * Time:17:00
 * author:colin
 */
public class httpUtils {

    public static String TAG = "httpUtils";

    public static void post(String url,String requestBodyString){
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");

        String requestBody = requestBodyString;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, requestBody))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("httpUtils", response.protocol() + " " +response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    public static void get(String url ,String value){

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("onResponse:",response.message());
                //该方法的返回值是response，所以我们可以通过response拿到相关信息。
                String string = response.body().string();//想拿到字符串，可以从response-body-string
                /*String a="111";*/
                Log.e("string = " , string);
                /*L.e(a);
                 */
            }
        });

    }


    //获取发现页的的分类标签

    public static void getDiscovery(){

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request=new Request.Builder()
                .get()
                .url(Constants.api_discovery)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("onResponse:",response.message());
                //该方法的返回值是response，所以我们可以通过response拿到相关信息。
                String string = response.body().string();//想拿到字符串，可以从response-body-string
                /*String a="111";*/
                Log.e("string = " , string);
                /*L.e(a);
                 */

                //json解析
                try {
                    JSONObject jsonObject = new JSONObject(string);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    CategoriesBean categoriesBean = new CategoriesBean();
                    categoriesBean.setCode(jsonObject.getInt("code"));
                    categoriesBean.setMessage(jsonObject.getString("message"));
                    categoriesBean.setSuccess(jsonObject.getBoolean("success"));

                    List<CategoriesBean.DataBean> list = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        CategoriesBean.DataBean dataBean = new CategoriesBean.DataBean();
                        dataBean.setId(jsonObject1.getInt("id"));
                        dataBean.setTitle(jsonObject1.getString("title"));
                        list.add(dataBean);
                    }

                    categoriesBean.setData(list);



                    System.out.println("categoriesBean = " + categoriesBean);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}

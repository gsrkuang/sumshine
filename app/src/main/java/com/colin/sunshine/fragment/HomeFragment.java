package com.colin.sunshine.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.sunshine.Constants;
import com.colin.sunshine.R;
import com.colin.sunshine.adapter.RvAdapter;
import com.colin.sunshine.http.CallBack;
import com.colin.sunshine.model.MoyuBean;
import com.colin.sunshine.utils.HttpUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Date:2021-12-02
 * Time:19:47
 * author:colin
 * 首页Fragment
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    MoyuBean moyuBean;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
        initData();
        return view;

    }


    public void initView(View view){
        recyclerView = view.findViewById(R.id.rv_moyu_detail_comment);
    }

    public void initData(){
        HttpUtils.getInstance().get(Constants.api_moyu_recommend("1"), new CallBack() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s) {
                Log.e("onSuccess = " , s);
                setDataToBean(s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        RvAdapter rvAdapter = new RvAdapter(getContext(),moyuBean.list);

                        //创建线性布局
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                        //垂直方向
                        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
                        //给RecyclerView设置布局管理器
                        recyclerView.setLayoutManager(mLayoutManager);
                        //创建适配器，并且设置
                        recyclerView.setAdapter(rvAdapter);

                    }
                });


            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }

    public void setDataToBean(String s){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            Gson gson = new Gson();
            moyuBean = gson.fromJson(jsonObject1.toString(), MoyuBean.class);
            System.out.println("json->obj:" + moyuBean.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

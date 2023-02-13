package com.colin.sunshine.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.colin.sunshine.Constants;
import com.colin.sunshine.R;
import com.colin.sunshine.adapter.RvAdapter;
import com.colin.sunshine.adapter.listener.EndlessRecyclerOnScrollListener;
import com.colin.sunshine.http.CallBack;
import com.colin.sunshine.model.MoyuBean;
import com.colin.sunshine.model.MoyuListBean;
import com.colin.sunshine.utils.HttpUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2021-12-02
 * Time:19:47
 * author:colin
 * 首页Fragment
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MoyuBean moyuBean;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tv_title;
    private int page = 1;
    private int loadingTag = 0; //1代表正在加载中

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        getHttpData();
        return view;

    }


    public void initView(View view) {
        recyclerView = view.findViewById(R.id.rv_moyu_detail);
        swipeRefreshLayout = view.findViewById(R.id.sl_moyu_refreshlayout);
        tv_title = view.findViewById(R.id.tv_title);

    }

    public void initData() {
        tv_title.setText(R.string.title_home);

        swipeRefreshLayout.setColorSchemeResources(R.color.pink);
//        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.pink));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        page = 1;
                        getHttpData();
                    }
                });
            }
        });


    }

    public void getHttpData() {
        HttpUtils.getInstance().get(Constants.api_moyu_recommend(page + ""), new CallBack() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String s) {
                Log.e("onSuccess = ", s);
                setDataToBean(s);

                //xuyao genggai
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        RvAdapter rvAdapter = new RvAdapter(getContext(), moyuBean.list);

                        //创建线性布局
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                        //垂直方向
                        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
                        //给RecyclerView设置布局管理器
                        recyclerView.setLayoutManager(mLayoutManager);
                        //创建适配器，并且设置
                        recyclerView.setAdapter(rvAdapter);

                        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
                            @Override
                            public void onLoadMore() {
                                if (loadingTag == 0){
                                    loadingTag = 1;
                                    page = page +1;
                                    HttpUtils.getInstance().get(Constants.api_moyu_recommend(page +""), new CallBack() {
                                        @Override
                                        public void onSuccess(String s) {
                                            loadingTag = 0;
                                            MoyuBean moyuBean = setDataToBean(s);

                                            //runOnUiThread 这里要改成Handler的
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    rvAdapter.addRefreshDataToBean(moyuBean.list);
                                                }
                                            });

//                                            RvAdapter rvAdapter = new RvAdapter(getContext(), moyuBeanList);
//
//                                            //创建适配器，并且设置
//                                            recyclerView.adapter
//                                            recyclerView.setAdapter(rvAdapter);
//                                            rvAdapter.addRefreshDataToBean();
//                                            rvAdapter.notifyDataSetChanged();
//                                            Toast.makeText(getContext(),"加载更多中",Toast.LENGTH_LONG).show();

                                        }

                                        @Override
                                        public void onFailed(Exception e) {

                                        }
                                    });
                                }


                            }
                        });

                    }
                });

            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    public MoyuBean setDataToBean(String s) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            Gson gson = new Gson();
            moyuBean = gson.fromJson(jsonObject1.toString(), MoyuBean.class);
            System.out.println("json->obj:" + moyuBean.toString());

            return moyuBean;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public void addRefreshDataToBean(MoyuBean bean){
////        moyuBeanList.add(bean);
//    }
}

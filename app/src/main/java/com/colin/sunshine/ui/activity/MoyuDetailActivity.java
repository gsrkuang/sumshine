package com.colin.sunshine.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.colin.sunshine.Constants;
import com.colin.sunshine.R;
import com.colin.sunshine.adapter.MoyuDetailRvAdapter;
import com.colin.sunshine.adapter.RvAdapter;
import com.colin.sunshine.adapter.listener.EndlessRecyclerOnScrollListener;
import com.colin.sunshine.http.CallBack;
import com.colin.sunshine.model.MoyuBean;
import com.colin.sunshine.model.MoyuDetailBean;
import com.colin.sunshine.model.MoyuListBean;
import com.colin.sunshine.utils.HttpUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2022-02-09
 * Time:10:23
 * author:colin
 * 摸鱼模块详情界面
 */
public class MoyuDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tv_title;

    //帖子内容bean
    private MoyuListBean moyuListBean;
    //摸鱼详情bean
    private MoyuDetailBean moyuDetailBean;

    private ImageView title_bar_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moyu_detail);

        //使得状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //1
        initView();
        //2 获取intent来的id数据，id用于查找api得到评论数据
        initIntentData();
        //3
        getHttpData();
        //4
        initListener();

    }

    private void initView() {

        recyclerView = findViewById(R.id.rv_moyu_detail_comment);
        swipeRefreshLayout = findViewById(R.id.sl_moyu_refreshlayout);
        tv_title = findViewById(R.id.tv_title);
        title_bar_back = findViewById(R.id.title_bar_back);

        initSetText();
    }
    private void initSetText(){

        tv_title.setText(R.string.title_detail);
    }

    private void initIntentData() {
        moyuListBean = (MoyuListBean) getIntent().getSerializableExtra("MoyuListBean");
    }

    private void getHttpData() {
        int page = 0;
        HttpUtils.getInstance().get(Constants.api_moyu_detail_comment(moyuListBean.id, page + ""), new CallBack() {
            @Override
            public void onSuccess(String s) {
                Log.e("+++onSuccess = ", s);

                setDataToBean(s);
                //需要在主线程中更新UI
                handler.sendEmptyMessage(0);


            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            setAdapterData();


        }
    };

    @SuppressLint("WrongConstant")
    private void setAdapterData() {
        List<MoyuListBean> list = new ArrayList<>();
        list.add(moyuListBean);

//        RvAdapter rvAdapter = new RvAdapter(this ,list);
        MoyuDetailRvAdapter moyuDetailRvAdapter = new MoyuDetailRvAdapter(this,moyuListBean,moyuDetailBean.list);
        //创建线性布局
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(moyuDetailRvAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                //滑动到底部时候执行


            }
        });

        stopRefresh();
    }

    public void initListener(){
        swipeRefreshLayout.setColorSchemeResources(R.color.pink);
//        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.pink));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHttpData();
            }
        });


        title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public MoyuDetailBean setDataToBean(String s) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            Gson gson = new Gson();
            moyuDetailBean = gson.fromJson(jsonObject1.toString(), MoyuDetailBean.class);

//            System.out.println("json->obj:" + moyuDetailBean.toString());
//            System.out.println("+++json->obj:" +moyuDetailBean.list.get(0).toString());

            return moyuDetailBean;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void stopRefresh(){
        swipeRefreshLayout.setRefreshing(false);
    }
}
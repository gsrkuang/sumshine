package com.colin.sunshine.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.colin.sunshine.R;
import com.colin.sunshine.adapter.MoyuDetailCommentRvAdapter;
import com.colin.sunshine.adapter.MoyuDetailRvAdapter;
import com.colin.sunshine.adapter.listener.EndlessRecyclerOnScrollListener;
import com.colin.sunshine.model.MoyuDetailBean;
import com.colin.sunshine.model.MoyuListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2022-02-15
 * Time:15:39
 * author:colin
 * 摸鱼模块评论,所有评论详情界面
 */
public class MoyuDetailCommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tv_title;

    private ImageView title_bar_back;

    private MoyuDetailBean.MoyuDetailListBean moyuDetailListBean ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moyu_detail_comment);
        //使得状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //1
        initView();
        //2 获取intent来的评论数据
        initIntentData();
        //3
        setAdapterData();
        //4
        initListener();

    }

    private void initView() {

        recyclerView = findViewById(R.id.rv_moyu_detail_comment_full);
        tv_title = findViewById(R.id.tv_title);
        title_bar_back = findViewById(R.id.title_bar_back);

        initSetText();
    }

    private void initSetText(){
        tv_title.setText(R.string.title_detail_comment);
    }

    private void initIntentData(){
        moyuDetailListBean = (MoyuDetailBean.MoyuDetailListBean) getIntent().getSerializableExtra("MoyuDetailListBean");
    }
    private void initListener(){
        title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @SuppressLint("WrongConstant")
    private void setAdapterData() {
//        List<MoyuListBean> list = new ArrayList<>();
//        list.add(moyuListBean);

//        RvAdapter rvAdapter = new RvAdapter(this ,list);
//        MoyuDetailRvAdapter moyuDetailRvAdapter = new MoyuDetailRvAdapter(this,moyuListBean,moyuDetailBean.list);

        MoyuDetailCommentRvAdapter moyuDetailCommentRvAdapter = new MoyuDetailCommentRvAdapter(this,moyuDetailListBean);
        //创建线性布局
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(moyuDetailCommentRvAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                //滑动到底部时候执行


            }
        });

    }
}
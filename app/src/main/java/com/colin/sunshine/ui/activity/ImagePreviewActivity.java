package com.colin.sunshine.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.colin.sunshine.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片查看器Activity
 * 2022.2.8
 */
public class ImagePreviewActivity extends AppCompatActivity {
    public List<String> images;
    public int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);


        initView();
        getIntentData();
        loadImg();
//        initFragment();
//        initListener();

    }

    public void initView() {

    }


    public void getIntentData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
//        images = bundle.getString("images");
//        index = bundle.getInt("index");

        Log.e("++++tag",index + "");
    }

    public void loadImg(){
        //这是你的数据
//        List<String> list = new ArrayList<>();
//        //网络图片
//        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
//        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgdmpxi7erj20qy0qyjtr.jpg");
//        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
//        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg");
//        //本地SD卡图片文件
//        list.add("/storage/emulated/0/DCIM/Camera/IMG_20170126_113019.jpg");
//        list.add("/storage/emulated/0/DCIM/Camera/IMG_20170126_113014.jpg");
//        list.add("/storage/emulated/0/DCIM/Camera/IMG_20170126_114018.jpg");
//        list.add("/storage/emulated/0/DCIM/Camera/IMG_20171031_152627.jpg");


//        PictureConfig
//        //使用方式
//        PictureConfig config = new PictureConfig.Builder()
//                .setListData((ArrayList<String>) list)	//图片数据List<String> list
//                .setPosition(0)	//图片下标（从第position张图片开始浏览）
//                .setDownloadPath("pictureviewer")	//图片下载文件夹地址
//                .setIsShowNumber(true)//是否显示数字下标
//                .needDownload(true)	//是否支持图片下载
//                .setPlacrHolder(R.mipmap.icon)	//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
//                .build();
//        ImagePagerActivity.startActivity(MainActivity.this, config);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}
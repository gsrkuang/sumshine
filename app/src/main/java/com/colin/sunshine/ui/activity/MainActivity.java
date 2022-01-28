package com.colin.sunshine.ui.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.colin.sunshine.R;
import com.colin.sunshine.fragment.ArticleFragment;
import com.colin.sunshine.fragment.HomeFragment;
import com.colin.sunshine.fragment.MyFragment;
import com.colin.sunshine.fragment.WallPaperFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private HomeFragment mHomeFragment;
    private WallPaperFragment mWallPaperFragment;
    private MyFragment mMyFragment;
    private ArticleFragment mArticleFragment;

    private FragmentManager mFm;
    public BottomNavigationView mNavigationView;

//    private OnSellFragment mRedPacketFragment;
//    private SelectedFragment mSelectedFragment;
//    private SearchFragment mSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //使得状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initFragment();
        initListener();
    }

public void initView(){
    mNavigationView = findViewById(R.id.main_navigation_bar);
}

    //初始化Fragment
    public void initFragment(){
        mHomeFragment = new HomeFragment();
        mWallPaperFragment = new WallPaperFragment();
        mArticleFragment = new ArticleFragment();
        mMyFragment = new MyFragment();
        mFm = getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }


    private Fragment lastOneFragment = null;

    private void switchFragment(Fragment targetFragment) {
        //如果上一个fragment跟当前要切换的fragment是同一个，那么不需要切换
        if(lastOneFragment == targetFragment) {
            return;
        }
        //修改成add和hide的方式来控制Fragment的切换
        FragmentTransaction fragmentTransaction = mFm.beginTransaction();
        if(!targetFragment.isAdded()) {
            fragmentTransaction.add(R.id.main_page_container,targetFragment);
        } else {
            fragmentTransaction.show(targetFragment);
        }
        if(lastOneFragment != null) {
            fragmentTransaction.hide(lastOneFragment);
        }
        lastOneFragment = targetFragment;
//        fragmentTransaction.replace(R.id.main_page_container,targetFragment);
        fragmentTransaction.commit();
    }


    public void initListener(){
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
          if (item.getItemId() == R.id.item_home){
              switchFragment(mHomeFragment);
          }else if(item.getItemId() == R.id.item_wallpaper){
              switchFragment(mWallPaperFragment);
          }else if(item.getItemId() == R.id.item_article){
              switchFragment(mArticleFragment);
          }else if(item.getItemId() == R.id.item_my){
              switchFragment(mMyFragment);
          }


          return true;
        });
    }
}
package com.colin.sunshine.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.colin.sunshine.Constants;
import com.colin.sunshine.R;
import com.colin.sunshine.utils.HttpUtils;

/**
 * Date:2021-12-02
 * Time:19:47
 * author:colin
 * 首页Fragment
 */
public class HomeFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpUtils.getDiscovery();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        

        return inflater.inflate(R.layout.fragment_home,container,false);

    }


}

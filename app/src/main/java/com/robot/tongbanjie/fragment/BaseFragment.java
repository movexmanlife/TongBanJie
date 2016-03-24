package com.robot.tongbanjie.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Activity基类
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initTitle();
        initView();
        setListener();
    }

    /** 初始化数据 */
    public abstract void initData();
    /** 初始化Title */
    public abstract void initTitle();
    /** 初始化View */
    public abstract void initView();
    /** 设置View的相关监听 */
    public abstract void setListener();

    @Override
    public void onResume() {
        super.onResume();
        startStatisticAnalysis();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopStatisticAnalysis();
    }

    /**
     * 开始用户行为统计与分析
     */
    private void startStatisticAnalysis() {

    }

    /**
     * 停止用户行为统计与分析
     */
    private void stopStatisticAnalysis() {

    }
}

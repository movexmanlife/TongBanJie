package com.robot.tongbanjie.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Activity基类
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initTitle();
        initView();
        setListener();
    }

    /** 初始化数据 */
    public abstract void initData();
    /** 初始化Title */
    public abstract void setListener();
    /** 初始化View */
    public abstract void initTitle();
    /** 设置View的相关监听 */
    public abstract void initView();

    @Override
    protected void onResume() {
        super.onResume();
        startStatisticAnalysis();
    }

    @Override
    protected void onPause() {
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

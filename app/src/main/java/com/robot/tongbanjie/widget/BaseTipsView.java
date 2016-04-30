package com.robot.tongbanjie.widget;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.robot.tongbanjie.R;

/**
 * 引导View
 */
public abstract class BaseTipsView extends LinearLayout {
    private static final String TAG = BaseTipsView.class.getSimpleName();
    private String mUniqueKey;
    protected OnCloseListener mOnCloseListener;
    protected OnSureListener mOnSureListener;

    public BaseTipsView(Context context) {
        super(context);
        init();
    }

    public BaseTipsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseTipsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mUniqueKey = getUniquekey();
        if (TextUtils.isEmpty(mUniqueKey)) {
            throw new IllegalArgumentException("Uniquekey must not empty!");
        }
        maskClick();
    }

    /**
     * 添加StatusBar
     */
    protected void addStatusBarView() {
        View statusBarView = new View(getContext());
        statusBarView.setBackgroundColor(getContext().getResources().getColor(R.color.divider_color));
        int statusBarHeight = getStatusBarHeight();
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        statusBarView.setLayoutParams(lp);
        addView(statusBarView);
    }

    public abstract String getUniquekey();

    /**
     * 屏蔽点击事件
     */
    private void maskClick() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // do nothing
                // 因此View背景透明，防止点击到此View后面的控件
            }
        });
    }

    /*
     * 显示
     */
    public void show(final Activity activity) {
        if (!isMoreTipsShowed()) {
            addViewToDector(activity);
            setMoreTipsShowed(true);
        }
    }

    /*
     * 消失
     */
    public void dismiss(final Activity activity) {
        removeViewFromDector(activity);
    }

    /**
     * 添加View到dectorView
     *
     * @param activity
     */
    private void addViewToDector(final Activity activity) {
        ViewGroup dectorView = (ViewGroup) activity.getWindow().getDecorView();
        dectorView.addView(this);
    }

    /**
     * 从dectorView移出View
     *
     * @param activity
     */
    private void removeViewFromDector(final Activity activity) {
        ViewGroup dectorView = (ViewGroup) activity.getWindow().getDecorView();
        dectorView.removeView(this);
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.mOnCloseListener = onCloseListener;
    }

    public void setOnSureListener(OnSureListener onSureListener) {
        this.mOnSureListener = onSureListener;
    }

    public interface OnCloseListener {
        public void onClose(BaseTipsView baseTipsView);
    }

    public interface OnSureListener {
        public void onSure(BaseTipsView baseTipsView);
    }

    /**
     * 判断引导View是否显示过
     * @return
     */
    protected boolean isMoreTipsShowed() {
        SharedPreferences sp = getContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sp.getBoolean(mUniqueKey, false);
    }

    /**
     * 设置引导View显示过
     * @param isShowed
     */
    protected void setMoreTipsShowed(boolean isShowed) {
        SharedPreferences sp = getContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(mUniqueKey, isShowed);
        editor.commit();
    }

    /**
     * 判断View是否显示
     * @return
     */
    public boolean isShowing() {
        if (getParent() == null) {
            return false;
        } else {
            return true;
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

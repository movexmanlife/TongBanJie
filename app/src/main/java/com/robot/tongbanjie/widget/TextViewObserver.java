package com.robot.tongbanjie.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * 实现观察者模式的观察者
 *
 */
public class TextViewObserver extends TextView implements Observer{
    private static final String DEFAULT_HIDER = "******";

    private String mOrignalContent = "";
    private String mHiderContent = DEFAULT_HIDER;
    private boolean mIsHideContent = false;
    private OnShownListener mOnShownListener;
    private OnHiderListener mOnHiderListener;

    public TextViewObserver(Context context) {
        super(context);
        mOrignalContent =  getText().toString();
    }

    public TextViewObserver(Context context, AttributeSet attrs) {
        super(context, attrs);
        mOrignalContent =  getText().toString();
    }

    public TextViewObserver(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mOrignalContent =  getText().toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        mIsHideContent = (boolean)arg;
        if (mIsHideContent) {
            setText(mHiderContent);
            if (mOnHiderListener != null) {
                mOnHiderListener.onHider();
            }
        } else {
            setText(mOrignalContent);
            if (mOnShownListener != null) {
                mOnShownListener.onShown();
            }
        }
    }

    /**
     * 注意：需要调用此方法才能实现隐藏效果，若直接调用setText()方法，则实现不了
     * @param text
     */
    public void setTextExtension(String text) {
        mOrignalContent = String.valueOf(text);
        if (!mIsHideContent) {  // 非隐藏状态下，更新内容
            setText(mOrignalContent);
        }
    }

    /**
     * 注意：需要调用此方法才能实现隐藏效果，若直接调用setText()方法，则实现不了
     * @param text
     */
    public void setTextExtension(CharSequence text) {
        mOrignalContent = String.valueOf(text);
        if (!mIsHideContent) {  // 非隐藏状态下，更新内容
            setText(mOrignalContent);
        }
    }

    public void setHiderContent(String mHiderContent) {
        this.mHiderContent = mHiderContent;
    }

    public String getHiderContent() {
        return this.mHiderContent;
    }

    public void setOnShownListener(OnShownListener onShownListener) {
        this.mOnShownListener = onShownListener;
    }

    public void setOnHiderListener(OnHiderListener onHiderListener) {
        this.mOnHiderListener = onHiderListener;
    }

    public interface OnShownListener {
        public void onShown();
    }

    public interface OnHiderListener {
        public void onHider();
    }
}

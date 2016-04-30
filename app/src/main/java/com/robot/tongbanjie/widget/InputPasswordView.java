package com.robot.tongbanjie.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.robot.tongbanjie.R;

import java.util.ArrayList;
import java.util.List;

public class InputPasswordView extends LinearLayout implements SafeKeyboardView.OnAddListener,
        SafeKeyboardView.OnDeleteListener, SafeKeyboardView.OnConfirmListener{
    private static final int PASSWORD_CNT = 6;
    private static final int DEFAULT_TOP_BOTTOM_BORDER_HEIGHT = 1;
    private static final int DEFAULT_MIDDLE_BORDER_WIDTH = 1;
    private static final String DEFAULT_BORDER_COLOR = "#c8c7cc";

    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_PASSWORD_ARRAY = "instance_password_array";

    private Context mContext;

    /**
     * 上边框
     */
    private View mTopBorder;
    /**
     * 下边框
     */
    private View mBottomBorder;

    /**
     * 中间边框
     */
    private View mMiddleBorder;
    private List<String> mPassword = new ArrayList<String>(PASSWORD_CNT);
    private List<ImageView> mImageList = new ArrayList<ImageView>(PASSWORD_CNT);
    private OnPasswdEmptyListener mOnPasswdEmptyListener;
    private OnPasswdCompletedListener mOnPasswdCompletedListener;

    public InputPasswordView(Context context) {
        super(context);
        initView(context);
    }

    public InputPasswordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public InputPasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView(Context context) {
        this.mContext = context;
        setOrientation(VERTICAL);
        addTopBorder();
        addPasswordViewAndMiddleBorder();
        addBottomBorder();
        setListener();
    }

    private void addTopBorder() {
        mTopBorder = new View(mContext);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, DEFAULT_TOP_BOTTOM_BORDER_HEIGHT);
        mTopBorder.setLayoutParams(layoutParams);
        mTopBorder.setBackgroundColor(Color.parseColor(DEFAULT_BORDER_COLOR));
        addView(mTopBorder);
    }

    private void addPasswordViewAndMiddleBorder() {
        LinearLayout passwordLayout = new LinearLayout(mContext);
        passwordLayout.setOrientation(HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f);
        passwordLayout.setLayoutParams(layoutParams);

        for (int i = 0; i < PASSWORD_CNT; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.drawable.trans_pwd_icon);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setVisibility(View.INVISIBLE);
            LayoutParams passwrodLp = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1f);
            imageView.setLayoutParams(passwrodLp);
            passwordLayout.addView(imageView);
            mImageList.add(imageView);

            if (i != PASSWORD_CNT - 1) {
                View view = new View(mContext);
                LayoutParams middleBorderLp = new LayoutParams(DEFAULT_MIDDLE_BORDER_WIDTH, LayoutParams.MATCH_PARENT);
                view.setBackgroundColor(Color.parseColor(DEFAULT_BORDER_COLOR));
                view.setLayoutParams(middleBorderLp);
                passwordLayout.addView(view);
            }
        }

        addView(passwordLayout);
    }

    private void addBottomBorder() {
        mBottomBorder = new View(mContext);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, DEFAULT_MIDDLE_BORDER_WIDTH);
        mBottomBorder.setLayoutParams(layoutParams);
        mBottomBorder.setBackgroundColor(Color.parseColor(DEFAULT_BORDER_COLOR));
        addView(mBottomBorder);
    }

    private void refreshPasswdView(List<String> arrayList) {
        for (int i = 0; i < mImageList.size(); i++) {
            ImageView pwd = mImageList.get(i);

            if (i < arrayList.size()) {
                if (pwd.getVisibility() == INVISIBLE) {
                    pwd.setVisibility(View.VISIBLE);
                }
            } else {
                pwd.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void setListener() {
//        setFocusable(true);
//        setFocusableInTouchMode(true);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                requestFocus();
            }
        });
    }

    private void clearAllPasswdView() {
        for (int i = 0; i < mImageList.size(); i++) {
            mImageList.get(i).setVisibility(View.INVISIBLE);
        }
    }

    public void add(String passwd) {
        if (!isFull()) {
            mPassword.add(passwd);
            refreshPasswdView(mPassword);
        } else {
            if (mOnPasswdEmptyListener != null) {
                mOnPasswdCompletedListener.onCompleted(this);
            }
        }
    }

    public void delete() {
        if (!isEmpty()) {
            mPassword.remove(mPassword.size() - 1);
            refreshPasswdView(mPassword);
        } else {
            if (mOnPasswdEmptyListener != null) {
                mOnPasswdCompletedListener.onCompleted(this);
            }
        }
    }

    public void clear() {
        mPassword.clear();
        clearAllPasswdView();
    }

    public boolean isFull() {
        return mPassword.size() == PASSWORD_CNT;
    }

    public boolean isCompleted() {
        return mPassword.size() == PASSWORD_CNT;
    }

    public boolean isEmpty() {
        return mPassword.isEmpty();
    }

    public String getPassword() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mPassword.size(); i++) {
            stringBuilder.append(mPassword.get(i));
        }
        return stringBuilder.toString();
    }

    public int getLength() {
        return mPassword.size();
    }

    public void setBgColor(String str) {
        this.setBackgroundColor(Color.parseColor(str));
    }

    public void setBgColor(int resId) {
        this.setBackgroundColor(getContext().getResources().getColor(resId));
    }


    public void setTopBorderBackgroud(int resId) {
        mTopBorder.setBackgroundResource(resId);
    }

    public void setBottomBorderBackgroud(int resId) {
        mTopBorder.setBackgroundResource(resId);
    }

    public void setOnPasswdEmptyListener(OnPasswdEmptyListener onPasswdEmptyListener) {
        this.mOnPasswdEmptyListener = onPasswdEmptyListener;
    }

    public void setOnPasswdCompletedListener(OnPasswdCompletedListener onPasswdCompletedListener) {
        this.mOnPasswdCompletedListener = onPasswdCompletedListener;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putStringArray(INSTANCE_PASSWORD_ARRAY, mPassword.toArray(new String[mPassword.size()]));
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            final Bundle bundle = (Bundle) state;
            String[] passwordArray = bundle.getStringArray(INSTANCE_PASSWORD_ARRAY);
            mPassword.clear();
            if (passwordArray != null) {
                for (int i = 0; i < passwordArray.length; i++) {
                    mPassword.add(passwordArray[i]);
                }
            }
            refreshPasswdView(mPassword);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    public void onAdd(View view, String number) {
        add(number);
    }

    @Override
    public void onConfirm(View view) {

    }

    @Override
    public void onDelete(View view) {
        delete();
    }

    public interface OnPasswdEmptyListener {
        public void onEmpty(View view);
    }

    public interface OnPasswdCompletedListener {
        public void onCompleted(View view);
    }
}
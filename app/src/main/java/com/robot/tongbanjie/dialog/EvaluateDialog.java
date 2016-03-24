package com.robot.tongbanjie.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.robot.tongbanjie.R;

public final class EvaluateDialog extends Dialog {

    private Button mStoreBtn;
    private Button mFeedbackBtn;
    private Button mCancelBtn;

    private OnStoreListener mOnStoreListener;
    private OnFeedbackListener mOnFeedbackListener;
    private OnCancelListener mOnCancelListener;

    public EvaluateDialog(Context context) {
        super(context);
    }

    public EvaluateDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_evaluate);

        initData();
        initView();
    }

    private void initData() {
        setCancelable(true);  // 设置当返回键按下是否关闭对话框
        setCanceledOnTouchOutside(false);  // 设置当点击对话框以外区域是否关闭对话框
    }

    private void initView() {
        mStoreBtn = (Button) findViewById(R.id.goto_store_btn);
        mFeedbackBtn = (Button) findViewById(R.id.goto_feedback_btn);
        mCancelBtn = (Button) findViewById(R.id.cancel_btn);

        mStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStoreListener != null) {
                    mOnStoreListener.onStore(v);
                    dismiss();
                }
            }
        });
        mFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mOnFeedbackListener != null) {
                    mOnFeedbackListener.onFeedback(v);
                    dismiss();
                }
            }
        });
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCancelListener != null) {
                    mOnCancelListener.onCancel(v);
                    dismiss();
                }
            }
        });
    }

    public OnStoreListener getOnStoreListener() {
        return mOnStoreListener;
    }

    public void setOnStoreListener(OnStoreListener onStoreListener) {
        this.mOnStoreListener = onStoreListener;
    }

    public OnFeedbackListener getOnFeedbackListener() {
        return mOnFeedbackListener;
    }

    public void setOnFeedbackListener(OnFeedbackListener onFeedbackListener) {
        this.mOnFeedbackListener = onFeedbackListener;
    }

    public OnCancelListener getOnCancelListener() {
        return mOnCancelListener;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.mOnCancelListener = onCancelListener;
    }

    public interface OnStoreListener {
        public void onStore(View v);
    }

    public interface OnFeedbackListener {
        public void onFeedback(View v);
    }

    public interface OnCancelListener {
        public void onCancel(View v);
    }
}

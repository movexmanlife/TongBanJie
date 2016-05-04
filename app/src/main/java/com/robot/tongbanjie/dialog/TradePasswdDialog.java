package com.robot.tongbanjie.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.widget.InputPasswordView;

public final class TradePasswdDialog extends ProgressDialog{

    private ImageView mCancel;
    private InputPasswordView mInputPwdView;
    private TextView mSure;
    private float mScale = 0.9f;

    public TradePasswdDialog(Context context) {
        super(context);
    }

    public TradePasswdDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (outMetrics.widthPixels * mScale);
        getWindow().setAttributes(params);
        setContentView(R.layout.dialog_trade_passwd);

        initData();
        initView();
    }

    private void initData() {
        setCancelable(false);  // 设置当返回键按下是否关闭对话框
        setCanceledOnTouchOutside(false);  // 设置当点击对话框以外区域是否关闭对话框
    }

    private void initView() {
        mCancel = (ImageView) findViewById(R.id.cancel);
        mInputPwdView = (InputPasswordView) findViewById(R.id.input_pwd_view);
        mSure = (TextView) findViewById(R.id.sure);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public InputPasswordView getInputPwdView() {
        return mInputPwdView;
    }

    @Override
    public void show() {
        super.show();
        mInputPwdView.clear();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}

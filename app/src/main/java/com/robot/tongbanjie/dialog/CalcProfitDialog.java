package com.robot.tongbanjie.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.robot.tongbanjie.R;

public final class CalcProfitDialog extends ProgressDialog{

    private ImageView mCancel;
    private Button mCalcEarningsBtn;
    private float mScale = 0.9f;

    public CalcProfitDialog(Context context) {
        super(context);
    }

    public CalcProfitDialog(Context context, int theme) {
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
        setContentView(R.layout.dialog_calc_profit);

        initData();
        initView();
    }

    private void initData() {
        setCancelable(false);  // 设置当返回键按下是否关闭对话框
        setCanceledOnTouchOutside(false);  // 设置当点击对话框以外区域是否关闭对话框
    }

    private void initView() {
        mCancel = (ImageView) findViewById(R.id.cancel);
        mCalcEarningsBtn = (Button) findViewById(R.id.calc_earnings_btn);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void show() {
        super.show();
        // 只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}

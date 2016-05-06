package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.sharedpreference.SettingSharedPreUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 欢迎页
 */
public class WelcomeActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    @Bind(R.id.iv_welcome)
    ImageView ivWelcome;
    @Bind(R.id.jump_btn)
    Button jumpBtn;
    @Bind(R.id.icon_welcome_iv)
    ImageView iconWelcomeIv;
    @Bind(R.id.bottom_img)
    RelativeLayout bottomImg;


    public static void start(Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        if (!SettingSharedPreUtils.getGuideState(this, false)) {
            GuideActivity.start(this);
            finish();
        } else {
            jumpBtn.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isFinishing()) {
                        return;
                    }

                    MainActivity.start(WelcomeActivity.this);
                    finish();
                }
            }, 5000);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        jumpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jump_btn:
                MainActivity.start(this);
                jumpBtn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 300);
                break;
            default:
                break;
        }
    }
}

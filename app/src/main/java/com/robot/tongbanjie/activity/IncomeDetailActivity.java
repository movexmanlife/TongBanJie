package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.anim.AnimationAdapter;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.util.TextViewUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class IncomeDetailActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = IncomeDetailActivity.class.getSimpleName();
    private static final String TITLE_IMG_TAG_DOWN = "down";
    private static final String TITLE_IMG_TAG_UP = "up";

    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.shader)
    View shader;
    @Bind(R.id.radio1)
    RadioButton radio1;
    @Bind(R.id.radio2)
    RadioButton radio2;
    @Bind(R.id.radio3)
    RadioButton radio3;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    private boolean isFilterAniming = false;

    public static void start(Context context) {
        Intent intent = new Intent(context, IncomeDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_income_detail);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtDrawable("收支明细", "返回", R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        mTitleBar.getTitleImgView().setVisibility(View.VISIBLE);
        mTitleBar.getTitleView().setPadding(0, 0, 0, 0);
        int paddingTop = DensityUtils.dip2px(this, 5);
        int paddingLeft = DensityUtils.dip2px(this, 10);
        mTitleBar.getTitleImgView().setPadding(paddingLeft, paddingTop, paddingLeft, paddingTop);
        mTitleBar.setTitleImg(R.drawable.arrow_down);
        mTitleBar.getTitleImgView().setTag(TITLE_IMG_TAG_DOWN);
        mTitleBar.getTitleImgView().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                filterLayoutAnim();
            }
        });
    }

    private void filterLayoutAnim() {
        if (isFilterAniming) {
            return;
        }

        isFilterAniming = true;
        if (TextUtils.equals(TITLE_IMG_TAG_DOWN, (String) mTitleBar.getTitleImgView().getTag())) {
            mTitleBar.setTitleImg(R.drawable.arrow_up);
            mTitleBar.getTitleImgView().setTag(TITLE_IMG_TAG_UP);

            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_top_in);
            animation.setAnimationListener(new AnimationAdapter() {
                @Override
                public void onAnimationStart(Animation animation) {
                    shader.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    radioGroup.clearAnimation();
                    isFilterAniming = false;
                }
            });
            radioGroup.startAnimation(animation);
        } else {

            mTitleBar.setTitleImg(R.drawable.arrow_down);
            mTitleBar.getTitleImgView().setTag(TITLE_IMG_TAG_DOWN);

            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_top_out);
            animation.setAnimationListener(new AnimationAdapter() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    shader.setVisibility(View.GONE);
                    radioGroup.setVisibility(View.GONE);
                    radioGroup.clearAnimation();
                    isFilterAniming = false;
                }
            });
            radioGroup.startAnimation(animation);
        }
    }

    @Override
    public void initView() {
        radio1.setChecked(true);
        TextViewUtils.setRightImage(radio1, R.drawable.tick, 5);
    }

    @Override
    public void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                clearAllSelectedTick();

                if (checkedId == R.id.radio1) {
                    TextViewUtils.setRightImage(radio1, R.drawable.tick, 5);
                } else if (checkedId == R.id.radio2) {
                    TextViewUtils.setRightImage(radio2, R.drawable.tick, 5);
                } else if (checkedId == R.id.radio3) {
                    TextViewUtils.setRightImage(radio3, R.drawable.tick, 5);
                }
                filterLayoutAnim();
            }
        });
        shader.setOnClickListener(this);
    }

    public void clearAllSelectedTick() {
        TextViewUtils.setRightImage(radio1, null, 0);
        TextViewUtils.setRightImage(radio2, null, 0);
        TextViewUtils.setRightImage(radio3, null, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shader:
                filterLayoutAnim();
            default:
                break;
        }
    }

}

package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.adapter.ProductSelectedAdapter;
import com.robot.tongbanjie.anim.AnimationAdapter;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.util.ToastUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 新手专享
 */
public class NewhandActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = NewhandActivity.class.getSimpleName();
    private static final String TITLE_IMG_TAG_DOWN = "down";
    private static final String TITLE_IMG_TAG_UP = "up";
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.shader)
    View shader;
    @Bind(R.id.listview)
    ListView listview;
    private ProductSelectedAdapter mProductSelectedAdapter;

    private boolean isFilterAniming;

    public static void start(Context context) {
        Intent intent = new Intent(context, NewhandActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_newer);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        mProductSelectedAdapter = new ProductSelectedAdapter(this);
    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtDrawable("新手专享", "返回", R.drawable.btn_back_sel, 5);
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
                    listview.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    listview.clearAnimation();
                    isFilterAniming = false;
                }
            });
            listview.startAnimation(animation);
        } else {

            mTitleBar.setTitleImg(R.drawable.arrow_down);
            mTitleBar.getTitleImgView().setTag(TITLE_IMG_TAG_DOWN);

            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_top_out);
            animation.setAnimationListener(new AnimationAdapter() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    shader.setVisibility(View.GONE);
                    listview.setVisibility(View.GONE);
                    listview.clearAnimation();
                    isFilterAniming = false;
                }
            });
            listview.startAnimation(animation);
        }
    }

    @Override
    public void initView() {
        listview.setAdapter(mProductSelectedAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showShort("position: " + position);
            }
        });

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

}

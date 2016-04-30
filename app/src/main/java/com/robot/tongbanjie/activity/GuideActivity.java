package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.sharedpreference.SettingSharedPreUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 引导页
 */
public class GuideActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = GuideActivity.class.getSimpleName();
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.indicator)
    LinearLayout indicator;
    @Bind(R.id.reg_login_layout)
    RelativeLayout regLoginLayout;
    @Bind(R.id.reg_login)
    TextView regLogin;
    @Bind(R.id.into_main)
    TextView intoMain;
    private List<View> viewList;
    private ImageView[] indicatorImgs;
    private static final int GUIDE_PAGE_COUNT = 4;
    private int[] imgResArr = new int[]{R.drawable.page0, R.drawable.page1, R.drawable.page2, R.drawable.page3};

    public static void start(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        indicatorImgs = new ImageView[GUIDE_PAGE_COUNT];
        viewList = new ArrayList<View>(GUIDE_PAGE_COUNT);
        for (int i = 0; i < GUIDE_PAGE_COUNT; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.activity_guide_item, null);
            view.setBackgroundResource(R.color.white);
            ((ImageView) view.findViewById(R.id.guide_image)).setBackgroundDrawable(getResources().getDrawable(imgResArr[i]));
            viewList.add(view);
            indicatorImgs[i] = new ImageView(this);
            if (i == 0) {
                indicatorImgs[i].setBackgroundResource(R.drawable.ic_indicators_now);
            } else {
                indicatorImgs[i].setBackgroundResource(R.drawable.ic_indicators_default);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(20, 0, 0, 0);
                indicatorImgs[i].setLayoutParams(layoutParams);
            }
            indicator.addView(indicatorImgs[i]);
        }
    }

    @Override
    public void initTitle() {
    }

    @Override
    public void initView() {
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setListener() {
        regLogin.setOnClickListener(this);
        intoMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_login:
                SettingSharedPreUtils.setGuideState(this, true);
                MainActivity.start(this);
                CheckPhoneActivity.start(this, true, null);
                gotoFinish();
                break;
            case R.id.into_main:
                SettingSharedPreUtils.setGuideState(this, true);
                MainActivity.start(this);
                gotoFinish();
                break;
            default:
                break;
        }
    }

    public void gotoFinish() {
        regLoginLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 300);
    }

    public void setIndicator(int targetIndex) {
        for (int i = 0; i < indicatorImgs.length; i++) {
            indicatorImgs[i].setBackgroundResource(R.drawable.ic_indicators_now);
            if (targetIndex != i) {
                indicatorImgs[i].setBackgroundResource(R.drawable.ic_indicators_default);
            }
        }
    }
}

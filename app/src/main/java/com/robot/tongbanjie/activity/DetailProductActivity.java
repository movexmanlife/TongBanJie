package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.fragment.DetailProductFragment;
import com.robot.tongbanjie.fragment.DetailProductFragment2;
import com.robot.tongbanjie.widget.TitleBarView;
import com.stone.verticalslide.DragLayout;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DetailProductActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = DetailProductActivity.class.getSimpleName();

    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.detail_first)
    FrameLayout detailFirst;
    @Bind(R.id.detail_second)
    FrameLayout detailSecond;
    @Bind(R.id.draglayout)
    DragLayout draglayout;
    private DetailProductFragment detailProductFragment;
    private DetailProductFragment2 detailProductFragment2;

    public static void start(Context context) {
        Intent intent = new Intent(context, DetailProductActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtDrawable("新手专享219期", "返回", R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        detailProductFragment = new DetailProductFragment();
        detailProductFragment2 = new DetailProductFragment2();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.detail_first, detailProductFragment).add(R.id.detail_second, detailProductFragment2)
                .commit();

        DragLayout.ShowNextPageNotifier nextIntf = new DragLayout.ShowNextPageNotifier() {
            @Override
            public void onDragNext() {
                detailProductFragment2.beginLoadData();
            }
        };
        draglayout = (DragLayout) findViewById(R.id.draglayout);
        draglayout.setNextPageListener(nextIntf);
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

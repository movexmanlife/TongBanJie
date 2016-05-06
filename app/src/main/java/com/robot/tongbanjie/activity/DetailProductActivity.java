package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.BigLoadingDialog;
import com.robot.tongbanjie.dialog.CalcProfitDialog;
import com.robot.tongbanjie.fragment.TopDetailProductFragment;
import com.robot.tongbanjie.fragment.BottomDetailProductFragment;
import com.robot.tongbanjie.widget.TitleBarView;
import com.stone.verticalslide.DragLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 产品详情页面
 */
public class DetailProductActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = DetailProductActivity.class.getSimpleName();

    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.top_detail_product_layout)
    FrameLayout mTopDetailProductLayout;
    @Bind(R.id.bottom_detail_product_layout)
    FrameLayout mBottomDetailProductLayout;
    @Bind(R.id.draglayout)
    DragLayout draglayout;
    @Bind(R.id.calc_profit)
    ImageView mCalcProfit;
    @Bind(R.id.buy_layout)
    LinearLayout buyLayout;
    private TopDetailProductFragment mTopDetailProductFragment;
    private BottomDetailProductFragment mBottomDetailProductFragment;

    private BigLoadingDialog mBigLoadingDialog;
    private CalcProfitDialog mCalcProfitDialog;

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
        mBigLoadingDialog = new BigLoadingDialog(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.style.LoadingDialogLight);
        mCalcProfitDialog = new CalcProfitDialog(this, R.style.LoadingDialogDark);
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
        mTopDetailProductFragment = new TopDetailProductFragment();
        mBottomDetailProductFragment = new BottomDetailProductFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.top_detail_product_layout, mTopDetailProductFragment).add(R.id.bottom_detail_product_layout, mBottomDetailProductFragment)
                .commit();

        DragLayout.ShowNextPageNotifier nextIntf = new DragLayout.ShowNextPageNotifier() {
            @Override
            public void onDragNext(int pageIndex) {
                if (pageIndex == 1) {
                    mBottomDetailProductFragment.beginLoadData();
                    mTopDetailProductFragment.setPageIndicator(TopDetailProductFragment.Direction.Down);
                } else {
                    mTopDetailProductFragment.setPageIndicator(TopDetailProductFragment.Direction.Up);
                }
            }
        };
        draglayout = (DragLayout) findViewById(R.id.draglayout);
        draglayout.setNextPageListener(nextIntf);

    }

    @Override
    public void setListener() {
        buyLayout.setOnClickListener(this);
        mCalcProfit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calc_profit:
                mCalcProfitDialog.show();
                break;
            case R.id.buy_layout:
                mBigLoadingDialog.show();
                buyLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBigLoadingDialog.dismiss();
                    }
                }, 2000);
                break;
            default:
                break;
        }
    }

}

package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;

import com.binaryfork.spanny.Spanny;
import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.CommonDialog;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MyTongBaoActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = MyTongBaoActivity.class.getSimpleName();
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.tongbao_count)
    CommonItem tongbaoCount;
    @Bind(R.id.tongbao_tele_charge)
    CommonItem tongbaoTeleCharge;
    @Bind(R.id.tongbao_jifen)
    CommonItem tongbaoJifen;
    @Bind(R.id.tongbao_store)
    CommonItem tongbaoStore;

    public static void start(Context context) {
        Intent intent = new Intent(context, MyTongBaoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_tongbao);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        mTitleBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFinishing()) {
                    new CommonDialog(MyTongBaoActivity.this).builder().setTitle("提示")
                            .setPositiveBtn("确定", null)
                            .setContentMsg("您尚未在铜板街完成首次交易，无法兑换").show();
                }
            }
        }, 300);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtDrawable("我的铜板", "返回", R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        tongbaoCount.setType(Type.All);
        tongbaoCount.setSummaryImg(R.drawable.tongbao_detail);
        Spanny count = new Spanny("2", new ForegroundColorSpan(getResources().getColor(R.color.txt_red)),
                new AbsoluteSizeSpan(DensityUtils.sp2px(this, 24)));
        tongbaoCount.setSummaryText(count);
        tongbaoCount.setDetailText("查看铜板详情");
        tongbaoCount.setDetailImg(R.mipmap.icon_triangle_arrow);


        tongbaoTeleCharge.setType(Type.SummaryImgSummaryTxt_DetailImg);
        tongbaoTeleCharge.setSummaryImg(R.drawable.tongbao_tele_charge);
        Spanny charge = new Spanny("兑换话费\n", new ForegroundColorSpan(getResources().getColor(R.color.txt_red)),
                new AbsoluteSizeSpan(DensityUtils.sp2px(this, 16)))
                .append("2000铜板起兑", new ForegroundColorSpan(getResources().getColor(R.color.txt_gray)),
                        new AbsoluteSizeSpan(DensityUtils.sp2px(this, 12)));
        tongbaoTeleCharge.setSummaryText(charge);
        tongbaoTeleCharge.setDetailImg(R.mipmap.icon_triangle_arrow);

        tongbaoJifen.setType(Type.SummaryImgSummaryTxt_DetailImg);
        tongbaoJifen.setSummaryImg(R.drawable.tongbao_jifen);
        Spanny jifen = new Spanny("兑换集分宝\n", new ForegroundColorSpan(getResources().getColor(R.color.txt_red)),
                new AbsoluteSizeSpan(DensityUtils.sp2px(this, 16)))
                .append("1铜板起兑", new ForegroundColorSpan(getResources().getColor(R.color.txt_gray)),
                        new AbsoluteSizeSpan(DensityUtils.sp2px(this, 12)));
        tongbaoJifen.setSummaryText(jifen);
        tongbaoJifen.setDetailImg(R.mipmap.icon_triangle_arrow);

        tongbaoStore.setType(Type.SummaryImgSummaryTxt_DetailImg);
        tongbaoStore.setSummaryImg(R.drawable.tongbao_store);
        Spanny store = new Spanny("铜板商城\n", new ForegroundColorSpan(getResources().getColor(R.color.txt_red)),
                new AbsoluteSizeSpan(DensityUtils.sp2px(this, 16)))
                .append("试运行", new ForegroundColorSpan(getResources().getColor(R.color.txt_gray)),
                        new AbsoluteSizeSpan(DensityUtils.sp2px(this, 12)));
        tongbaoStore.setSummaryText(store);
        tongbaoStore.setDetailImg(R.mipmap.icon_triangle_arrow);
    }

    @Override
    public void setListener() {
        tongbaoCount.setOnClickListener(this);
        tongbaoTeleCharge.setOnClickListener(this);
        tongbaoJifen.setOnClickListener(this);
        tongbaoStore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tongbao_count:
                break;
            case R.id.tongbao_tele_charge:
                break;
            case R.id.tongbao_jifen:
                break;
            case R.id.tongbao_store:
                break;
            default:
                break;
        }
    }

}

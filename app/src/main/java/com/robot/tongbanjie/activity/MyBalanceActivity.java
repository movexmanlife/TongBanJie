package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.util.PackageUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 账户余额
 */
public class MyBalanceActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = MyBalanceActivity.class.getSimpleName();
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.my_balance_auto)
    CommonItem myBalanceAuto;
    @Bind(R.id.my_balance_divider)
    View myBalanceDivider;
    @Bind(R.id.my_balance_value)
    CommonItem myBalanceValue;
    @Bind(R.id.my_balance_charge)
    CommonItem myBalanceCharge;
    @Bind(R.id.my_balance_roll_out)
    CommonItem myBalanceRollOut;


    public static void start(Context context) {
        Intent intent = new Intent(context, MyBalanceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_balance);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtDrawable("账户余额", "返回", R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        myBalanceAuto.setType(Type.SummaryTxt_DetailImg);
        myBalanceAuto.getSummaryTextView().setTextSize(14);
        myBalanceAuto.getSummaryTextView().setTextColor(Color.parseColor("#d1564f"));
        myBalanceAuto.setSummaryText("资金自动转入铜宝，持续享受收益，随时提现");
        myBalanceAuto.setDetailImg(R.mipmap.icon_triangle_arrow);
        myBalanceAuto.setBackgroundColor(getResources().getColor(R.color.my_balance));
        myBalanceDivider.setVisibility(View.GONE);

        myBalanceValue.setType(Type.SummaryTxt_DetailImg);
        String account = "0.00";
        String unit = "(元)";
        SpannableString content = new SpannableString(account + unit);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#d1564f"));
        content.setSpan(colorSpan, 0, account.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        content.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(this, 28)), 0, account.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        myBalanceValue.setSummaryText(content);
        myBalanceValue.setDetailImg(R.mipmap.icon_triangle_arrow);

        myBalanceCharge.setType(Type.SummaryTxt_DetailImg);
        myBalanceCharge.setSummaryText("充值");
        myBalanceCharge.setDetailImg(R.mipmap.icon_triangle_arrow);

        myBalanceRollOut.setType(Type.SummaryTxt_DetailImg);
        myBalanceRollOut.setSummaryText("余额转出(提现)");
        myBalanceRollOut.setDetailImg(R.mipmap.icon_triangle_arrow);
    }

    @Override
    public void setListener() {
        myBalanceAuto.setOnClickListener(this);
        myBalanceValue.setOnClickListener(this);
        myBalanceCharge.setOnClickListener(this);
        myBalanceRollOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_balance_auto:

                break;
            case R.id.my_balance_value:
                IncomeDetailActivity.start(this);
                break;
            case R.id.my_balance_charge:
                break;
            case R.id.my_balance_roll_out:
                break;
            default:
                break;
        }
    }

}

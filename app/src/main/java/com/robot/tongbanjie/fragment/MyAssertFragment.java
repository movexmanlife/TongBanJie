package com.robot.tongbanjie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.activity.MyBalanceActivity;
import com.robot.tongbanjie.activity.MyDiscountActivity;
import com.robot.tongbanjie.activity.MyTCodeActivity;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的资产
 */
public class MyAssertFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG = MyAssertFragment.class.getSimpleName();
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.my_assert_my_balance)
    CommonItem mMyBalance;
    @Bind(R.id.my_assert_my_discount)
    CommonItem mMyDiscount;
    @Bind(R.id.my_assert_my_t_code)
    CommonItem mMyTCode;
    @Bind(R.id.my_assert_my_coin)
    CommonItem mMyCoin;
    @Bind(R.id.my_assert_trade_record)
    CommonItem mTradeRecord;

    public static MyAssertFragment newInstance() {
        MyAssertFragment fragment = new MyAssertFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_assert, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithRightImg("我的资产", R.drawable.service_senter_sel);
        mTitleBar.setBackgroundResource(R.color.my_assert_titlebar_bg);
        mTitleBar.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mTitleBar.getTitleView().setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        mTitleBar.setOnRightImgClickListener(new TitleBarView.OnRightImgClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(getActivity(), TAG, Toast.LENGTH_SHORT).show();
            }
        });
        mTitleBar.setDividerBg(getResources().getColor(R.color.my_assert_titlebar_bg));
    }

    @Override
    public void initView() {
        mMyBalance.setType(CommonItem.Type.SummaryTxt_DetailImg);
        mMyBalance.setSummaryText("账户余额 0.00");
        mMyBalance.setDetailImg(R.mipmap.icon_triangle_arrow);

        mMyDiscount.setType(CommonItem.Type.SummaryImgSummaryTxt_DetailImg);
        mMyDiscount.setSummaryImg(R.drawable.icon_discont);
        mMyDiscount.setSummaryText("我的优惠");
        mMyDiscount.setDetailImg(R.mipmap.icon_triangle_arrow);

        mMyTCode.setType(CommonItem.Type.SummaryImgSummaryTxt_DetailImg);
        mMyTCode.setSummaryImg(R.drawable.icon_tma);
        mMyTCode.setSummaryText("我的T码");
        mMyTCode.setDetailImg(R.mipmap.icon_triangle_arrow);

        mMyCoin.setType(CommonItem.Type.SummaryImgSummaryTxt_DetailImg);
        mMyCoin.setSummaryImg(R.drawable.icon_copperplate);
        mMyCoin.setSummaryText("我的铜板");
        mMyCoin.setDetailImg(R.mipmap.icon_triangle_arrow);

        mTradeRecord.setType(CommonItem.Type.SummaryImgSummaryTxt_DetailImg);
        mTradeRecord.setSummaryImg(R.drawable.icon_record);
        mTradeRecord.setSummaryText("交易记录");
        mTradeRecord.setDetailImg(R.mipmap.icon_triangle_arrow);
    }

    @Override
    public void setListener() {
        mMyBalance.setOnClickListener(this);
        mMyDiscount.setOnClickListener(this);
        mMyTCode.setOnClickListener(this);
        mMyCoin.setOnClickListener(this);
        mTradeRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_assert_my_balance:
                MyBalanceActivity.start(getActivity());
                break;
            case R.id.my_assert_my_discount:
                MyDiscountActivity.start(getActivity());
                break;
            case R.id.my_assert_my_t_code:
                MyTCodeActivity.start(getActivity());
                break;
            case R.id.my_assert_my_coin:
                break;
            case R.id.my_assert_trade_record:
                break;
            default:
                break;
        }
    }
}

package com.robot.tongbanjie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.binaryfork.spanny.Spanny;
import com.robot.tongbanjie.R;
import com.robot.tongbanjie.activity.MyBalanceActivity;
import com.robot.tongbanjie.activity.MyDiscountActivity;
import com.robot.tongbanjie.activity.MyTCodeActivity;
import com.robot.tongbanjie.activity.MyTongBaoActivity;
import com.robot.tongbanjie.activity.TongbaoActivity;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.util.HiderFormatUtils;
import com.robot.tongbanjie.util.NetworkBroadcastReceiverHelper;
import com.robot.tongbanjie.util.NetworkUtils;
import com.robot.tongbanjie.widget.CheckBoxObservable;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.TextViewObserver;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 我的资产
 */
public class MyAssertFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = MyAssertFragment.class.getSimpleName();
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.view_no_network)
    View mViewNoNetwork;
    @Bind(R.id.id_ptr_classic_frame_layout)
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    @Bind(R.id.my_assert_my_balance)
    LinearLayout myAssertMyBalance;
    @Bind(R.id.my_assert_limit_product)
    LinearLayout myAssertLimitProduct;
    @Bind(R.id.my_assert_tongbao)
    LinearLayout myAssertTongbao;
    @Bind(R.id.my_assert_my_discount)
    CommonItem mMyDiscount;
    @Bind(R.id.my_assert_my_t_code)
    CommonItem mMyTCode;
    @Bind(R.id.my_assert_my_coin)
    CommonItem mMyCoin;
    @Bind(R.id.my_assert_trade_record)
    CommonItem mTradeRecord;
    @Bind(R.id.checkBoxObservable)
    CheckBoxObservable checkBoxObservable;
    @Bind(R.id.my_assert_total_account)
    TextViewObserver myAssertTotalAccount;
    @Bind(R.id.regular_investment_account)
    TextViewObserver regularInvestmentAccount;
    @Bind(R.id.tongbao_account)
    TextViewObserver tongbaoAccount;
    @Bind(R.id.my_balance_account)
    TextViewObserver myBalanceAccount;
    @Bind(R.id.my_assert_total_account_layout)
    LinearLayout myAssertTotalAccountLayout;
    @Bind(R.id.total_account_hider)
    ImageView totalAccountHider;
    private NetworkBroadcastReceiverHelper mNetworkBroadcastReceiverHelper;

    public static MyAssertFragment newInstance() {
        MyAssertFragment fragment = new MyAssertFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_assert, null);
        ButterKnife.bind(this, view);
        registerBroadcast();
        return view;
    }

    /**
     * 显示网络提示
     */
    private void showNetworkTips() {
        if (NetworkUtils.getConnectivityStatus(getActivity()) == NetworkUtils.TYPE_NOT_CONNECTED) {
            mViewNoNetwork.setVisibility(View.VISIBLE);
        } else {
            mViewNoNetwork.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithRightImg("财富", R.drawable.service_senter_sel);
        String phoneNumber = "13829050259";

        Spanny title = new Spanny("财富\n", new ForegroundColorSpan(getResources().getColor(R.color.white)),
                new AbsoluteSizeSpan(DensityUtils.sp2px(getActivity(), 16)))
                .append(HiderFormatUtils.getPhone(phoneNumber), new ForegroundColorSpan(getResources().getColor(R.color.white)),
                        new AbsoluteSizeSpan(DensityUtils.sp2px(getActivity(), 12)));
        mTitleBar.getTitleView().setPadding(0, 0, 0, 0);
        mTitleBar.getTitleView().setGravity(Gravity.CENTER);
        mTitleBar.getTitleView().setSingleLine(false);
        mTitleBar.getTitleView().setText(title);
        mTitleBar.setBackgroundResource(R.color.my_assert_titlebar_bg);
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

        checkBoxObservable.addObserver(myAssertTotalAccount);
        totalAccountHider.setVisibility(View.INVISIBLE);
        myAssertTotalAccount.setOnHiderListener(new TextViewObserver.OnHiderListener() {
            @Override
            public void onHider() {
                myAssertTotalAccountLayout.setVisibility(View.INVISIBLE);
                totalAccountHider.setVisibility(View.VISIBLE);
            }
        });
        myAssertTotalAccount.setOnShownListener(new TextViewObserver.OnShownListener() {
            @Override
            public void onShown() {
                myAssertTotalAccountLayout.setVisibility(View.VISIBLE);
                totalAccountHider.setVisibility(View.INVISIBLE);
            }
        });
        checkBoxObservable.addObserver(regularInvestmentAccount);
        checkBoxObservable.addObserver(tongbaoAccount);
        checkBoxObservable.addObserver(myBalanceAccount);
        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrClassicFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrClassicFrameLayout.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        showNetworkTips();
    }

    @Override
    public void setListener() {
        myAssertMyBalance.setOnClickListener(this);
        myAssertLimitProduct.setOnClickListener(this);
        myAssertTongbao.setOnClickListener(this);
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
            case R.id.my_assert_limit_product:
                break;
            case R.id.my_assert_tongbao:
                TongbaoActivity.start(getActivity());
                break;
            case R.id.my_assert_my_discount:
                MyDiscountActivity.start(getActivity());
                break;
            case R.id.my_assert_my_t_code:
                MyTCodeActivity.start(getActivity());
                break;
            case R.id.my_assert_my_coin:
                MyTongBaoActivity.start(getActivity());
                break;
            case R.id.my_assert_trade_record:
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        unregisterBroadcast();
        if (checkBoxObservable != null) {
            checkBoxObservable.deleteObservers();
        }
    }

    private void registerBroadcast() {
        mNetworkBroadcastReceiverHelper = new NetworkBroadcastReceiverHelper(getActivity(),
                new NetworkBroadcastReceiverHelper.OnNetworkStateChangedListener(){
                    @Override
                    public void onConnected() {
                        if (mViewNoNetwork != null) {
                            mViewNoNetwork.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onDisConnected() {
                        if (mViewNoNetwork != null) {
                            mViewNoNetwork.setVisibility(View.VISIBLE);
                        }
                    }
                });
        mNetworkBroadcastReceiverHelper.register();
    }

    private void unregisterBroadcast() {
        mNetworkBroadcastReceiverHelper.unregister();
    }
}

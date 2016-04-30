package com.robot.tongbanjie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.adapter.InvestProductAdapter;
import com.robot.tongbanjie.entity.InvestProduct;
import com.robot.tongbanjie.util.NetworkBroadcastReceiverHelper;
import com.robot.tongbanjie.util.NetworkUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 理财产品
 */
public class ProductFragment extends BaseFragment {
    @Bind(R.id.titlebar)
    TitleBarView mTitlebar;
    @Bind(R.id.id_ptr_classic_frame_layout)
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private InvestProductAdapter mAdapter;
    private List<InvestProduct> mDatas;
    private NetworkBroadcastReceiverHelper mNetworkBroadcastReceiverHelper;

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, null);
        ButterKnife.bind(this, view);
        registerBroadcast();
        return view;
    }

    /**
     * 显示网络提示
     */
    private void showNetworkTips() {
        if (NetworkUtils.getConnectivityStatus(getActivity()) == NetworkUtils.TYPE_NOT_CONNECTED) {
            mAdapter.isShowNetworkErrorTips(true);
        } else {
            mAdapter.isShowNetworkErrorTips(false);
        }
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<InvestProduct>();
        InvestProduct network = new InvestProduct();
        network.viewType = InvestProductAdapter.ITEM_TYPE_NETWORK_ERROR;
        InvestProduct none = new InvestProduct();
        none.viewType = InvestProductAdapter.ITEM_TYPE_TONG_BAO;

        InvestProduct investProduct = new InvestProduct();
        investProduct.resId = R.drawable.invest_newer;
        investProduct.title = "新手专享";
        investProduct.desc = "超短期限，超高收益";
        investProduct.hasTimeLimit = true;
        investProduct.timeLimit = 1;
        investProduct.isReservation = false;
        investProduct.reservation = "";

        InvestProduct investProduct2 = new InvestProduct();
        investProduct2.resId = R.drawable.invest_yuyue;
        investProduct2.title = "预约专区";
        investProduct2.desc = "提前预约，锁定收益";
        investProduct2.hasTimeLimit = true;
        investProduct2.timeLimit = 6;
        investProduct2.isReservation = true;
        investProduct2.reservation = "预约成功，开售前享铜宝收益";

        InvestProduct investProduct3 = new InvestProduct();
        investProduct3.resId = R.drawable.invest_duanqi;
        investProduct3.title = "短期理财";
        investProduct3.desc = "期限灵活，收益稳健";
        investProduct3.hasTimeLimit = true;
        investProduct3.timeLimit = 6;
        investProduct3.isReservation = true;
        investProduct3.reservation = "更多产品，请在预约专区选购";

        InvestProduct investProduct4 = new InvestProduct();
        investProduct4.resId = R.drawable.invest_dingqi;
        investProduct4.title = "定期理财";
        investProduct4.desc = "长期优选，每月收钱";
        investProduct4.hasTimeLimit = false;
        investProduct4.timeLimit = 0;
        investProduct4.isReservation = false;
        investProduct4.reservation = "";

        InvestProduct investProduct5 = new InvestProduct();
        investProduct5.resId = R.drawable.invest_benxi;
        investProduct5.title = "等额本息";
        investProduct5.desc = "一次投资，每月收钱";
        investProduct5.hasTimeLimit = false;
        investProduct5.timeLimit = 0;
        investProduct5.isReservation = false;
        investProduct5.reservation = "";

        InvestProduct investProduct6 = new InvestProduct();
        investProduct6.resId = R.drawable.invest_yugao;
        investProduct6.title = "产品预告";
        investProduct6.desc = "优质产品，抢先知道";
        investProduct6.hasTimeLimit = false;
        investProduct6.timeLimit = 0;
        investProduct6.isReservation = false;
        investProduct6.reservation = "";

        InvestProduct investProduct7 = new InvestProduct();
        investProduct7.resId = R.drawable.invest_zhuanrang;
        investProduct7.title = "转让专区";
        investProduct7.desc = "当日起息，期限灵活";
        investProduct7.hasTimeLimit = true;
        investProduct7.timeLimit = 5;
        investProduct7.isReservation = true;
        investProduct7.reservation = "灵活变现：转出手续费3折优惠";

        mDatas.add(network); // 数据占位
        mDatas.add(none); // 数据占位
        mDatas.add(investProduct);
        mDatas.add(investProduct2);
        mDatas.add(investProduct3);
        mDatas.add(investProduct4);
        mDatas.add(investProduct5);
        mDatas.add(investProduct6);
        mDatas.add(investProduct7);
        mPtrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
        mPtrClassicFrameLayout.getHeaderView();
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
        mAdapter = new InvestProductAdapter(getActivity(), mDatas);
    }

    @Override
    public void initTitle() {
        mTitlebar.setTitle("投资");
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        showNetworkTips();
    }

    @Override
    public void setListener() {

    }

    private void registerBroadcast() {
        mNetworkBroadcastReceiverHelper = new NetworkBroadcastReceiverHelper(getActivity(),
                new NetworkBroadcastReceiverHelper.OnNetworkStateChangedListener(){
                    @Override
                    public void onConnected() {
                        mAdapter.isShowNetworkErrorTips(false);
                    }
                    @Override
                    public void onDisConnected() {
                        mAdapter.isShowNetworkErrorTips(true);
                    }
                });
        mNetworkBroadcastReceiverHelper.register();
    }

    private void unregisterBroadcast() {
        mNetworkBroadcastReceiverHelper.unregister();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterBroadcast();
    }

}

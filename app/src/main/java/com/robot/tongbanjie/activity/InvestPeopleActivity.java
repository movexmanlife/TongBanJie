package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.activeandroid.query.Select;
import com.robot.tongbanjie.R;
import com.robot.tongbanjie.adapter.InvestPeopleAdapter;
import com.robot.tongbanjie.entity.InvestPeople;
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
 * 预约人数
 */
public class InvestPeopleActivity extends BaseActivity {
    private static final String TAG = InvestPeopleActivity.class.getSimpleName();
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.id_ptr_classic_frame_layout)
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private InvestPeopleAdapter mAdapter;
    private List<InvestPeople> mDatas;
    private NetworkBroadcastReceiverHelper mNetworkBroadcastReceiverHelper;

    public static void start(Context context) {
        Intent intent = new Intent(context, InvestPeopleActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_invest_people);
        ButterKnife.bind(this);
        registerBroadcast();
        super.onCreate(savedInstanceState);
    }


    /**
     * 显示网络提示
     */
    private void showNetworkTips() {
        if (NetworkUtils.getConnectivityStatus(this) == NetworkUtils.TYPE_NOT_CONNECTED) {
            mAdapter.isShowNetworkErrorTips(true);
        } else {
            mAdapter.isShowNetworkErrorTips(false);
        }
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<InvestPeople>();
        InvestPeople network = new InvestPeople();
        network.viewType = InvestPeopleAdapter.ITEM_TYPE_NETWORK_ERROR;
        mDatas.add(network); // 数据占位

        mPtrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
        mPtrClassicFrameLayout.getHeaderView();
        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrClassicFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDbData();
                        mPtrClassicFrameLayout.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mAdapter = new InvestPeopleAdapter(this, mDatas);
    }

    private void getDbData() {
        int count = mDatas.size();
        long indexId = 0;
        if (count != 0) {
            InvestPeople investPeople = mDatas.get(count - 1);
            indexId = investPeople.getId();
        }

        final List<InvestPeople> list = new Select().from(InvestPeople.class).where("Id > " + indexId).orderBy("Id ASC").execute();
        if (list != null) {
            mDatas.addAll(list);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtDrawable("预约人数", "返回", R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        showNetworkTips();
    }

    @Override
    public void setListener() {

    }

    private void registerBroadcast() {
        mNetworkBroadcastReceiverHelper = new NetworkBroadcastReceiverHelper(this,
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
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
    }

}

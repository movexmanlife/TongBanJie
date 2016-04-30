package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.LoadingDialog;
import com.robot.tongbanjie.dialog.CommonDialog;
import com.robot.tongbanjie.util.TextViewUtils;
import com.robot.tongbanjie.util.sharedpreference.SettingSharedPreUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TongbaoAutoSettingActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = TongbaoAutoSettingActivity.class.getSimpleName();
    public static final String KEY_TITLE = "key_title";
    public static final String TITLE_SETTING = "设置";
    public static final String TITLE_AUTO_TRANSFERR = "自动转入";

    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.auto_setting)
    CommonItem mAutoSetting;
    @Bind(R.id.browse_protocal)
    TextView mBrowseProtocal;
    @Bind(R.id.switch_transferr)
    CheckBox mSwitchTransferr;
    @Bind(R.id.auto_switch_layout)
    LinearLayout mAutoSwitchLayout;
    private String mTitle;
    private LoadingDialog mLoadingDialog;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, TongbaoAutoSettingActivity.class);
        intent.putExtra(KEY_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tongbao_auto_setting);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        getArgs();
        mLoadingDialog = new LoadingDialog(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.style.LoadingDialogLight);
    }

    private void getArgs() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mTitle = bundle.getString(KEY_TITLE);
        }
    }

    @Override
    public void initTitle() {
        if (TextUtils.equals(mTitle, TITLE_SETTING)) {
            setTitle(mTitle);
        } else if ((TextUtils.equals(mTitle, TITLE_AUTO_TRANSFERR))) {
            setTitle(mTitle);
        }
    }

    private void setTitle(String title) {
        mTitleBar.initTitleWithLeftTxt(title, "返回");
        TextViewUtils.setLeftImage(mTitleBar.getLeftTxtView(), R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        if (TextUtils.equals(mTitle, TITLE_SETTING)) {
            mAutoSetting.setType(CommonItem.Type.SummaryTxt_DetailImg);
            mAutoSetting.setSummaryText("账户余额自动转入");
            mAutoSetting.setDetailImg(R.mipmap.icon_triangle_arrow);
            mAutoSetting.setVisibility(View.VISIBLE);
            mAutoSwitchLayout.setVisibility(View.GONE);
        } else if ((TextUtils.equals(mTitle, TITLE_AUTO_TRANSFERR))) {
            mAutoSetting.setVisibility(View.GONE);
            mAutoSwitchLayout.setVisibility(View.VISIBLE);
            if (SettingSharedPreUtils.getWifiSwitchState(this, false)) {
                mSwitchTransferr.setChecked(true);
            } else {
                mSwitchTransferr.setChecked(false);
            }
        }
        mLoadingDialog.show();
        mTitleBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingDialog.dismiss();
            }
        }, 2000);
    }

    @Override
    public void setListener() {
        mAutoSetting.setOnClickListener(this);
        mBrowseProtocal.setOnClickListener(this);
        mSwitchTransferr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SettingSharedPreUtils.setWifiSwitchState(getApplicationContext(), true);
                    mLoadingDialog.show();
                    mTitleBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mLoadingDialog.dismiss();
                            String msg = "自动转入已开启，账户余额将在每日凌晨自动转入铜宝";
                            showTipsDialog(msg);
                        }
                    }, 2000);
                } else {
                    SettingSharedPreUtils.setWifiSwitchState(getApplicationContext(), false);
                    mLoadingDialog.show();
                    mTitleBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mLoadingDialog.dismiss();
                            String msg = "自动转入已关闭";
                            showTipsDialog(msg);
                        }
                    }, 2000);
                }
            }

        });
    }

    private void showTipsDialog(String msg) {
        new CommonDialog(this).builder().setTitle("提示")
                .setContentMsg(msg)
                .setCanceledOnTouchOutside(false)
                .setPositiveBtn("确定", new CommonDialog.OnPositiveListener() {
                    @Override
                    public void onPositive(View view) {
                        finish();
                    }
                }).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auto_setting:
                TongbaoAutoSettingActivity.start(this, TITLE_AUTO_TRANSFERR);
                break;
            case R.id.browse_protocal:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.DeviceDialogFragment;
import com.robot.tongbanjie.dialog.ShareDialogFragment;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.util.DeviceInfoUtils;
import com.robot.tongbanjie.util.PackageUtils;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DeviceManagerActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = DeviceManagerActivity.class.getSimpleName();
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.device_manager_device_name)
    TextView deviceManagerDeviceName;
    @Bind(R.id.device_manager_device_info)
    LinearLayout deviceManagerDeviceInfo;
    @Bind(R.id.device_manager_device_img)
    ImageView deviceManagerDeviceImg;
    @Bind(R.id.device_manager_device_version)
    TextView deviceManagerDeviceVersion;
    @Bind(R.id.device_manager_login_time)
    TextView deviceManagerLoginTime;

    public static void start(Context context) {
        Intent intent = new Intent(context, DeviceManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_device_manager);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtDrawable("设备管理", "返回", R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        deviceManagerDeviceName.setText(DeviceInfoUtils.getModel());

        judgeDeviceIsLow();
        setLoginTime();
    }

    /**
     * 判断设备版本高低
     */
    private void judgeDeviceIsLow() {
        if (isLowDevice()) {
            deviceManagerDeviceImg.setImageResource(R.drawable.equipment_mobile);
            deviceManagerDeviceVersion.setText("高版本登录");
        } else {
            deviceManagerDeviceImg.setImageResource(R.drawable.equipment_low);
            deviceManagerDeviceVersion.setText("低版本登录");
        }
    }

    private boolean isLowDevice() {
        if (DeviceInfoUtils.getVersionSDK() >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置登录时间
     */
    private void setLoginTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sdf.format(new Date());
        deviceManagerLoginTime.setText(date);
    }

    @Override
    public void setListener() {
        deviceManagerDeviceInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.device_manager_device_info:
                DeviceDialogFragment deviceDialogFragment = DeviceDialogFragment.newInstance(this, null, 0);
                deviceDialogFragment.show(getSupportFragmentManager(), null);
                break;
            default:
                break;
        }
    }

}

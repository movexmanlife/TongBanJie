package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class TongbaoAssertsSettingActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = TongbaoAssertsSettingActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitlebar;

	public static void start(Context context) {
		Intent intent = new Intent(context, TongbaoAssertsSettingActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_tongbao_asserts_setting);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData(){
	}

	@Override
	public void initTitle() {
		mTitlebar.initTitleWithLeftTxtDrawable("铜宝资产配置", "返回", R.drawable.btn_back_sel, 5);
		mTitlebar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
	}

	@Override
	public void setListener() {

	}

	@Override
	public void onClick(View v) {

	}

}

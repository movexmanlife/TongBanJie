package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.TextViewUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * T码
 */
public class MyTCodeActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = MyTCodeActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitlebar;

	public static void start(Context context) {
		Intent intent = new Intent(context, MyTCodeActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_my_t_code);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitlebar.initTitleWithLeftTxtRightImg("T码", "返回", R.drawable.icon_fund_detail);
		TextViewUtils.setLeftImage(mTitlebar.getLeftTxtView(), R.drawable.btn_back_sel, 5);
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
		switch (v.getId()) {
			default:
				break;
		}
	}

}

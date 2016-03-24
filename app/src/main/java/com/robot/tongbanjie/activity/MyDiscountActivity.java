package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.TextViewUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MyDiscountActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = MyDiscountActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitlebar;
	@Bind(R.id.my_useable_ticket)
	RadioButton mMyUseableTicket;
	@Bind(R.id.my_history_ticket)
	RadioButton mMyHistoryTicket;

	public static void start(Context context) {
		Intent intent = new Intent(context, MyDiscountActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_my_discount);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitlebar.initTitleWithLeftTxtRightImg("我的优惠券", "返回", R.drawable.icon_fund_detail);
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
		mMyUseableTicket.setChecked(true);
	}

	@Override
	public void setListener() {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.my_useable_ticket:
				break;
			case R.id.my_history_ticket:
				break;
			default:
				break;
		}
	}

}

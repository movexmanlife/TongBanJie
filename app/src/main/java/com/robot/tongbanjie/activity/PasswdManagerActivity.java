package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PasswdManagerActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = PasswdManagerActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitlebar;
	@Bind(R.id.passwd_manager_modify_login_pwd)
	CommonItem mModifyLoginPwd;
	@Bind(R.id.passwd_manager_modify_trade_pwd)
	CommonItem mModifyTradePwd;
	@Bind(R.id.passwd_manager_retrieve_pwd)
	CommonItem mRetrievePwd;
	@Bind(R.id.passwd_manager_lock_pattern)
	CommonItem mLockPattern;

	public static void start(Context context) {
		Intent intent = new Intent(context, PasswdManagerActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_passwd_manager);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitlebar.initTitleWithLeftTxtDrawable("密码管理", "返回", R.drawable.btn_back_sel, 5);
		mTitlebar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		mModifyLoginPwd.setType(Type.SummaryTxt_DetailImg);
		mModifyLoginPwd.setSummaryText("修改登录密码");
		mModifyLoginPwd.setDetailImg(R.mipmap.icon_triangle_arrow);

		mModifyTradePwd.setType(Type.SummaryTxt_DetailImg);
		mModifyTradePwd.setSummaryText("修改交易密码");
		mModifyTradePwd.setDetailImg(R.mipmap.icon_triangle_arrow);

		mRetrievePwd.setType(Type.SummaryTxt_DetailImg);
		mRetrievePwd.setSummaryText("找回交易密码");
		mRetrievePwd.setDetailImg(R.mipmap.icon_triangle_arrow);

		mLockPattern.setType(Type.SummaryTxt_DetailImg);
		mLockPattern.setSummaryText("手势解锁");
		mRetrievePwd.setDetailImg(R.mipmap.icon_triangle_arrow);
	}

	@Override
	public void setListener() {
		mModifyLoginPwd.setOnClickListener(this);
		mModifyTradePwd.setOnClickListener(this);
		mRetrievePwd.setOnClickListener(this);
		mLockPattern.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.passwd_manager_modify_login_pwd:
				LoginPasswdModifyActivity.start(this);
				break;
			case R.id.passwd_manager_modify_trade_pwd:
				break;
			case R.id.passwd_manager_retrieve_pwd:

				break;
			case R.id.passwd_manager_lock_pattern:
				LockPatternGuideActivity.start(this);
				break;
			default:
				break;
		}
	}

}

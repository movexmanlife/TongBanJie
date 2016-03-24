package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.util.PackageUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SettingActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = SettingActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.setting_bank_card)
	CommonItem mBankCard;
	@Bind(R.id.setting_passwd)
	CommonItem mPasswd;
	@Bind(R.id.setting_amount_cashed)
	CommonItem mAccountCashed;
	@Bind(R.id.setting_update)
	CommonItem mUpdate;
	@Bind(R.id.setting_version)
	TextView mVersion;


	public static void start(Context context) {
		Intent intent = new Intent(context, SettingActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_setting);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitleBar.initTitleWithLeftTxtDrawable("设置", "返回", R.drawable.btn_back_sel, 5);
		mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		mBankCard.setType(Type.SummaryTxt_DetailImg);
		mBankCard.setSummaryText("银行卡管理");
		mBankCard.setDetailImg(R.mipmap.icon_triangle_arrow);

		mPasswd.setType(Type.SummaryTxt_DetailImg);
		mPasswd.setSummaryText("密码管理");
		mPasswd.setDetailImg(R.mipmap.icon_triangle_arrow);

		mAccountCashed.setType(Type.SummaryTxt_DetailImg);
		mAccountCashed.setSummaryText("回款路径");
		mAccountCashed.setDetailImg(R.mipmap.icon_triangle_arrow);

		mUpdate.setType(Type.SummaryTxt_DetailTxtDetailImg);
		mUpdate.setSummaryText("检查更新");
		mUpdate.setDetailText("NEW");
		mUpdate.getDetailTextView().setBackgroundResource(R.drawable.icon_count_99_bg);
		mUpdate.getDetailTextView().setTextColor(getResources().getColor(R.color.white));
		mUpdate.getDetailTextView().setPadding(DensityUtils.dip2px(this, 5),
				DensityUtils.dip2px(this, 1),
				DensityUtils.dip2px(this, 5),
				DensityUtils.dip2px(this, 1));
		mUpdate.getDetailTextView().setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		mUpdate.setDetailImg(R.mipmap.icon_triangle_arrow);

		String versionName = String.format(getString(R.string.versionName), PackageUtils.getVersionName(this));
		mVersion.setText(versionName);
	}

	@Override
	public void setListener() {
		mBankCard.setOnClickListener(this);
		mPasswd.setOnClickListener(this);
		mAccountCashed.setOnClickListener(this);
		mUpdate.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.setting_bank_card:
				BankCardManagerActivity.start(this);
				break;
			case R.id.setting_passwd:
				PasswdManagerActivity.start(this);
				break;
			case R.id.setting_amount_cashed:
				break;
			case R.id.setting_update:
				break;
			default:
				break;
		}
	}

}

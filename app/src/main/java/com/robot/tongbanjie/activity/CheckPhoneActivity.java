package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.LoadingDialog;
import com.robot.tongbanjie.util.ClearerFormatUtils;
import com.robot.tongbanjie.util.ToastUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CheckPhoneActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = CheckPhoneActivity.class.getSimpleName();
	private static final String KEY_CHECK_PHONE_ARGS = "check_phone_args";
	private static final String KEY_CHECK_PHONE_PHONE_NUMBER = "check_phone_number";

	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.check_phone_title)
	TextView mTitle;
	@Bind(R.id.check_phone_number)
	EditText mPhoneNumberView;
	@Bind(R.id.check_phone_tips)
	TextView mTips;
	@Bind(R.id.check_phone_next)
	Button mNextBtn;
	@Bind(R.id.check_phone_safe_tips)
	TextView mSafeTips;

	private boolean mIsCheckPhone = true;
	private String mPhoneNumber;
	private LoadingDialog mLoadingDialog;

	public static void start(Context context, boolean isCheckPhone, String phoneNumber) {
		Intent intent = new Intent(context, CheckPhoneActivity.class);
		intent.putExtra(KEY_CHECK_PHONE_ARGS, isCheckPhone);
		intent.putExtra(KEY_CHECK_PHONE_PHONE_NUMBER, phoneNumber);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getArgs();
		setContentView(R.layout.activity_check_phone);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);


	}

	private void getArgs() {
		Bundle bundle = getIntent().getExtras();
		mIsCheckPhone = bundle.getBoolean(KEY_CHECK_PHONE_ARGS, true);
		mPhoneNumber = bundle.getString(KEY_CHECK_PHONE_PHONE_NUMBER);
	}

	@Override
	public void initData() {
		mLoadingDialog = new LoadingDialog(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.style.LoadingDialogLight);
	}

	@Override
	public void initTitle() {
		String title;
		if (mIsCheckPhone) {
			title = "手机号";
		} else {
			title = "找回密码";
		}

		mTitleBar.initTitleWithLeftTxtDrawable(title, "返回", R.drawable.btn_back_sel, 5);
		mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		if (mIsCheckPhone) {
			mTitle.setVisibility(View.VISIBLE);
			mSafeTips.setVisibility(View.VISIBLE);
		} else {
			mTitle.setVisibility(View.GONE);
			mSafeTips.setVisibility(View.GONE);
			mPhoneNumberView.setText(mPhoneNumber);
			showTips(mPhoneNumber);
		}
	}

	@Override
	public void setListener() {
		mPhoneNumberView.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				showTips(s.toString());
			}
		});
		mNextBtn.setOnClickListener(this);
	}

	private void showTips(String content) {
		if (TextUtils.isEmpty(content)) {
			mTips.setVisibility(View.GONE);
			mNextBtn.setEnabled(false);
		} else {
			mTips.setVisibility(View.VISIBLE);
			mNextBtn.setEnabled(true);
		}
		mTips.setText(ClearerFormatUtils.getPhone(content));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.check_phone_next:
				mLoadingDialog.show();
				mNextBtn.postDelayed(new Runnable() {
					@Override
					public void run() {
						gotoLogin();
					}
				}, 3000);
				break;
			default:
				break;
		}
	}

	private void gotoLogin() {
		if (TextUtils.isEmpty(mPhoneNumberView.getText())) {
			ToastUtils.showShort("请输入正确的手机号码！");
			return;
		}
		mLoadingDialog.dismiss();
		int len = mPhoneNumberView.getText().length();
		if (len == getResources().getInteger(R.integer.phone_number_max_cnt)) {
			if (mIsCheckPhone) {
				LoginActivity.start(this, mPhoneNumberView.getText().toString());
			} else {

			}
		} else {
			ToastUtils.showShort("请输入正确的手机号码！");
		}
	}
}

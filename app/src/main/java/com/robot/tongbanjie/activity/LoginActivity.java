package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = LoginActivity.class.getSimpleName();
	private static final String KEY_PHONE_NUMBER = "phone_number";
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.login_passwd)
	EditText mPasswd;
	@Bind(R.id.login_forget_passwd)
	TextView mForgetPasswd;
	@Bind(R.id.login_confirm)
	Button mConfirm;
	private String mPhoneNumber;


	public static void start(Context context, String phoneNumber) {
		Intent intent = new Intent(context, LoginActivity.class);
		intent.putExtra(KEY_PHONE_NUMBER, phoneNumber);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getArgs();
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	private void getArgs() {
		Bundle bundle = getIntent().getExtras();
		mPhoneNumber = bundle.getString(KEY_PHONE_NUMBER);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitleBar.initTitleWithLeftTxtDrawable("登录密码", "返回", R.drawable.btn_back_sel, 5);
		mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
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
		mPasswd.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (TextUtils.isEmpty(s.toString())) {
					mConfirm.setEnabled(false);
				} else {
					int len = s.toString().length();
					if (len >= getResources().getInteger(R.integer.login_pwd_min_cnt) &&
							len <= getResources().getInteger(R.integer.login_pwd_max_cnt)) {
						mConfirm.setEnabled(true);
					} else {
						mConfirm.setEnabled(false);
					}
				}
			}
		});
		mForgetPasswd.setOnClickListener(this);
		mConfirm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.login_passwd:

				break;
			case R.id.login_forget_passwd:
				CheckPhoneActivity.start(this, false, mPhoneNumber);
				break;
			case R.id.login_confirm:

				break;
			default:
				break;
		}
	}

}

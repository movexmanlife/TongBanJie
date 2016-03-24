package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.util.PackageUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginPasswdModifyActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = LoginPasswdModifyActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.login_pwd_modify_old)
	EditText mOldPwd;
	@Bind(R.id.login_pwd_modify_new)
	EditText mSetPwd;
	@Bind(R.id.login_pwd_modify_repeat)
	EditText mRepeatPwd;
	@Bind(R.id.login_pwd_modify_btn)
	Button mModifyBtn;


	public static void start(Context context) {
		Intent intent = new Intent(context, LoginPasswdModifyActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login_passwd_modify);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitleBar.initTitleWithLeftTxtDrawable("修改登录密码", "返回", R.drawable.btn_back_sel, 5);
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
		mModifyBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.login_pwd_modify_btn:
				break;
			default:
				break;
		}
	}

}

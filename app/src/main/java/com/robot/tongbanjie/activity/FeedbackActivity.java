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
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FeedbackActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = FeedbackActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.feedback_contact)
	EditText mContact;
	@Bind(R.id.feedback_content)
	EditText mContent;
	@Bind(R.id.feedback_btn)
	Button mCommitBtn;


	public static void start(Context context) {
		Intent intent = new Intent(context, FeedbackActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_feedback);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitleBar.initTitleWithLeftTxtDrawable("意见反馈", "返回", R.drawable.btn_back_sel, 5);
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
		mContact.setOnClickListener(this);
		mContent.setOnClickListener(this);
		mCommitBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.feedback_contact:
				break;
			case R.id.feedback_content:
				break;
			case R.id.feedback_btn:
				break;
			default:
				break;
		}
	}

}

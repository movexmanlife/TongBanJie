package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.ShareDialogFragment;
import com.robot.tongbanjie.util.QuestionUtils;
import com.robot.tongbanjie.util.TextViewUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EvaluateRiskActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = EvaluateRiskActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.evaluate_risk)
	Button mEvaluateRisk;


	public static void start(Context context) {
		Intent intent = new Intent(context, EvaluateRiskActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_evaluate_risk);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitleBar.initTitleWithLeftTxtRightImg("评估结果", "返回", R.drawable.icon_share);
		TextViewUtils.setLeftImage(mTitleBar.getLeftTxtView(), R.drawable.btn_back_sel, 5);
		mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
		mTitleBar.setOnRightImgClickListener(new TitleBarView.OnRightImgClickListener() {
			@Override
			public void onClick() {
				ShareDialogFragment shareDialogFragment = ShareDialogFragment.newInstance(EvaluateRiskActivity.this, null, 0);
				shareDialogFragment.show(getSupportFragmentManager(), null);
			}
		});
	}

	@Override
	public void initView() {

	}

	@Override
	public void setListener() {
		mEvaluateRisk.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.evaluate_risk:
				QuestionInvestActivity.start(this);
				break;
			default:
				break;
		}
	}

}

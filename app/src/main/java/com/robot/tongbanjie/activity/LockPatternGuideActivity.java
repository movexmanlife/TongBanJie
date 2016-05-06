package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.util.PackageUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 手势密码锁定
 */
public class LockPatternGuideActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = LockPatternGuideActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitlebar;
	@Bind(R.id.lock_pattern_guide_create_btn)
	Button mCreateBtn;


	public static void start(Context context) {
		Intent intent = new Intent(context, LockPatternGuideActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_lock_pattern_guide);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitlebar.initTitleWithLeftTxtDrawable("手势密码锁定", "返回", R.drawable.btn_back_sel, 5);
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
		mCreateBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.lock_pattern_guide_create_btn:
				break;
			default:
				break;
		}
	}

}

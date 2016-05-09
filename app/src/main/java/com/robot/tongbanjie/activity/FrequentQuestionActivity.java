package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.fragment.BaseFragment;
import com.robot.tongbanjie.fragment.QuestionCategoryFragment;
import com.robot.tongbanjie.fragment.QuestionHotFragment;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 常见问题
 */
public class FrequentQuestionActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = FrequentQuestionActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitlebar;
	@Bind(R.id.question_hot)
	RadioButton mHot;
	@Bind(R.id.question_category)
	RadioButton mCategory;
	@Bind(R.id.rl_container)
	FrameLayout mContainer;

	public static void start(Context context) {
		Intent intent = new Intent(context, FrequentQuestionActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_question);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitlebar.initTitleWithLeftTxtDrawable("常见问题", "返回", R.drawable.btn_back_sel, 5);
		mTitlebar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		mHot.setChecked(true);
		onClick(mHot);
	}

	@Override
	public void setListener() {
		mHot.setOnClickListener(this);
		mCategory.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		BaseFragment fragment = null;
		switch (v.getId()) {
			case R.id.question_hot:
				fragment = QuestionHotFragment.newInstance();
				break;
			case R.id.question_category:
				fragment = QuestionCategoryFragment.newInstance();
				break;
			default:
				break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			transaction.replace(R.id.rl_container, fragment);
			transaction.commit();
		}
	}

}

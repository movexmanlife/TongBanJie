package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.adapter.QuestionAdapter;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.util.PackageUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 帮助与反馈
 */
public class HelpAndFeedbackActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = HelpAndFeedbackActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.help_feedback_question)
	CommonItem mQuestion;
	@Bind(R.id.help_feedback_feedback)
	CommonItem mFeedback;
	@Bind(R.id.help_feedback_custom_service)
	CommonItem mCustom_service;

	public static void start(Context context) {
		Intent intent = new Intent(context, HelpAndFeedbackActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_help_and_feedback);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitleBar.initTitleWithLeftTxtDrawable("帮助与反馈", "返回", R.drawable.btn_back_sel, 5);
		mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		mQuestion.setType(Type.SummaryTxt_DetailImg);
		mQuestion.setSummaryText("常见问题");
		mQuestion.setDetailImg(R.mipmap.icon_triangle_arrow);

		mFeedback.setType(Type.SummaryTxt_DetailImg);
		mFeedback.setSummaryText("意见反馈");
		mFeedback.setDetailImg(R.mipmap.icon_triangle_arrow);

		mCustom_service.setType(Type.SummaryTxt_DetailImg);
		mCustom_service.setSummaryText("联系我们");
		mCustom_service.setDetailImg(R.mipmap.icon_triangle_arrow);
	}

	@Override
	public void setListener() {
		mQuestion.setOnClickListener(this);
		mFeedback.setOnClickListener(this);
		mCustom_service.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.help_feedback_question:
				FrequentQuestionActivity.start(this);
				break;
			case R.id.help_feedback_feedback:
				FeedbackActivity.start(this);
				break;
			case R.id.help_feedback_custom_service:
				CustomServiceActivity.start(this);
				break;
			default:
				break;
		}
	}

}

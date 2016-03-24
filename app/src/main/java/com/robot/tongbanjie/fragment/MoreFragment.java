package com.robot.tongbanjie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.activity.CheckPhoneActivity;
import com.robot.tongbanjie.activity.EvaluateRiskActivity;
import com.robot.tongbanjie.activity.FeedbackActivity;
import com.robot.tongbanjie.activity.HelpAndFeedbackActivity;
import com.robot.tongbanjie.activity.SettingActivity;
import com.robot.tongbanjie.dialog.EvaluateDialog;
import com.robot.tongbanjie.util.MarketUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MoreFragment extends BaseFragment implements OnClickListener {
	private static final String TAG = MoreFragment.class.getSimpleName();
	private static final String TONG_BAN_JIE_PACKAGE_NAME = "com.tongbanjie.android";
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.more_invest_mode)
	CommonItem mInvestMode;
	@Bind(R.id.more_recommend)
	CommonItem mRecommend;
	@Bind(R.id.more_help_and_feedback)
	CommonItem mHelpAndFeedback;
	@Bind(R.id.more_score)
	CommonItem mScore;
	@Bind(R.id.more_setting)
	CommonItem mSetting;
	@Bind(R.id.more_about)
	CommonItem mAbout;

	public static MoreFragment newInstance() {
		MoreFragment fragment = new MoreFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_more, null);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitleBar.initOnlyTitle("更多");
	}

	@Override
	public void initView() {
		mInvestMode.setType(Type.All);
		mInvestMode.setSummaryImg(R.mipmap.icon_riskstyle);
		mInvestMode.setSummaryText("投资风格");
		mInvestMode.setDetailText("未测评");
		mInvestMode.setDetailImg(R.mipmap.icon_triangle_arrow);

		mRecommend.setType(Type.All);
		mRecommend.setSummaryImg(R.drawable.icon_hongbao);
		mRecommend.setSummaryText("推荐有奖");
		mRecommend.setDetailText("送好友8888理财金");
		mRecommend.setDetailImg(R.mipmap.icon_triangle_arrow);

		mHelpAndFeedback.setType(Type.SummaryImgSummaryTxt_DetailImg);
		mHelpAndFeedback.setSummaryImg(R.drawable.icon_service);
		mHelpAndFeedback.setSummaryText("帮助与反馈");
		mHelpAndFeedback.setDetailImg(R.mipmap.icon_triangle_arrow);

		mScore.setType(Type.SummaryImgSummaryTxt_DetailImg);
		mScore.setSummaryImg(R.drawable.icon_evaluate);
		mScore.setSummaryText("评价一下");
		mScore.setDetailImg(R.mipmap.icon_triangle_arrow);

		mSetting.setType(Type.All);
		mSetting.setSummaryImg(R.drawable.icon_more_settings);
		mSetting.setSummaryText("设置");
		mSetting.getDetailTextView().setText("");
		mSetting.getDetailTextView().setBackgroundResource(R.drawable.ic_indicators_now);
		mSetting.setDetailImg(R.mipmap.icon_triangle_arrow);

		mAbout.setType(Type.SummaryImgSummaryTxt_DetailImg);
		mAbout.setSummaryImg(R.drawable.icon_about_tbj);
		mAbout.setSummaryText("了解我们");
		mAbout.setDetailImg(R.mipmap.icon_triangle_arrow);
	}

	@Override
	public void setListener() {
		mInvestMode.setOnClickListener(this);
		mRecommend.setOnClickListener(this);
		mHelpAndFeedback.setOnClickListener(this);
		mScore.setOnClickListener(this);
		mSetting.setOnClickListener(this);
		mAbout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.more_invest_mode:
				CheckPhoneActivity.start(getActivity(), true, "");
				break;
			case R.id.more_recommend:
//				new TipsDialog(getActivity()).builder()
//						.setTitle("提示").setContentMsg("您尚未认证身份信息\n请添加银行卡进行身份认证")
//						.setNegativeBtn("取消", new TipsDialog.OnNegativeListener() {
//							@Override
//							public void onNegative(View view) {
//								ToastUtils.showShort("取消");
//							}
//						})
//						.setPositiveBtn("添加银行卡", new TipsDialog.OnPositiveListener() {
//							@Override
//							public void onPositive(View view) {
//								ToastUtils.showShort("添加银行卡");
//							}
//						})
//						.show();
				EvaluateRiskActivity.start(getActivity());
				break;
			case R.id.more_help_and_feedback:
				HelpAndFeedbackActivity.start(getActivity());
				break;
			case R.id.more_score:
				final EvaluateDialog evaluateDialog = new EvaluateDialog(getActivity(), R.style.EvaluateDialog);
				evaluateDialog.setOnCancelListener(new EvaluateDialog.OnCancelListener() {
					@Override
					public void onCancel(View v) {
					}
				});
				evaluateDialog.setOnStoreListener(new EvaluateDialog.OnStoreListener() {
					@Override
					public void onStore(View v) {
						MarketUtils.searchAppByPkgName(getActivity(), TONG_BAN_JIE_PACKAGE_NAME);
					}
				});
				evaluateDialog.setOnFeedbackListener(new EvaluateDialog.OnFeedbackListener() {
					@Override
					public void onFeedback(View v) {
						FeedbackActivity.start(getActivity());
					}
				});
				evaluateDialog.show();
				break;
			case R.id.more_setting:
				SettingActivity.start(getActivity());
				break;
			case R.id.more_about:
				break;
			default:
				break;
		}
	}

}

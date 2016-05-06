package com.robot.tongbanjie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.activity.BankCardAddActivity;
import com.robot.tongbanjie.activity.EvaluateRiskActivity;
import com.robot.tongbanjie.activity.FeedbackActivity;
import com.robot.tongbanjie.activity.HelpAndFeedbackActivity;
import com.robot.tongbanjie.activity.SettingActivity;
import com.robot.tongbanjie.dialog.CommonDialog;
import com.robot.tongbanjie.dialog.EvaluateDialog;
import com.robot.tongbanjie.util.MarketUtils;
import com.robot.tongbanjie.util.ToastUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MoreFragment extends BaseFragment implements OnClickListener {
    private static final String TAG = MoreFragment.class.getSimpleName();
    private static final String TONG_BAN_JIE_PACKAGE_NAME = "com.tongbanjie.android";
    @Bind(R.id.more_invest_mode)
    CommonItem mInvestMode;
    @Bind(R.id.more_help_and_feedback)
    CommonItem mHelpAndFeedback;
    @Bind(R.id.more_score)
    CommonItem mScore;
    @Bind(R.id.more_setting)
    CommonItem mSetting;
    @Bind(R.id.more_about)
    CommonItem mAbout;
    @Bind(R.id.rl_vip_member)
    RelativeLayout mVipMember;
    @Bind(R.id.rl_task_center)
    RelativeLayout mTaskCenter;
    @Bind(R.id.tv_full_name)
    TextView tvFullName;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.iv_level_icon)
    ImageView ivLevelIcon;
    @Bind(R.id.tv_level_tip)
    TextView tvLevelTip;
    @Bind(R.id.ll_level_des)
    LinearLayout llLevelDes;
    @Bind(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @Bind(R.id.tv_vip_member)
    TextView tvVipMember;
    @Bind(R.id.tv_task_center)
    TextView tvTaskCenter;

    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        mInvestMode.setType(Type.All);
        mInvestMode.setSummaryImg(R.drawable.icon_riskstyle);
        mInvestMode.setSummaryText("投资风格");
        mInvestMode.setDetailText("未测评");
        mInvestMode.setDetailImg(R.mipmap.icon_triangle_arrow);

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
        setIndicatorSize(mSetting.getDetailTextView());
        mSetting.getDetailTextView().setBackgroundResource(R.drawable.bg_indicator);
        mSetting.setDetailImg(R.mipmap.icon_triangle_arrow);

        mAbout.setType(Type.SummaryImgSummaryTxt_DetailImg);
        mAbout.setSummaryImg(R.drawable.icon_about_tbj);
        mAbout.setSummaryText("了解我们");
        mAbout.setDetailImg(R.mipmap.icon_triangle_arrow);
    }

    private void setIndicatorSize(TextView textView) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        lp.width = (int) getActivity().getResources().getDimension(R.dimen.indicator_width);
        lp.height = (int) getActivity().getResources().getDimension(R.dimen.indicator_width);
        textView.setLayoutParams(lp);
    }

    @Override
    public void setListener() {
        tvFullName.setOnClickListener(this);
        mVipMember.setOnClickListener(this);
        mTaskCenter.setOnClickListener(this);
        mInvestMode.setOnClickListener(this);
        mHelpAndFeedback.setOnClickListener(this);
        mScore.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more_invest_mode:
//				CheckPhoneActivity.start(getActivity(), true, "");
                EvaluateRiskActivity.start(getActivity());
                break;
            case R.id.rl_vip_member:
                break;
            case R.id.rl_task_center:
                break;
			case R.id.tv_full_name:
				new CommonDialog(getActivity()).builder()
						.setTitle("提示").setContentMsg("您尚未认证身份信息\n请添加银行卡进行身份认证")
						.setNegativeBtn("取消", new CommonDialog.OnNegativeListener() {
							@Override
							public void onNegative(View view) {
								ToastUtils.showShort("取消");
							}
						})
						.setPositiveBtn("添加银行卡", new CommonDialog.OnPositiveListener() {
							@Override
							public void onPositive(View view) {
                                BankCardAddActivity.start(getActivity());
							}
						})
						.show();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

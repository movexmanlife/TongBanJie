package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.ClipboardUtils;
import com.robot.tongbanjie.util.IntentUtils;
import com.robot.tongbanjie.util.ToastUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * 联系界面
 */
public class ContactActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = ContactActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.userinfo_mail)
	CommonItem mMail;
	@Bind(R.id.userinfo_qq)
	CommonItem mQQ;


	public static void start(Context context) {
		Intent intent = new Intent(context, ContactActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_contact);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitleBar.initTitleWithLeftTxtDrawable("联系方式", "返回", R.drawable.btn_back_sel, 5);
		mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		setCommonItem(mMail, "邮箱", "940350851@qq.com");
		setCommonItem(mQQ, "QQ群", "230274309");
	}

	private void setCommonItem(CommonItem commonItem, String summaryTxt, String detailTxt) {
		commonItem.setType(Type.SummaryTxt_DetailTxtDetailImg);
		commonItem.setSummaryText(summaryTxt);
		commonItem.setDetailText(detailTxt);
		commonItem.setDetailImg(R.mipmap.icon_triangle_arrow);
	}

	@Override
	public void setListener() {
		mMail.setOnClickListener(this);
		mQQ.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.userinfo_mail:
				IntentUtils.sendEmail(this);
				break;
			case R.id.userinfo_qq:
				ClipboardUtils.copyToClipboard(this, mQQ.getDetailText().toString());
				ToastUtils.showShort("复制QQ群号码成功！");
				IntentUtils.gotoQQ(this);
				break;
			default:
				break;
		}
	}
}

package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.CommonDialog;
import com.robot.tongbanjie.util.ClipboardUtils;
import com.robot.tongbanjie.util.IntentUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CustomServiceActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = CustomServiceActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.service_weixin)
	CommonItem mWeinxin;
	@Bind(R.id.service_qq)
	CommonItem mQQ;
	@Bind(R.id.service_sina)
	CommonItem mSina;
	@Bind(R.id.service_website)
	CommonItem mWebsite;
	@Bind(R.id.custom_service_phone_number)
	TextView mPhoneNumber;
	@Bind(R.id.call_btn)
	Button mCallBtn;


	public static void start(Context context) {
		Intent intent = new Intent(context, CustomServiceActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_custom_service);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {
	}

	@Override
	public void initTitle() {
		mTitleBar.initTitleWithLeftTxtDrawable("联系我们", "返回", R.drawable.btn_back_sel, 5);
		mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		mPhoneNumber.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/TT0959M.TTF"));
		mWeinxin.setType(Type.SummaryImgSummaryTxt_DetailImg);
		mWeinxin.setSummaryImg(R.drawable.weixin_logo);
		mWeinxin.setSummaryText("微信订阅号:itongbanjie");
		mWeinxin.setDetailImg(R.mipmap.icon_triangle_arrow);

		mQQ.setType(Type.SummaryImgSummaryTxt_DetailImg);
		mQQ.setSummaryImg(R.drawable.qq_logo);
		mQQ.setSummaryText("企业QQ:4008559600");
		mQQ.setDetailImg(R.mipmap.icon_triangle_arrow);

		mSina.setType(Type.SummaryImgSummaryTxt_DetailImg);
		mSina.setSummaryImg(R.drawable.sina_logo);
		mSina.setSummaryText("新浪微博:@铜板街");
		mSina.setDetailImg(R.mipmap.icon_triangle_arrow);
		
		mWebsite.setType(Type.SummaryImgSummaryTxt_DetailImg);
		mWebsite.setSummaryImg(R.drawable.icon_website);
		mWebsite.setSummaryText("官网:www.tongbanjie.com");
		mWebsite.setDetailImg(R.mipmap.icon_triangle_arrow);
	}

	@Override
	public void setListener() {
		mCallBtn.setOnClickListener(this);
		mWeinxin.setOnClickListener(this);
		mQQ.setOnClickListener(this);
		mSina.setOnClickListener(this);
		mWebsite.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.call_btn:
				new CommonDialog(this).builder().setTitle("提示")
						.setContentMsg("拨打铜板街客服电话")
						.setNegativeBtn("取消", null)
						.setPositiveBtn("确定", new CommonDialog.OnPositiveListener() {
							@Override
							public void onPositive(View view) {
								IntentUtils.gotoCall(CustomServiceActivity.this, mPhoneNumber.getText().toString());
							}
						}).show();
				break;
			case R.id.service_weixin:
				ClipboardUtils.copyToClipboard(this, getCommonItemSummaryText((CommonItem)view));
				break;
			case R.id.service_qq:
				ClipboardUtils.copyToClipboard(this, getCommonItemSummaryText((CommonItem)view));
				break;
			case R.id.service_sina:
				ClipboardUtils.copyToClipboard(this, getCommonItemSummaryText((CommonItem)view));
				break;
			case R.id.service_website:
				ClipboardUtils.copyToClipboard(this, getCommonItemSummaryText((CommonItem)view));
				break;
			default:
				break;
		}
	}

	private String getCommonItemSummaryText(CommonItem commonItem) {
		String allText =commonItem.getSummaryText().toString();
		int index = allText.indexOf(':');
		String resultText = allText.substring(index + 1);
		if (resultText.charAt(0) == '@') {
			resultText = resultText.substring(1);
		}
		Toast.makeText(this, resultText, Toast.LENGTH_SHORT).show();
		return resultText;
	}
}

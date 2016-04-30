package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BankCardAddActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = BankCardAddActivity.class.getSimpleName();

	@Bind(R.id.titlebar)
	TitleBarView mTitlebar;
	@Bind(R.id.my_bank_card)
	RadioButton mMyBankCard;
	@Bind(R.id.fund_bank_card)
	RadioButton mFundBankCard;
	@Bind(R.id.bank_card_manager_add)
	CommonItem mAddBtn;

	public static void start(Context context) {
		Intent intent = new Intent(context, BankCardAddActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_bank_card_manager);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitlebar.initTitleWithLeftTxtDrawable("银行卡管理", "返回", R.drawable.btn_back_sel, 5);
		mTitlebar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		mMyBankCard.setChecked(true);
		mAddBtn.setType(Type.SummaryImgSummaryTxt_DetailImg);
		mAddBtn.setSummaryImg(R.drawable.icon_add);
		mAddBtn.setSummaryText("添加银行卡");
		mAddBtn.setDetailImg(R.mipmap.icon_triangle_arrow);
		mAddBtn.getSummaryTextView().setTextColor(getResources().getColor(R.color.add_bank_card_color));
	}

	@Override
	public void setListener() {
		mMyBankCard.setOnClickListener(this);
		mFundBankCard.setOnClickListener(this);
		mAddBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.my_bank_card:
				break;
			case R.id.fund_bank_card:
				break;
			case R.id.bank_card_manager_add:
				BankCardSelectedActivity.start(this);
				break;
			default:
				break;
		}
	}

}

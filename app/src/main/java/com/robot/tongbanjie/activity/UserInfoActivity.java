package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.BaseWheelFragment;
import com.robot.tongbanjie.dialog.CommonWheelSelectedDialog;
import com.robot.tongbanjie.dialog.InputDialog;
import com.robot.tongbanjie.dialog.WeightWheelSelectedDialog;
import com.robot.tongbanjie.util.SoftKeyboardUtils;
import com.robot.tongbanjie.util.ToastUtils;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * 编辑个人资料
 */
public class UserInfoActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = UserInfoActivity.class.getSimpleName();
	@Bind(R.id.titlebar)
	TitleBarView mTitleBar;
	@Bind(R.id.userinfo_nick)
	CommonItem mNick;
	@Bind(R.id.userinfo_sign)
	CommonItem mSign;
	@Bind(R.id.userinfo_gender)
	CommonItem mGender;
	@Bind(R.id.userinfo_age)
	CommonItem mAge;
	@Bind(R.id.userinfo_tall)
	CommonItem mTall;
	@Bind(R.id.userinfo_weight)
	CommonItem mWeight;
	@Bind(R.id.userinfo_contact)
	CommonItem mContact;


	public static void start(Context context) {
		Intent intent = new Intent(context, UserInfoActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_user_info);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initTitle() {
		mTitleBar.initTitleWithLeftTxtDrawable("编辑个人资料", "返回", R.drawable.btn_back_sel, 5);
		mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		setCommonItem(mNick, "昵称", "50% percent pain");
		setCommonItem(mSign, "个性签名", "coding for fun!");
		setCommonItem(mGender, "性别", "男");
		setCommonItem(mAge, "年龄", "27");
		setCommonItem(mTall, "身高", "170 cm");
		setCommonItem(mWeight, "体重", "65.0 kg");
		setCommonItem(mContact, "联系方式", null);
	}

	private void setCommonItem(CommonItem commonItem, String summaryTxt, String detailTxt) {
		commonItem.setType(Type.SummaryTxt_DetailTxtDetailImg);
		commonItem.setSummaryText(summaryTxt);
		commonItem.setDetailText(detailTxt);
		commonItem.setDetailImg(R.mipmap.icon_triangle_arrow);
	}

	@Override
	public void setListener() {
		mNick.setOnClickListener(this);
		mSign.setOnClickListener(this);
		mGender.setOnClickListener(this);
		mAge.setOnClickListener(this);
		mTall.setOnClickListener(this);
		mWeight.setOnClickListener(this);
		mContact.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.userinfo_nick:
				inputNickName();
				break;
			case R.id.userinfo_sign:
				inputSign();
				break;
			case R.id.userinfo_gender:
				genderSelected();
				break;
			case R.id.userinfo_age:
				ageSelected();
				break;
			case R.id.userinfo_tall:
				tallSelected();
				break;
			case R.id.userinfo_weight:
				weightSelected();
				break;
			case R.id.userinfo_contact:
				ContactActivity.start(this);
				break;
			default:
				break;
		}
	}

	private void inputNickName() {
		InputDialog inputDialog = new InputDialog(this).builder();
		inputDialog.getContentView().setSingleLine(true);
		inputDialog.setTitle(mNick.getSummaryText().toString())
				.setPositiveBtn("确定", new InputDialog.OnPositiveListener() {
					@Override
					public void onPositive(View view, String inputMsg) {
						mNick.setDetailText(inputMsg);
					}
				})
				.setNegativeBtn("取消", null)
				.setContentMsg(mNick.getDetailText().toString());
		inputDialog.getContentView().setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
		inputDialog.show();
	}

	private void inputSign() {
		InputDialog inputDialog = new InputDialog(this).builder().setTitle(mSign.getSummaryText().toString())
				.setPositiveBtn("确定", new InputDialog.OnPositiveListener() {
					@Override
					public void onPositive(View view, String inputMsg) {
						mSign.setDetailText(inputMsg);
					}
				})
				.setNegativeBtn("取消", null)
				.setContentMsg(mSign.getDetailText().toString());
		inputDialog.getContentView().setHint("请输入50字以内的个性签名");
		inputDialog.getContentView().setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
		inputDialog.show();
	}

	private void genderSelected() {
		CommonWheelSelectedDialog dialog = CommonWheelSelectedDialog.newInstance(this, null, ViewGroup.LayoutParams.WRAP_CONTENT, CommonWheelSelectedDialog.Type.Gender);
		dialog.show(getSupportFragmentManager(), "CommonWheelSelectedDialog_gender");
		dialog.setSelection(mGender.getDetailText().toString());
		dialog.setOnSureListener(new BaseWheelFragment.OnSureListener() {
			@Override
			public void doSure(String item) {
				ToastUtils.showShort(item);
				mGender.setDetailText(item);
			}
		});
	}

	private void ageSelected() {
		CommonWheelSelectedDialog dialog = CommonWheelSelectedDialog.newInstance(this, null, ViewGroup.LayoutParams.WRAP_CONTENT, CommonWheelSelectedDialog.Type.Age);
		dialog.show(getSupportFragmentManager(), "CommonWheelSelectedDialog_age");
		dialog.setSelection(mAge.getDetailText().toString());
		dialog.setOnSureListener(new BaseWheelFragment.OnSureListener() {
			@Override
			public void doSure(String item) {
				ToastUtils.showShort(item);
				mAge.setDetailText(item);
			}
		});
	}

	private void tallSelected() {
		CommonWheelSelectedDialog dialog = CommonWheelSelectedDialog.newInstance(this, null, ViewGroup.LayoutParams.WRAP_CONTENT, CommonWheelSelectedDialog.Type.Tall);
		dialog.show(getSupportFragmentManager(), "CommonWheelSelectedDialog_tall");
		dialog.setSelection(mTall.getDetailText().toString());
		dialog.setOnSureListener(new BaseWheelFragment.OnSureListener() {
			@Override
			public void doSure(String item) {
				ToastUtils.showShort(item);
				mTall.setDetailText(item);
			}
		});
	}

	private void weightSelected() {
		WeightWheelSelectedDialog dialog = WeightWheelSelectedDialog.newInstance(this, null, ViewGroup.LayoutParams.WRAP_CONTENT);
		dialog.show(getSupportFragmentManager(), "WeightWheelSelectedDialog");
		dialog.setSelection(mWeight.getDetailText().toString());
		dialog.setOnSureListener(new BaseWheelFragment.OnSureListener() {
			@Override
			public void doSure(String item) {
				ToastUtils.showShort(item);
				mWeight.setDetailText(item);
			}
		});
	}

}

package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.adapter.BankSelectedAdapter;
import com.robot.tongbanjie.entity.BankInfo;
import com.robot.tongbanjie.widget.CommonItem;
import com.robot.tongbanjie.widget.CommonItem.Type;
import com.robot.tongbanjie.widget.TitleBarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BankCardSelectedActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = BankCardSelectedActivity.class.getSimpleName();
	private static final String FILE_NAME = "bankresource.txt";
	@Bind(R.id.titlebar)
	TitleBarView mTitlebar;
	@Bind(R.id.listview)
	ListView listview;
	private BankSelectedAdapter mBankSelectedAdapter;
	private List<BankInfo> mBankInfoList;

	public static void start(Context context) {
		Intent intent = new Intent(context, BankCardSelectedActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_bank_card_select);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initData() {
		String content = getFromAssets(FILE_NAME);
		if (TextUtils.isEmpty(content)) {
			return;
		}

		mBankInfoList = new ArrayList<BankInfo>();
		try {
			JSONObject object = new JSONObject(content);
			Iterator<?> iterator = object.keys();
			while(iterator.hasNext()) {
				String code = iterator.next().toString();
				if (code != null) {
					String name = object.getString(code);
					mBankInfoList.add(new BankInfo(code, name, false));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mBankSelectedAdapter = new BankSelectedAdapter(this, mBankInfoList);
	}

	@Override
	public void initTitle() {
		mTitlebar.initTitleWithLeftTxtDrawable("添加银行卡", "返回", R.drawable.btn_back_sel, 5);
		mTitlebar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		listview.setAdapter(mBankSelectedAdapter);
	}

	@Override
	public void setListener() {
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String lastSeleced = mBankSelectedAdapter.getSelectedBankName();
				String nowSeleced = mBankInfoList.get(position).name;

				if (!TextUtils.equals(lastSeleced, nowSeleced)) {
					mBankSelectedAdapter.setSelectedBankName(nowSeleced);
					mBankSelectedAdapter.notifyDataSetChanged();
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
	}

	public String getFromAssets(String fileName) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.adapter.AccountQuestionAdapter;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class QuestionAccountActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = QuestionAccountActivity.class.getSimpleName();
	private static final String KEY_TITLE = "title";
	@Bind(R.id.titlebar)
	TitleBarView mTitlebar;
	@Bind(R.id.account_listview)
	ExpandableListView mListView;
	AccountQuestionAdapter mAccountQuestionAdapter;
	private String mTitle;

	public static void start(Context context, String title) {
		Intent intent = new Intent(context, QuestionAccountActivity.class);
		intent.putExtra(KEY_TITLE, title);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getArgs();
		setContentView(R.layout.activity_question_account);
		ButterKnife.bind(this);
		super.onCreate(savedInstanceState);
	}

	private void getArgs() {
		Bundle bundle = getIntent().getExtras();
		mTitle = bundle.getString(KEY_TITLE);
	}

	@Override
	public void initData() {
		mAccountQuestionAdapter = new AccountQuestionAdapter(this);
	}

	@Override
	public void initTitle() {
		mTitlebar.initTitleWithLeftTxtDrawable(mTitle, "返回", R.drawable.btn_back_sel, 5);
		mTitlebar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
			@Override
			public void onClick() {
				finish();
			}
		});
	}

	@Override
	public void initView() {
		mListView.setAdapter(mAccountQuestionAdapter);
		mListView.setGroupIndicator(null);
	}

	@Override
	public void setListener() {
		mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				mListView.collapseGroup(groupPosition);
				return false;
			}
		});
		mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				for (int i = 0; i < mAccountQuestionAdapter.getGroupCount(); i++) {
					if (groupPosition != i && mListView.isGroupExpanded(groupPosition)) {
						mListView.collapseGroup(i);
					}
				}
			}
		});
	}

	@Override
	public void onClick(View v) {

	}

}

package com.robot.tongbanjie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.adapter.QuestionAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 热门问题
 */
public class QuestionHotFragment extends BaseFragment {
    @Bind(R.id.question_listview)
    ExpandableListView mListView;
    QuestionAdapter mQuestionAdapter;

    public static QuestionHotFragment newInstance() {
        QuestionHotFragment fragment = new QuestionHotFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_hot, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        mQuestionAdapter = new QuestionAdapter(getActivity());
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        mListView.setAdapter(mQuestionAdapter);
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
                for (int i = 0; i < mQuestionAdapter.getGroupCount(); i++) {
                    if (groupPosition != i && mListView.isGroupExpanded(groupPosition)) {
                        mListView.collapseGroup(i);
                    }
                }
            }
        });
    }
}

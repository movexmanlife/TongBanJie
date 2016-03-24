package com.robot.tongbanjie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.activity.QuestionAccountActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 问题分类
 */
public class QuestionCategoryFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.account_txt)
    TextView accountTxt;
    @Bind(R.id.account_layout)
    LinearLayout accountLayout;
    @Bind(R.id.product_txt)
    TextView productTxt;
    @Bind(R.id.product_layout)
    LinearLayout productLayout;
    @Bind(R.id.invest_txt)
    TextView investTxt;
    @Bind(R.id.invest_layout)
    LinearLayout investLayout;
    @Bind(R.id.earn_txt)
    TextView earnTxt;
    @Bind(R.id.earn_layout)
    LinearLayout earnLayout;
    @Bind(R.id.discount_txt)
    TextView discountTxt;
    @Bind(R.id.discount_layout)
    LinearLayout discountLayout;
    @Bind(R.id.desc_txt)
    TextView descTxt;
    @Bind(R.id.desc_layout)
    LinearLayout descLayout;
    @Bind(R.id.notarization_txt)
    TextView notarizationTxt;
    @Bind(R.id.notarization_layout)
    LinearLayout notarizationLayout;

    public static QuestionCategoryFragment newInstance() {
        QuestionCategoryFragment fragment = new QuestionCategoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_category, null);
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

    }

    @Override
    public void setListener() {
        accountLayout.setOnClickListener(this);
        productLayout.setOnClickListener(this);
        investLayout.setOnClickListener(this);
        earnLayout.setOnClickListener(this);
        discountLayout.setOnClickListener(this);
        descLayout.setOnClickListener(this);
        notarizationLayout.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_layout:
                QuestionAccountActivity.start(getActivity(), accountTxt.getText().toString());
                break;
            case R.id.product_layout:
                QuestionAccountActivity.start(getActivity(), productTxt.getText().toString());
                break;
            case R.id.invest_layout:
                QuestionAccountActivity.start(getActivity(), investTxt.getText().toString());
                break;
            case R.id.earn_layout:
                QuestionAccountActivity.start(getActivity(), earnTxt.getText().toString());
                break;
            case R.id.discount_layout:
                QuestionAccountActivity.start(getActivity(), discountTxt.getText().toString());
                break;
            case R.id.desc_layout:
                QuestionAccountActivity.start(getActivity(), descTxt.getText().toString());
                break;
            case R.id.notarization_layout:
                QuestionAccountActivity.start(getActivity(), notarizationTxt.getText().toString());
                break;
            default:
                break;
        }
    }
}

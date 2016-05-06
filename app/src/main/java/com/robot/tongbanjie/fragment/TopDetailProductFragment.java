package com.robot.tongbanjie.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.activity.InvestPeopleActivity;
import com.robot.tongbanjie.entity.InvestPeople;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class TopDetailProductFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.txt_arrow)
    TextView txtArrow;
    @Bind(R.id.invest_number_layout)
    LinearLayout investNumberLayout;

    public enum Direction {
        Up, Down
    }

    public static TopDetailProductFragment newInstance() {
        TopDetailProductFragment fragment = new TopDetailProductFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_product, null);
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
        investNumberLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.invest_number_layout:
                InvestPeopleActivity.start(getActivity());
                break;
        }
    }

    public void setPageIndicator(Direction direction) {
        if (direction == Direction.Up) {
            setTextViewLeftImage(txtArrow, R.drawable.detail_up);
        } else {
            setTextViewLeftImage(txtArrow, R.drawable.detail_down);
        }
    }

    private void setTextViewLeftImage(TextView textView, int resId) {
        Drawable drawable = textView.getContext().getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawablePadding((int)getResources().getDimension(R.dimen.txt_arrow));
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}

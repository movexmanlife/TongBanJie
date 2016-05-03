package com.robot.tongbanjie.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.stone.verticalslide.CustScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class DetailProductCategoryFragment extends BaseFragment {

    @Bind(R.id.return_tips)
    TextView returnTips;
    @Bind(R.id.custScrollView)
    CustScrollView custScrollView;

    public static DetailProductCategoryFragment newInstance() {
        DetailProductCategoryFragment fragment = new DetailProductCategoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_product_category, null);
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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_tips_return);
        ImageSpan imageSpan = new ImageSpan(getActivity(), bitmap);
        SpannableString spannableString = new SpannableString("*");
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        returnTips.setText(spannableString);
        returnTips.append("因参加本服务计划而涉及的相关税费，投资人许自行申报及缴纳");
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

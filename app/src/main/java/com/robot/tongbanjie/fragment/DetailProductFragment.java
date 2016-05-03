package com.robot.tongbanjie.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.DensityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class DetailProductFragment extends BaseFragment {

    @Bind(R.id.txt_arrow)
    TextView txtArrow;

    public enum Direction {
        Up, Down
    }

    public static DetailProductFragment newInstance() {
        DetailProductFragment fragment = new DetailProductFragment();
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

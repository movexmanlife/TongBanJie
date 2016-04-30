package com.robot.tongbanjie.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;

public class SingleChoiceListItemView extends LinearLayout implements Checkable {
    private TextView nameTxt;
    private CheckBox selectedCheckBox;

    public SingleChoiceListItemView(Context context) {
        super(context);
        init();
    }

    public SingleChoiceListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SingleChoiceListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View root = inflater.inflate(R.layout.list_list_single_choice, this, true);
        nameTxt = (TextView) root.findViewById(R.id.txt);
        selectedCheckBox = (CheckBox) root.findViewById(R.id.checkbox);
    }

    public TextView getNameView() {
        return nameTxt;
    }

    public void setName(String text) {
        nameTxt.setText(text);
    }
 
    @Override
    public boolean isChecked() {
        return selectedCheckBox.isChecked();
    }
 
    @Override
    public void setChecked(boolean checked) {
        selectedCheckBox.setChecked(checked);
        //根据是否选中来选择不同的背景图片
        if (checked) {
            nameTxt.setTextColor(getResources().getColor(R.color.txt_red));
        } else {
            nameTxt.setTextColor(getResources().getColor(R.color.txt_gray));
        }
    }
 
    @Override
    public void toggle() {
        selectedCheckBox.toggle();
    }
 
}
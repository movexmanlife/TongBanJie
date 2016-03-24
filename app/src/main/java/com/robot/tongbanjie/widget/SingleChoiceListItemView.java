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
        View roobt = inflater.inflate(R.layout.list_list_single_choice, this, false);
        nameTxt = (TextView) roobt.findViewById(R.id.txt);
        selectedCheckBox = (CheckBox) roobt.findViewById(R.id.checkbox);
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
            selectedCheckBox.setBackgroundResource(R.drawable.tick);
        } else {
            selectedCheckBox.setBackgroundDrawable(null);
        }
    }
 
    @Override
    public void toggle() {
        selectedCheckBox.toggle();
    }
 
}
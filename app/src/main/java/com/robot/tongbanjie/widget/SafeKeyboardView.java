package com.robot.tongbanjie.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.DensityUtils;

public class SafeKeyboardView extends LinearLayout{
    public SafeKeyboardView(Context context) {
        super(context);
        initView();
    }

    public SafeKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SafeKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(VERTICAL);
        setPadding(DensityUtils.dip2px(getContext(), 20), DensityUtils.dip2px(getContext(), 15), DensityUtils.dip2px(getContext(), 20), DensityUtils.dip2px(getContext(), 10));
        addHeader();
        addGridPasswdView();
        setBackgroundColor(Color.parseColor("#282828"));
    }

    private void addHeader() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.view_keyboard_header, this);
    }

    private void addGridPasswdView() {
        LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.gravity  = Gravity.CENTER_HORIZONTAL;
        GridView gridView = new GridView(getContext());
        gridView.setBackgroundColor(Color.parseColor("#282828"));
        gridView.setGravity(Gravity.CENTER_HORIZONTAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setLayoutParams(lp);
        gridView.setNumColumns(3);
        gridView.setHorizontalSpacing(DensityUtils.dip2px(getContext(), 10));
        gridView.setVerticalSpacing(DensityUtils.dip2px(getContext(), 10));
        addView(gridView);
        gridView.setAdapter(new KeyAdapter(getContext()));

    }

     public static class KeyAdapter extends BaseAdapter {
         private static final String KEY_CONFIRM = "确定";
         private static final String KEY_DEL = "删除";
         private String[] keys = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", KEY_CONFIRM, "0", KEY_DEL};
         private Context mContext;
         public KeyAdapter(Context context) {
             mContext = context;
         }
         @Override
         public int getCount() {
             return keys.length;
         }

         @Override
         public Object getItem(int position) {
             return keys[position];
         }

         @Override
         public long getItemId(int position) {
             return position;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
             ViewHolder viewHolder = null;
             if (convertView == null) {
                 convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_grid_passwd, parent, false);
                 viewHolder = new ViewHolder();
                 viewHolder.number = (TextView) convertView.findViewById(R.id.number);
                 viewHolder.img = (ImageView) convertView.findViewById(R.id.img);
                 convertView.setTag(viewHolder);
             } else {
                 viewHolder = (ViewHolder)convertView.getTag();
             }

             String key = keys[position];
             if (TextUtils.equals(KEY_DEL, key)) {
                 viewHolder.number.setText(null);
                 viewHolder.img.setVisibility(View.VISIBLE);
             } else {
                 viewHolder.number.setText(key);
                 viewHolder.img.setVisibility(View.GONE);
                 if (TextUtils.equals(KEY_CONFIRM, key)) {
                     viewHolder.number.setTextSize(14);
                     TextPaint textPaint = viewHolder.number.getPaint();
                     textPaint.setFakeBoldText(true);
                 } else {
                     viewHolder.number.setTextSize(20);
                     TextPaint textPaint = viewHolder.number.getPaint();
                     textPaint.setFakeBoldText(false);
                 }
             }

             return convertView;
         }

         static class ViewHolder {
             TextView number;
             ImageView img;
         }
     }

}

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SafeKeyboardView extends LinearLayout {
    private OnAddListener mOnAddListener;
    private OnConfirmListener mOnConfirmListener;
    private OnDeleteListener mOnDeleteListener;
    private KeyAdapter mKeyAdapter;
    public enum Mode {
        Normal,
        Shuffle
    }

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
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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
        mKeyAdapter = new KeyAdapter(getContext());
        gridView.setAdapter(mKeyAdapter);
    }

    public void shuffle() {
        mKeyAdapter.shuffle();
        mKeyAdapter.notifyDataSetChanged();
    }

     public class KeyAdapter extends BaseAdapter {
         public static final String KEY_CONFIRM = "确定";
         public static final String KEY_DEL = "删除";

         private final String[] numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
         private List<String> keyList = new ArrayList<String>(Arrays.asList(numbers));
         private Context mContext;

         public KeyAdapter(Context context) {
             mContext = context;
             insertSpecialKey();
         }

         private void insertSpecialKey() {
             keyList.add(KEY_CONFIRM);
             Collections.swap(keyList, keyList.size() - 2, keyList.size() -1);
             keyList.add(KEY_DEL);
         }

         private void removeSpecialKey() {
             Collections.swap(keyList, keyList.size() - 3, keyList.size() - 2);
             keyList.remove(keyList.size() - 1);
             keyList.remove(keyList.size() - 1);
         }

         @Override
         public int getCount() {
             return keyList.size();
         }

         @Override
         public Object getItem(int position) {
             return keyList.get(position);
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

             String key = keyList.get(position);
             viewHolder.number.setOnClickListener(new NumberClickListener());
             if (TextUtils.equals(KEY_DEL, key)) {
                 viewHolder.number.setText(KEY_DEL);
                 viewHolder.number.setTextColor(Color.TRANSPARENT);
                 viewHolder.img.setVisibility(View.VISIBLE);
             } else {
                 viewHolder.number.setText(key);
                 viewHolder.number.setTextColor(Color.WHITE);
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

         public void shuffle() {
             removeSpecialKey();
             Collections.shuffle(keyList);
             insertSpecialKey();
         }

         class ViewHolder {
             TextView number;
             ImageView img;
         }
     }

    private class NumberClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            String content = ((TextView)v).getText().toString();
            if (TextUtils.equals(KeyAdapter.KEY_CONFIRM, content)) {
                if (mOnConfirmListener != null) {
                    mOnConfirmListener.onConfirm(v);
                }
            } else if (TextUtils.equals(KeyAdapter.KEY_DEL, content)) {
                if (mOnDeleteListener != null) {
                    mOnDeleteListener.onDelete(v);
                }
            } else {
                if (mOnAddListener != null) {
                    mOnAddListener.onAdd(v, content);
                }
            }
        }
    }

    public void setOnAddListener(OnAddListener onAddListener) {
        this.mOnAddListener = onAddListener;
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.mOnConfirmListener = onConfirmListener;
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.mOnDeleteListener = onDeleteListener;
    }

    /**
     * 添加
     */
    public interface OnAddListener {
        public void onAdd(View view, String number);
    }

    /**
     * 确定
     */
    public interface OnConfirmListener {
        public void onConfirm(View view);
    }

    /**
     * 删除
     */
    public interface OnDeleteListener {
        public void onDelete(View view);
    }
}

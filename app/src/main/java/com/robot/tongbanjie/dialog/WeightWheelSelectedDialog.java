package com.robot.tongbanjie.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.widget.WheelView;

import java.util.Arrays;

public class WeightWheelSelectedDialog extends BaseWheelFragment {
    private static final int TYPE_WEIGHT = 0;
    private static final String UNIT_WEIGHT = "kg";
    private WheelView mainWheelView;
    private WheelView decimalWheelView;
    private String mainCurrentItem;
    private String decimalCurrentItem;

    private static final String[] mMainPartArray;
    private static final String[] mDecimalArray;

    static {
        mMainPartArray = new String[201];
        for (int i = 0; i <= 200 ; i++) {
            mMainPartArray[i] = String.valueOf(i + 20);
        }

        mDecimalArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    }

    private static final String TAG = WeightWheelSelectedDialog.class.getSimpleName();

    public static WeightWheelSelectedDialog newInstance(Context context, String title, int height) {
        WeightWheelSelectedDialog dialogFragment = new WeightWheelSelectedDialog();
        dialogFragment.mContext = context;
        dialogFragment.setArguments(initArgs(title, height, TYPE_WEIGHT));
        return dialogFragment;
    }

    @Override
    public int setStyleId() {
        return R.style.CustomDarkDialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_weight_wheel_selected, container,
                false);
        initDialogTitle(view);

        TextView closeDialog = (TextView) view.findViewById(R.id.close);
        TextView sureDialog = (TextView) view.findViewById(R.id.sure);

        mainWheelView = (WheelView) view.findViewById(R.id.wheel_view_main);
        decimalWheelView = (WheelView) view.findViewById(R.id.wheel_view_decimal);

        mainWheelView.setOffset(1);
        mainWheelView.setItems(Arrays.asList(mMainPartArray));
        decimalWheelView.setOffset(1);
        decimalWheelView.setItems(Arrays.asList(mDecimalArray));

        if (!TextUtils.isEmpty(mSelectioned)) {
            setSelectioned(mSelectioned);

            String str = mSelectioned.replace(UNIT_WEIGHT, "").trim();
            String[] strArr = str.split("\\.");
            mainCurrentItem = strArr[0];
            decimalCurrentItem = strArr[1];
        } else {
            mainWheelView.setSeletion(1);
            decimalWheelView.setSeletion(1);
        }

        mainWheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                mainCurrentItem = item;
            }
        });
        decimalWheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                decimalCurrentItem = item;
            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancelListener != null) {
                    String itemContent = mainCurrentItem + "." + decimalCurrentItem + " " + UNIT_WEIGHT;
                    mCancelListener.doCancel(itemContent);
                }
                dismiss();
            }
        });
        sureDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSureListener != null) {
                    String itemContent = mainCurrentItem + "." + decimalCurrentItem + " " + UNIT_WEIGHT;
                    mSureListener.doSure(itemContent);
                }
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void setSelectioned(String item) {
        if (TextUtils.isEmpty(item)) {
            return;
        }

        item = item.replace(UNIT_WEIGHT, "").trim();
        // 注意：split()操作点符号需要使用转义符
        String[] weightArr = item.split("\\.");
        int mainIndex = Arrays.asList(mMainPartArray).indexOf(weightArr[0]);
        if (mainIndex != -1) {
            mainWheelView.setSeletion(mainIndex);
        }

        int decimalIndex = Arrays.asList(mDecimalArray).indexOf(weightArr[1]);
        if (decimalIndex != -1) {
            decimalWheelView.setSeletion(decimalIndex);
        }
    }

}

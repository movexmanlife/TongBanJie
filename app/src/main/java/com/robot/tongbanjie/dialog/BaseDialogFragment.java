package com.robot.tongbanjie.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public abstract class BaseDialogFragment extends DialogFragment {

    protected Context mContext;
    protected OnCancelListener mCancelListener;
    protected TextView mTilte;
    protected Button mCancel;

    protected String mTitle; // DialogFragment的标题
    protected int mHeight; // DialogFragment的高度

    protected static final String TITLE = "title";
    protected static final String HEIGHT = "height";
    protected int styleId;

    public abstract int setStyleId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        styleId = setStyleId();
        setStyle(STYLE_NO_FRAME, styleId);
        getArgs();
    }

    protected void getArgs() {
        Bundle bd = getArguments();
        if (bd != null) {
            mTitle = bd.getString(TITLE);
            mHeight = bd.getInt(HEIGHT);
        }
    }

    protected static Bundle initArgs(String title, int height) {
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putInt(HEIGHT, height);
        return args;
    }

    protected void initDialog() {
        Dialog dialog = getDialog();
        dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();

        lp.windowAnimations = styleId;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
    }

    protected void initDialogTitle(View view) {
//        mTilte = (TextView) view.findViewById(R.id.tv_navTitle);
//        mTilte.setText(mTitle);
//
//        mCancel = (Button) view.findViewById(R.id.btn_navCancel);
//        mCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mCancelListener != null) {
//                    mCancelListener.doCancelClick();
//                }
//
//                dismiss();
//            }
//        });
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    public void setOnCancelListener(OnCancelListener cancelListener) {
        this.mCancelListener = cancelListener;
    }

    public static interface OnCancelListener {
        public void doCancelClick();
    }
}

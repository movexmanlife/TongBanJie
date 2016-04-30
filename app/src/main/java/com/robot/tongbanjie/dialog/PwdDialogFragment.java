package com.robot.tongbanjie.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.adapter.ShareAdapter;
import com.robot.tongbanjie.util.ToastUtils;
import com.robot.tongbanjie.widget.SafeKeyboardView;
import com.robot.tongbanjie.widget.SafeKeyboardView.OnAddListener;

public class PwdDialogFragment extends BaseDialogFragment {
    private SafeKeyboardView keyboardView;
    private SafeKeyboardView.OnAddListener mOnAddListener;
    private SafeKeyboardView.OnDeleteListener mOnDeleteListener;
    private SafeKeyboardView.OnConfirmListener mOnConfirmListener;

    public static PwdDialogFragment newInstance(Context context, String title, int height) {
        PwdDialogFragment dialogFragment = new PwdDialogFragment();
        dialogFragment.mContext = context;
        dialogFragment.setArguments(initArgs(title, height));

        return dialogFragment;
    }

    @Override
    protected void initDialog() {
        Dialog dialog = getDialog();
        dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();

        lp.windowAnimations = styleId;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public int setStyleId() {
        return R.style.KeyboardDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.key_board, container,
                false);
        initDialogTitle(view);
        keyboardView = (SafeKeyboardView) view.findViewById(R.id.keyboard);
        keyboardView.setOnConfirmListener(mOnConfirmListener);
        keyboardView.setOnAddListener(mOnAddListener);
        keyboardView.setOnDeleteListener(mOnDeleteListener);
        keyboardView.shuffle();
        view.post(new Runnable() {

            @Override
            public void run() {
                Window dialogWindow = getDialog().getWindow();

                dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
                dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                getView().invalidate();
            }
        });
        return view;
    }

    public void setOnAddListener(SafeKeyboardView.OnAddListener onAddListener) {
        this.mOnAddListener = onAddListener;
    }

    public void setOnConfirmListener(SafeKeyboardView.OnConfirmListener onConfirmListener) {
        this.mOnConfirmListener = onConfirmListener;
    }

    public void setOnDeleteListener(SafeKeyboardView.OnDeleteListener onDeleteListener) {
        this.mOnDeleteListener = onDeleteListener;
    }
}

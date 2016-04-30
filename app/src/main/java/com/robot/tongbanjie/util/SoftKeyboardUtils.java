package com.robot.tongbanjie.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class SoftKeyboardUtils {
    /**
     * 隐藏软键盘
     *
     * @param context
     */
    public static void hideKeyboard(Context context) {
        if (context == null || !(context instanceof Activity)) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 调用hideSoftInputFromWindow方法隐藏软键盘
        imm.hideSoftInputFromWindow(((Activity)context).getWindow().getDecorView().getWindowToken(), 0); //强制隐藏键盘
    }

    /**
     * 显示软键盘
     *
     * @param context
     */
    public static void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE, InputMethodManager.SHOW_FORCED);
    }

}

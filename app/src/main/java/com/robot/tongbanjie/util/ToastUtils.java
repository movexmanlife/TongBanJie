package com.robot.tongbanjie.util;

import android.content.Context;
import android.widget.Toast;

import com.robot.tongbanjie.TbjApp;

public final class ToastUtils {

    private ToastUtils() {

    }

    public static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    public static void show(Context context, String msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }

    public static void showShort(int resId) {
        showShort(TbjApp.getInstance().getString(resId));
    }

    public static void showShort(String msg) {
        Toast.makeText(TbjApp.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        showLong(TbjApp.getInstance().getString(resId));
    }

    public static void showLong(String msg) {
        Toast.makeText(TbjApp.getInstance(), msg, Toast.LENGTH_LONG).show();
    }
}
package com.robot.tongbanjie.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * 利用LocalBroadcastManager管理广播
 */
public class LocalBroadcastUtils {
    /**
     * 注册广播
     *
     * @param context
     * @param broadcastReceiver
     * @param intentFilter
     */
    public static void register(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * 注销广播
     *
     * @param context
     * @param broadcastReceiver
     */
    public static void unregister(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    /**
     * 发送广播
     *
     * @param context
     * @param intent
     */
    public static void send(Context context, Intent intent) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        localBroadcastManager.sendBroadcast(intent);
    }
}

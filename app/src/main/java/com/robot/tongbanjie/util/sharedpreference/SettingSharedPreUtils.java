package com.robot.tongbanjie.util.sharedpreference;

import android.content.Context;

import java.util.Map;

/**
 * 用户无关的设置
 */
public final class SettingSharedPreUtils implements SharedPreferencesImpl {
    public static final String FILE_NAME = SettingSharedPreUtils.class.getSimpleName();
    public static final String KEY_WIFI_SWITCH = "wifi_switch";
    public static final String KEY_GUIDE = "guide";

    private static volatile SettingSharedPreUtils mInstance;

    public static SettingSharedPreUtils getInstance() {
        if (mInstance == null) {
            synchronized (SettingSharedPreUtils.class) {
                if (mInstance == null) {
                    mInstance = new SettingSharedPreUtils();
                }
            }
        }
        return mInstance;
    }

    private SettingSharedPreUtils() {

    }

    @Override
    public void put(Context context, String key, Object object) {
        SharedPreferenceUtils.put(context, FILE_NAME, key, object);
    }

    @Override
    public Object get(Context context, String key, Object defaultObject) {
        return SharedPreferenceUtils.get(context, FILE_NAME, key, defaultObject);
    }

    @Override
    public void remove(Context context, String key) {
        SharedPreferenceUtils.remove(context, FILE_NAME, key);
    }

    @Override
    public void clear(Context context) {
        SharedPreferenceUtils.clear(context, FILE_NAME);
    }

    @Override
    public boolean contains(Context context, String key) {
        return SharedPreferenceUtils.contains(context, FILE_NAME, key);
    }

    @Override
    public Map<String, ?> getAll(Context context) {
        return SharedPreferenceUtils.getAll(context, FILE_NAME);
    }

    public static boolean getWifiSwitchState(Context context, boolean isOnDefault) {
        return (Boolean)SettingSharedPreUtils.getInstance().get(context, KEY_WIFI_SWITCH, isOnDefault);
    }

    public static void setWifiSwitchState(Context context, boolean isOn) {
        SettingSharedPreUtils.getInstance().put(context, KEY_WIFI_SWITCH, isOn);
    }

    public static boolean getGuideState(Context context, boolean isGuideDefault) {
        return (Boolean)SettingSharedPreUtils.getInstance().get(context, KEY_GUIDE, isGuideDefault);
    }

    public static void setGuideState(Context context, boolean isGuide) {
        SettingSharedPreUtils.getInstance().put(context, KEY_GUIDE, isGuide);
    }

}

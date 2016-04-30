package com.robot.tongbanjie.util.sharedpreference;

import android.content.Context;

import java.util.Map;

/**
 * 用户有关的设置
 */
public class UserInfoSharedPreUtils implements SharedPreferencesImpl {
    public static final String FILE_NAME = UserInfoSharedPreUtils.class.getSimpleName();
    private static volatile UserInfoSharedPreUtils mInstance;

    public UserInfoSharedPreUtils getInstance() {
        if (mInstance == null) {
            synchronized (SettingSharedPreUtils.class) {
                if (mInstance == null) {
                    mInstance = new UserInfoSharedPreUtils();
                }
            }
        }
        return mInstance;
    }

    private UserInfoSharedPreUtils() {

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
}

package com.robot.tongbanjie.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 获取手机信息工具类<br>
 *  
 */  
public class DeviceInfoUtils {
    private static final String TAG = DeviceInfoUtils.class.getSimpleName();

    private DeviceInfoUtils() {

    }
  
    /** 
     * 获取应用程序的IMEI号 
     */  
    public static String getIMEI(Context context) {
        TelephonyManager telecomManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);  
        String imei = telecomManager.getDeviceId();  
        Log.e(TAG, "Device IMEI:" + imei);
        return imei;
    }  
  
    /** 
     * 获取设备的系统版本号 
     */  
    public static int getVersionSDK() {
        int sdk = android.os.Build.VERSION.SDK_INT;  
        Log.e(TAG, "Device VERSION:" + sdk);
        return sdk;  
    }  
  
    /** 
     * 获取设备的型号 
     */  
    public static String getModel() {
        String model = android.os.Build.MODEL;
        Log.e(TAG, "Device MODEL:" + model);
        return model;  
    }  
}  
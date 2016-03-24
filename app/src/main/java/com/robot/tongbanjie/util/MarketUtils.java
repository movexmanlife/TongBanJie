package com.robot.tongbanjie.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 应用市场评分
 */
public class MarketUtils {
    private MarketUtils() {
    }

    /**
     *
     * @param context
     * @param appPckName 应用包名
     */
    public static void searchAppByPkgName(Context context, String appPckName) {
        if (TextUtils.isEmpty(appPckName)) {
            return;
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + appPckName));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "未找到相应的市场！", Toast.LENGTH_SHORT).show();
        }
    }

}
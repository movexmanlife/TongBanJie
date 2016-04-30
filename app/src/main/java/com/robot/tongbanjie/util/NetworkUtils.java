package com.robot.tongbanjie.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetworkUtils {
	public static final int TYPE_NOT_CONNECTED = 0;
	public static final int TYPE_WIFI = 1;
	public static final int TYPE_MOBILE = 2;

	private NetworkUtils() {

	}

	public static int getConnectivityStatus(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (null != activeNetwork) {
			if(ConnectivityManager.TYPE_WIFI == activeNetwork.getType()) {
				return TYPE_WIFI;
			} else if(ConnectivityManager.TYPE_MOBILE == activeNetwork.getType()) {
				return TYPE_MOBILE;
			}
		}

		return TYPE_NOT_CONNECTED;
	}
	
	public static String getConnectivityStatusString(Context context) {
		int conn = NetworkUtils.getConnectivityStatus(context);
		String status = null;
		if (conn == NetworkUtils.TYPE_WIFI) {
			status = "Wifi enabled";
		} else if (conn == NetworkUtils.TYPE_MOBILE) {
			status = "Mobile data enabled";
		} else if (conn == NetworkUtils.TYPE_NOT_CONNECTED) {
			status = "Not connected to Internet";
		}
		return status;
	}
}
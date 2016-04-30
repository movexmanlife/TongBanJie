package com.robot.tongbanjie.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.text.TextUtils;

import com.robot.tongbanjie.util.LocalBroadcastUtils;
import com.robot.tongbanjie.util.ReceiverConst;

public class NetworkStateService extends Service {

	private ConnectivityManager connectivityManager;
    private NetworkInfo info;
    
    private SharedPreferences shared;
	private SharedPreferences.Editor editor;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                info = connectivityManager.getActiveNetworkInfo();  
                if(info != null && info.isAvailable()) {
                    String name = info.getTypeName();
                    if(TextUtils.equals("WIFI", name)) {
                    	editor.putString("netType", "wifi");
            			editor.commit();
                    } else {
                    	editor.putString("netType", "3g");
            			editor.commit();
                    }
					sendNetworkStateBroadCast(ReceiverConst.ACTION_CONNECTIONED);
                } else {
					sendNetworkStateBroadCast(ReceiverConst.ACTION_NO_CONNECTION);
                }
            }
        }
    };

	/**
	 * 发送本地广播通知网络状态
	 * @param action
	 */
	private void sendNetworkStateBroadCast(String action) {
		if (TextUtils.equals(ReceiverConst.ACTION_CONNECTIONED, action) ||
				TextUtils.equals(ReceiverConst.ACTION_NO_CONNECTION, action)) {
			Intent intent = new Intent(action);
			LocalBroadcastUtils.send(getApplication(), intent);
		}
	}
    
	@Override
	public IBinder onBind(Intent intent) {
		 
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		shared = getSharedPreferences("userInfo", 0); 
		editor = shared.edit();
		
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mReceiver, mFilter);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	
}
package com.robot.tongbanjie;

import android.app.Application;

public class TbjApp extends Application{

    private static TbjApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static TbjApp getInstance() {
        return app;
    }
}

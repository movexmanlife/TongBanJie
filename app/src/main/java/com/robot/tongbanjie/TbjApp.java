package com.robot.tongbanjie;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.robot.tongbanjie.entity.InvestPeople;
import com.robot.tongbanjie.entity.InvestPeopleRes;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TbjApp extends Application{

    private static TbjApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        ActiveAndroid.initialize(this);
        insertDbData();
    }

    public static TbjApp getInstance() {
        return app;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    private void insertDbData() {
        final List<InvestPeople> investPeoples = InvestPeopleRes.getInvestPeopleList();
        if (investPeoples != null) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    /**
                     * 删除所有数据
                     */
                    new Delete().from(InvestPeople.class).execute();

                    /**
                     * 批量插入数据
                     */
                    ActiveAndroid.beginTransaction();
                    try {
                        int len = investPeoples.size();
                        for (int i = 0; i < len; i++) {
                            investPeoples.get(i).save();
                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }
                }
            });
        }
    }
}

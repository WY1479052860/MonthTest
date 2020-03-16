package com.bawei.monthtest.base;

import android.app.Application;
import android.content.Context;

import com.bawei.monthtest.utils.CrashHandler;

/**
 * 辅助类
 */
public class App  extends Application {

    private  static  Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        CrashHandler.getInstance().init();

    }
    public static Context getAppContext(){
        return context;
    }
}

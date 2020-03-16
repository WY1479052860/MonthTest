package com.bawei.monthtest.utils;

import androidx.annotation.NonNull;

/**
 * 全局异常处理
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler{
   private CrashHandler(){

   }
   private static class SingleInstance{
       private static final CrashHandler INSTANCE=new CrashHandler();
   }

    public static CrashHandler getInstance() {
        return SingleInstance.INSTANCE;
    }
    public void init(){
       Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        e.printStackTrace();
    }
}

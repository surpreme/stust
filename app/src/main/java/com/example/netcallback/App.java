package com.example.netcallback;

import android.app.Application;

import com.example.netcallback.utils.HttpHelper;
import com.example.netcallback.utils.OkhttpProcessor;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 全局初始化类（切换框架 换一个类即可）
         */
        HttpHelper.init(new OkhttpProcessor());
    }
}

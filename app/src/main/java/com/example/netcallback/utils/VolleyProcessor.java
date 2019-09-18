package com.example.netcallback.utils;


import android.content.Context;

import com.example.netcallback.interfaces.ICallback;
import com.example.netcallback.interfaces.IHttpProcessor;

import java.util.Map;

public class VolleyProcessor implements IHttpProcessor {
    /**
     * 执行任务类Volley
     */
    public static String mQueuue=null;

    public VolleyProcessor(Context context) {

    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback iCallback) {

    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback iCallback) {

    }
}

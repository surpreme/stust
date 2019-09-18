package com.example.netcallback.callback;

import com.example.netcallback.interfaces.ICallback;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallback<T> implements ICallback {
    /**
     * 解析类
     * <T>  t是不存在 不要以为是个类 是泛型
     * abstract抽象类 因为下面类中有抽象方法 类必须为抽象（java基础）
     * java面向思想和java反射
     */

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> claz = syncClassInfo(this);
        T objResult = (T) gson.fromJson(result, claz);
        if (objResult==null)
            onSuccess("null");
        onSuccess(objResult);

    }

    //为了处理上面返回的泛型
    public abstract void onSuccess(T result);

    private static Class<?> syncClassInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];


    }
}

package com.example.netcallback.interfaces;

public interface ICallback {
    void onSuccess(String result);
    void onFailure(String e);
}

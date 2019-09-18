package com.example.netcallback.struct;

/**
 * 无参数有返回值
 */
public abstract class FuctionWithResultOnly<Result> extends Function {
    public FuctionWithResultOnly(String function) {
        super(function);
    }
    public abstract Result function();
}

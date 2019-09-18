package com.example.netcallback.struct;

/**
 * 有参数有返回值
 */
public abstract class FuctionWithParamAndResult<Result,Param> extends Function {
    public FuctionWithParamAndResult(String functionName) {
        super(functionName);
    }
    public abstract Result function(Param data);
}

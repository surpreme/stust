package com.example.netcallback.struct;

/**
 * 有参数无返回值（返回值使用泛型）
 */
public abstract class FuctionWithParamOnly<Params> extends Function {
    public FuctionWithParamOnly(String function) {
        super(function);
    }
    public abstract void function(Params params);
}

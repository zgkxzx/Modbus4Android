package com.zgkxzx.modbus4And.requset;

/**
 * 创建者   zgkxzx
 * 创建日期 2017/6/12.
 * 功能描述
 */

public interface OnRequestBack<T> {
    void onSuccess(T t);
    void onFailed(String msg);
}

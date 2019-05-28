package com.sunmi.opensdk.base;

import android.os.Bundle;

import com.sunmi.opensdk.utils.IntentUtil;

/**
 * 作者：杨柳 on 2019/5/14 0014 17:15
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public abstract class BaseReq {

    public String transaction;

    public BaseReq() {
    }

    public abstract int getType();

    public void toBundle(Bundle var1) {
        var1.putInt(IntentUtil._SM_API_COMMAND_TYPE, this.getType());
        var1.putString(IntentUtil._WXAPI_BASEREQ_TRANSACTION, String.valueOf(System.currentTimeMillis() / 1000));
    }

    public void fromBundle(Bundle var1) {
        this.transaction = IntentUtil.getStringExtra(var1, IntentUtil._WXAPI_BASEREQ_TRANSACTION);
    }

    public abstract boolean checkArgs();


}

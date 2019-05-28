package com.sunmi.opensdk.base;

import android.os.Bundle;

import com.sunmi.opensdk.utils.IntentUtil;
import com.sunmi.opensdk.utils.StrUtil;

/**
 * 作者：杨柳 on 2019/5/14 0014 17:27
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public abstract class BaseResp {

    public String errCode;
    public String errType;
    public String errDes;
    public String transaction;

    public BaseResp() {
    }

    public abstract int getType();

    public void toBundle(Bundle var1) {
        var1.putInt(IntentUtil._SM_API_COMMAND_TYPE, this.getType());
        var1.putString(IntentUtil._SM_API_BASERESP_ERR_CODE, this.errCode);
        var1.putString(IntentUtil._SM_API_BASERESP_ERR_TYPE, this.errType);
        var1.putString(IntentUtil._SM_API_BASERESP_ERR_DES, this.errDes);
        var1.putString(IntentUtil._SM_API_BASERESP_TRANSACTION, String.valueOf(System.currentTimeMillis() / 1000));
    }

    public void fromBundle(Bundle var1) {
        this.errCode = StrUtil.safeString(IntentUtil.getStringExtra(var1, IntentUtil._SM_API_BASERESP_ERR_CODE));
        this.errType = StrUtil.safeString(IntentUtil.getStringExtra(var1, IntentUtil._SM_API_BASERESP_ERR_TYPE));
        this.errDes = StrUtil.safeString(IntentUtil.getStringExtra(var1, IntentUtil._SM_API_BASERESP_ERR_DES));
        this.transaction = StrUtil.safeString(IntentUtil.getStringExtra(var1, IntentUtil._SM_API_BASERESP_TRANSACTION));
    }

    public abstract boolean checkArgs();


    @Override
    public String toString() {
        return " errCode = " + errCode + "\n "
                + "errType = " + errType + "\n "
                + "errDes = " + errDes + "\n "
                + "transaction = " + transaction + "\n "
                ;
    }
}

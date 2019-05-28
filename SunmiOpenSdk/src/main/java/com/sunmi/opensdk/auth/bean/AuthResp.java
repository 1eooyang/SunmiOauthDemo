package com.sunmi.opensdk.auth.bean;

import android.os.Bundle;

import com.sunmi.opensdk.base.BaseResp;
import com.sunmi.opensdk.utils.IntentUtil;
import com.sunmi.opensdk.utils.LogUtil;
import com.sunmi.opensdk.utils.StrUtil;

/**
 * 作者：杨柳 on 2019/5/13 0013 11:43
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class AuthResp extends BaseResp {
    private static final int LENGTH_LIMIT = 1024;
    private String code;
    private String lang;
    private String state;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public void fromBundle(Bundle var1) {
        super.fromBundle(var1);
        this.code = StrUtil.safeString(IntentUtil.getStringExtra(var1, "sm_auth_code"));
        this.state = StrUtil.safeString(IntentUtil.getStringExtra(var1, "sm_auth_state"));
        this.lang = StrUtil.safeString(IntentUtil.getStringExtra(var1, "sm_auth_lang"));
    }


    public void toBundle(Bundle var1) {
        super.toBundle(var1);
        var1.putString("sm_auth_code", this.code);
        var1.putString("sm_auth_state", this.state);
        var1.putString("sm_auth_lang", this.lang);
    }

    private static String getString(Bundle bundle, String key, String defaultValue) {
        if (bundle != null) {
            String value = bundle.getString(key);
            return value != null ? value : defaultValue;
        } else {
            return defaultValue;
        }
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public boolean checkArgs() {
        if (this.state != null && this.state.length() > 1024) {
            LogUtil.e("MicroMsg.SDK.SendAuth.Resp", "checkArgs fail, state is invalid");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public String toString() {
        return "code = " + code + "\n " +
                "lang = " + lang + "\n " +
                "state = " + state + "\n ";
    }
}

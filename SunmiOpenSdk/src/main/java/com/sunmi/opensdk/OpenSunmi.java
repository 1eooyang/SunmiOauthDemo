package com.sunmi.opensdk;

import android.app.Activity;

import com.sunmi.opensdk.auth.OauthCallback;
import com.sunmi.opensdk.openapi.ActivityJumper;
import com.sunmi.opensdk.auth.bean.AuthReq;
import com.sunmi.opensdk.utils.SafeUtil;

/**
 * 作者：杨柳 on 2019/5/10 0010 15:25
 *
 * 邮箱：yangliu@sunmi.com
 */
public class OpenSunmi {
    public static final String TAG = "OpenSunmi";
    private AuthReq mAuthReq;


    public OpenSunmi(AuthReq info) {
        SafeUtil.checkNotNull(info, "AuthReq must not be null ");
        this.mAuthReq = info;
    }


    public void authorize(Activity activity, OauthCallback callBack) {
        SafeUtil.checkNotNull(activity, "activity must not be null ");
        SafeUtil.checkNotNull(callBack, "callBack must not be null ");
        ActivityJumper.authorize(activity, mAuthReq, callBack);
    }

}

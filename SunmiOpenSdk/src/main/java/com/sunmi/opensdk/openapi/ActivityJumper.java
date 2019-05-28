package com.sunmi.opensdk.openapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.sunmi.opensdk.BuildConfig;
import com.sunmi.opensdk.auth.OauthCallback;
import com.sunmi.opensdk.auth.bean.AuthReq;
import com.sunmi.opensdk.base.BaseErrorMessage;
import com.sunmi.opensdk.base.SunmiCallbackManager;
import com.sunmi.opensdk.utils.IntentUtil;
import com.sunmi.opensdk.utils.SafeUtil;

import static com.sunmi.opensdk.base.SunmiAppInfo.SUNMI_CLASS_NAME;
import static com.sunmi.opensdk.base.SunmiAppInfo.SUNMI_PACKAGE_NAME;

/**
 * 作者：杨柳 on 2019/5/10 0010 18:42
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class ActivityJumper {


    public static void authorize(Activity activity, AuthReq authReq, OauthCallback callBack) {
        if (TextUtils.isEmpty(authReq.getPackageName())) {
            callBack.OnFailed(new BaseErrorMessage("send fail, invalid targetPkgName, targetPkgName = " + authReq.getPackageName(), "-2"));
        } else {

            Intent intent = new Intent();
            intent.setClassName(SUNMI_PACKAGE_NAME, SUNMI_CLASS_NAME);
            Bundle bundle = new Bundle();
            authReq.toBundle(bundle);
            intent.putExtras(bundle);

            intent.putExtra(IntentUtil._SM_OPEN_SDKVERSION, BuildConfig.VERSION_CODE);
            intent.putExtra(IntentUtil._SM_API_COMMAND_TYPE, SunmiCallbackManager.TYPE_AUTH);

            if (!SafeUtil.validateAppSignatureForIntent(activity, intent)) {
                callBack.OnFailed(new BaseErrorMessage("send fail, failed for SunmiUserCenter app signature check failed", "-6"));
                return ;
            }

            try {
                activity.startActivity(intent);
                SunmiCallbackManager.getInstance().setSunmiCallback(SunmiCallbackManager.TYPE_AUTH, callBack);
            } catch (Exception E) {
                callBack.OnFailed(new BaseErrorMessage("startActivity fail, " + E.getLocalizedMessage(), "-3"));
            }
        }
    }


}

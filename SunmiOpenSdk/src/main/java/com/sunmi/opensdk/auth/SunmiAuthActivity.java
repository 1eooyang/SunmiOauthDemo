package com.sunmi.opensdk.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.sunmi.opensdk.auth.bean.AuthResp;
import com.sunmi.opensdk.base.BaseCallBack;
import com.sunmi.opensdk.base.BaseErrorMessage;
import com.sunmi.opensdk.base.SunmiCallbackManager;
import com.sunmi.opensdk.utils.IntentUtil;
import com.sunmi.opensdk.utils.LogUtil;
import com.sunmi.opensdk.utils.SafeUtil;
import com.sunmi.opensdk.utils.StrUtil;

/**
 * 作者：杨柳 on 2019/5/10 0010 18:43
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
final public class SunmiAuthActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("SUNMI_OPEN_LOG.SunmiAuthActivity", "onCreate");
        hand();

    }

    private void hand() {
        try {
            handlerIntent(getIntent());
        } catch (Exception e) {
            LogUtil.e("SUNMI_OPEN_LOG.SunmiAuthActivity", "-->handlerIntent : " + e.getLocalizedMessage());
        } finally {
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        LogUtil.e("SUNMI_OPEN_LOG.SunmiAuthActivity", "onNewIntent");
        hand();
    }


    private void handlerIntent(Intent intent) {

        if (null == intent) {//检测intent是否为null
            LogUtil.e("SUNMI_OPEN_LOG.SunmiAuthActivity", "-->onCreate, getIntent() return null");
            return;
        }


        int type = intent.getIntExtra(IntentUtil._SM_API_COMMAND_TYPE, 0);
        LogUtil.e("SUNMI_OPEN_LOG.SunmiAuthActivity", "handleIntent, cmd = " + type);
        BaseCallBack authListener = SunmiCallbackManager.getInstance().getCallBack(type);

        if (!IntentUtil.isIntentFromSunmi(intent, IntentUtil.COM_SUNMI_OPENAPI_TOKEN) || !SafeUtil.validateSunmiSign(this)) {
            LogUtil.e("SUNMI_OPEN_LOG.SunmiAuthActivity", "handleIntent fail, intent not from sunmi msg");
            if (authListener != null) {
                authListener.OnFailed(new BaseErrorMessage("Intent not from sunmi msg", "100"));
            }
        }else{
            String error_code = StrUtil.safeString(intent.getStringExtra(IntentUtil._SM_API_BASERESP_ERR_CODE));
            String error_type = StrUtil.safeString(intent.getStringExtra(IntentUtil._SM_API_BASERESP_ERR_TYPE));
            String error_des = StrUtil.safeString(intent.getStringExtra(IntentUtil._SM_API_BASERESP_ERR_DES));

            if (TextUtils.isEmpty(error_code) && TextUtils.isEmpty(error_type) && TextUtils.isEmpty(error_des)) {//成功
                handType(intent, type, authListener);
            } else if ("_sm_type_denied".equals(error_type)) {
                if (authListener != null) {
                    authListener.OnFailed(new BaseErrorMessage(error_des, error_code));
                }
            } else {
                if (authListener != null) {
                    authListener.onCancel();
                }
            }
        }

        SunmiCallbackManager.getInstance().removeCallback(type);
    }

    private void handType(Intent intent, int type, BaseCallBack authListener) {
        switch (type) {
            case SunmiCallbackManager.TYPE_AUTH:
                if (authListener != null) {
                    AuthResp authResp = new AuthResp();
                    authResp.fromBundle(intent.getExtras());
                    ((OauthCallback) authListener).onSuccess(authResp);
                }
                break;

        }
    }
}

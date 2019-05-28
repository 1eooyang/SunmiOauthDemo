package com.sunmi.opensdk.utils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * 作者：杨柳 on 2019/5/10 0010 15:16
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public final class IntentUtil {


    public static final String _SM_OPEN_SDKVERSION = "_sm_open_sdkVersion";
    public static final String _SM_OPEN_APPPACKAGE = "_sm_open_appPackage";
    public static final String _SM_OPEN_CONTENT = "_sm_open_content";
    public static final String _SM_OPEN_CHECKSUM = "_sm_open_checksum";


    public static final String COM_SUNMI_OPENAPI_TOKEN = "com.sunmi.openapi.token";
    public static final String _SM_API_COMMAND_TYPE = "_sm_api_command_type";

    public static final String _WXAPI_BASEREQ_TRANSACTION = "_sm_api_req_transaction";

    public static final String _SM_API_BASERESP_ERR_CODE = "_sm_api_base_err_code";
    public static final String _SM_API_BASERESP_ERR_DES = "_sm_api_base_err_des";
    public static final String _SM_API_BASERESP_ERR_TYPE = "_sm_api_base_err_type";
    public static final String _SM_API_BASERESP_TRANSACTION = "_sm_api_resp_transaction";




    private IntentUtil() {
        throw new RuntimeException(this.getClass().getSimpleName() + " should not be instantiated");
    }


    public static int getIntExtra(Bundle var0, String var1) {
        if (var0 == null) {
            return -1;
        } else {
            int var3;
            try {
                var3 = var0.getInt(var1, -1);
            } catch (Exception var2) {
                Log.e("SunmiMsg.IntentUtil", "getIntExtra exception:" + var2.getMessage());
                var3 = -1;
            }

            return var3;
        }
    }

    public static String getStringExtra(Bundle var0, String var1) {
        if (var0 == null) {
            return null;
        } else {
            String var3;
            try {
                var3 = var0.getString(var1);
            } catch (Exception var2) {
                Log.e("SunmiMsg.IntentUtil", "getStringExtra exception:" + var2.getMessage());
                var3 = null;
            }

            return var3;
        }
    }

    public static boolean isIntentFromSunmi(Intent var0, String var1) {
        if (var0 == null) {
            return false;
        } else {
            String var2;
            return (var2 = var0.getStringExtra("sm_token_key")) != null && var2.equals(var1);
        }
    }


}

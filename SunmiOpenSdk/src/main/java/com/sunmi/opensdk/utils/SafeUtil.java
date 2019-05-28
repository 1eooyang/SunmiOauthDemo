package com.sunmi.opensdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.sunmi.opensdk.base.SunmiAppInfo;

/**
 * 作者：杨柳 on 2019/5/10 0010 18:26
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class SafeUtil {


    public static <T> T checkNotNull(T var0, Object var1) {
        if (var0 == null) {
            throw new NullPointerException(String.valueOf(var1));
        } else {
            return var0;
        }
    }


    public static boolean validateAppSignatureForIntent(Context context, Intent intent) {
        PackageManager pkgMgr = context.getPackageManager();
        try {
            if (null != pkgMgr) {
                ResolveInfo resolveInfo = pkgMgr.resolveActivity(intent, 0);
                if (resolveInfo != null) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    return SunmiAppInfo.SUNMI_SIGN.equals(SignUtil.getSign(context, packageName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public static boolean validateSunmiSign(Context context) {


        return SunmiAppInfo.SUNMI_SIGN.equals(SignUtil.getSign(context, SunmiAppInfo.SUNMI_PACKAGE_NAME));


    }



}

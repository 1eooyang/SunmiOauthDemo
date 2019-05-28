package com.sunmi.opensdk.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import java.security.MessageDigest;
import java.util.Locale;

/**
 * 作者：杨柳 on 2019/5/10 0010 16:43
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class SignUtil {


    public static String getSign(Context context, String pkgName) {
        try {
            @SuppressLint("PackageManagerGetSignatures") PackageInfo packageInfo = context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);

            if (null != packageInfo.signatures) {
                Signature[] var2 = packageInfo.signatures;
                int var3 = var2.length;

                for (int var4 = 0; var4 < var3; ++var4) {
                        Signature signature = var2[var4];
                        byte[] cert = signature.toByteArray();
                        MessageDigest md = MessageDigest.getInstance("SHA1");
                        byte[] publicKey = md.digest(cert);
                        StringBuffer hexString = new StringBuffer();
                        for (int i = 0; i < publicKey.length; i++) {
                            String appendString = Integer.toHexString(0xFF & publicKey[i])
                                    .toUpperCase(Locale.US);
                            if (appendString.length() == 1)
                                hexString.append("0");
                            hexString.append(appendString);
                            hexString.append(":");
                        }
                        String result = hexString.substring(0, hexString.length() - 1);
                        return MD5.hexdigest(result);
                }
            }

        } catch (Exception var6) {
            var6.printStackTrace();
        }
        return "";
    }

}

package com.sunmi.opensdk.utils;

import android.text.TextUtils;

/**
 * 作者：杨柳 on 2019/5/13 0013 15:29
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class StrUtil {

    public static String safeString(String orignal) {
        return TextUtils.isEmpty(orignal) ? "" : orignal;
    }


}

package com.sunmi.util;

import android.os.Build;

import java.lang.reflect.Method;

import sunmi.os.SystemPropertiesUtils;


/**
 * Created by yangliu on 2018/8/9.
 */
public class DeviceUtils {

    public static final String VERSION_CODE = "ro.version.woyou_versioncode";
    public static final String VERSION_NAME = "ro.version.woyou_versionname";
    public static final String VERSION_NAME_T1 = "ro.version.sunmi_versionname";
    public static final String VERSION_CODE_T1 = "ro.version.sunmi_versioncode";

    public static void init() {
        try {
            SystemPropertiesUtils.get("ro.serialno");
        } catch (Exception e) {
            isNewRom = false;
        }
    }

    private static boolean isNewRom = true;

    /**
     * 获取设备型号
     *
     * @return
     */
    public static String getDeviceModel() {
        if (isNewRom) {
            return sunmi.os.Build.SUNMI_PRODUCT_NAME;
        } else {
            return Build.MODEL;
        }
    }

    /**
     * 获取系统版本标志
     */
    public static String getSystemVersionCode() {

        if (isNewRom) {
            return sunmi.os.Build.SUNMI_VERSION_CODE;
        } else {
            if (getDeviceModel().toLowerCase().contains("t1") || getDeviceModel().toLowerCase().contains("v2")) {
                return getOldRomInfo(VERSION_CODE_T1);
            } else {
                return getOldRomInfo(VERSION_CODE);
            }
        }

    }

    /**
     * 获取系统版本名
     */
    public static String getSystemVersionName() {
        if (isNewRom) {
            return sunmi.os.Build.SUNMI_VERSION_NAME;
        } else {
            if (getDeviceModel().toLowerCase().contains("t1") || getDeviceModel().toLowerCase().contains("v2")) {
                return getOldRomInfo(VERSION_NAME_T1);
            } else {
                return getOldRomInfo(VERSION_NAME);
            }
        }


    }


    /**
     * 获取机器SN码
     *
     * @return
     */
    public static String getMSN() {
        // return "T206186L70774";
        String msn = "";
        if (isNewRom) {
            msn = SystemPropertiesUtils.get("ro.serialno");
        } else {
            msn = getOldRomInfo("ro.serialno");
        }
        return msn;
    }

    private static String getOldRomInfo(String tag) {
        String msn = "";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            msn = (String) get.invoke(c, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msn;
    }

/*

    public static DeviceInfo getDeviceInfo() {
        DeviceInfo info = new DeviceInfo();
        info.machineModel = getDeviceModel();
        info.msn = getMSN();
        info.versionCode = Integer.parseInt(getSystemVersionCode());
        info.versionName = getSystemVersionName();
        return info;
    }
*/





}


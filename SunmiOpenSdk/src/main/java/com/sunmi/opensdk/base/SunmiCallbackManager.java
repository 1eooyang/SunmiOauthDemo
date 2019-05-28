package com.sunmi.opensdk.base;

import android.util.SparseArray;

/**
 * 作者：杨柳 on 2019/5/13 0013 11:33
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class SunmiCallbackManager {

    public static final int TYPE_AUTH = 1;




    private SparseArray<BaseCallBack> mCallBackSparseArray = new SparseArray<>();

    private SunmiCallbackManager() {

    }

    private static class Holder {
        private static SunmiCallbackManager sunmiCallbackManager = new SunmiCallbackManager();
    }


    public static SunmiCallbackManager getInstance() {
        return Holder.sunmiCallbackManager;
    }


    public synchronized void setSunmiCallback(int callbackId, BaseCallBack authListener) {
        if (callbackId >= 0 && authListener != null) {
            this.mCallBackSparseArray.put(callbackId, authListener);
        }
    }




    public synchronized void removeCallback(int callbackId) {
        this.mCallBackSparseArray.remove(callbackId);

    }


    public synchronized BaseCallBack getCallBack(int callbackId) {
        return mCallBackSparseArray.get(callbackId);
    }





}

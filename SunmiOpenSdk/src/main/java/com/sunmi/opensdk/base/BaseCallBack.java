package com.sunmi.opensdk.base;

/**
 * 作者：杨柳 on 2019/5/10 0010 18:36
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public interface BaseCallBack<S> {


    void onSuccess(S s);

    void onCancel();


    void OnFailed(BaseErrorMessage baseErrorMessage);


}

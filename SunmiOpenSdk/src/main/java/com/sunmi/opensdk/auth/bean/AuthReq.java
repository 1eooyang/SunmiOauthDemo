package com.sunmi.opensdk.auth.bean;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.sunmi.opensdk.base.BaseReq;
import com.sunmi.opensdk.utils.IntentUtil;
import com.sunmi.opensdk.utils.LogUtil;
import com.sunmi.opensdk.utils.SignUtil;
import com.sunmi.opensdk.utils.StrUtil;

/**
 * 作者：杨柳 on 2019/5/10 0010 15:50
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class AuthReq extends BaseReq {
    private String appid = "";
    private String scope = "";
    private String mPackageName = "";
    private String mKeyHash = "";
    private String state = "";

    private static final int LENGTH_LIMIT = 1024;
    public static final String DEFAULT_SCOPE = "userInfo,shopInfo";
    public static final String DEFAULT_AND_MEMBER_SCOPE = "userInfo,shopInfo,shopMember";


    private AuthReq(Context context, String appid, String scope, String state) {
        this.appid = appid;
        this.scope = scope;
        this.mPackageName = context.getPackageName();
        this.state = state;
        this.mKeyHash = SignUtil.getSign(context, mPackageName);
    }


    public String getAppid() {
        return appid;
    }


    public static Builder Builder(Context context) {
        return new Builder(context);
    }


    public String getPackageName() {
        return mPackageName;
    }

    @Override
    public void toBundle(Bundle var1) {
        super.toBundle(var1);

        var1.putString("appKey", this.appid);
        var1.putString("scope", this.scope);
        var1.putString("packageName", this.mPackageName);
        var1.putString("key_hash", this.mKeyHash);
        var1.putString("state", this.state);
    }


    @Override
    public void fromBundle(Bundle var1) {
        super.fromBundle(var1);

        this.appid = StrUtil.safeString(IntentUtil.getStringExtra(var1, "appKey"));
        this.scope = StrUtil.safeString(IntentUtil.getStringExtra(var1, "scope"));
        this.mPackageName = StrUtil.safeString(IntentUtil.getStringExtra(var1, "packageName"));
        this.mKeyHash = StrUtil.safeString(IntentUtil.getStringExtra(var1, "key_hash"));
        this.state = StrUtil.safeString(IntentUtil.getStringExtra(var1, "state"));

    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public boolean checkArgs() {
        if (!TextUtils.isEmpty(appid) && !TextUtils.isEmpty(mPackageName) && !TextUtils.isEmpty(scope) && !TextUtils.isEmpty(mKeyHash)) {
            if (this.state != null && this.state.length() > 1024) {
                LogUtil.e("SunmiMsg.AuthReq.checkArgs", "checkArgs fail, state is invalid");
                return false;
            } else {
                return true;
            }
        }
        LogUtil.e("SunmiMsg.AuthReq.checkArgs", "checkArgs fail, appid,mPackageName,scope is should not null");
        return false;
    }

    public static final class Builder {
        String mAppid;
        String mScop;
        String mStatus = "";
        Context mContext;

        public Builder(Context context) {
            mContext = context;
        }


        public Builder setAppid(String appid) {
            mAppid = appid;
            return this;
        }


        public Builder setScop(String scop) {
            mScop = scop;
            return this;
        }

        public Builder setStatus(String status) {
            mStatus = status;
            return this;
        }


        public AuthReq build() {
            if (mContext == null || TextUtils.isEmpty(mAppid) || TextUtils.isEmpty(mScop)) {
                throw new RuntimeException("please set right app info (context,appKey,Scope)");
            }
            return new AuthReq(mContext, mAppid, mScop, mStatus);
        }

    }


}

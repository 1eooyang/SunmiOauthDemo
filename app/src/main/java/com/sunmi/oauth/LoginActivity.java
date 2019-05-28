package com.sunmi.oauth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sunmi.api.RetrofitProvider;
import com.sunmi.api.resp.MemberResp;
import com.sunmi.api.resp.Result;
import com.sunmi.api.resp.ShopInfoResp;
import com.sunmi.api.resp.TokenResp;
import com.sunmi.api.resp.UserInfoResp;
import com.sunmi.opensdk.OpenSunmi;
import com.sunmi.opensdk.auth.OauthCallback;
import com.sunmi.opensdk.auth.bean.AuthReq;
import com.sunmi.opensdk.auth.bean.AuthResp;
import com.sunmi.opensdk.base.BaseErrorMessage;
import com.sunmi.util.DeviceUtils;
import com.sunmi.util.RxUtil;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    private OpenSunmi mOpenSunmi;
    private TextView mTextView;
    private String getToken = "http://test.api.sunmi.com/api/merchant/app/oauth/1.0/?service=/getaccesstoken";
    private String Appid = "abcdefghijk";
    private String app_secret = "123456789wfx";
    private TextView mTv_token;
    private TextView tv_user_info;
    private TextView tv_shop_info;
    private TextView tv_refresh_token;
    private TextView tv_shop_list;
    private TokenResp mTokenRespData;
    private AuthResp mAuthResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        //mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mTextView = (TextView) findViewById(R.id.tv_resp);
        mTv_token = (TextView) findViewById(R.id.tv_token);
        tv_user_info = (TextView) findViewById(R.id.tv_user_info);
        tv_shop_info = (TextView) findViewById(R.id.tv_shop_info);
        tv_shop_list = (TextView) findViewById(R.id.tv_shop_list);
        tv_refresh_token = (TextView) findViewById(R.id.tv_refresh_token);


        AuthReq build = AuthReq.Builder(this)
                .setAppid(Appid)
                .setScop(AuthReq.DEFAULT_AND_MEMBER_SCOPE)
                .setStatus("SM")
                .build();


        mOpenSunmi = new OpenSunmi(build);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
                oauth();
            }
        });


    }

    private void oauth() {

        mOpenSunmi.authorize(this, new OauthCallback() {


            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(AuthResp authResp) {
                mAuthResp = authResp;
       /*         mTextView.setTextColor(getResources().getColor(R.color.ok));
                mTextView.setText(" 授权成功 ！ \n\n " + authResp.toString());*/
                showGreenText(mTextView, " 授权成功 ！ \n\n " + authResp.toString());
            }

            @Override
            public void onCancel() {
                showZiSeText(mTextView, "取消授权 ！");
           /*     mTextView.setTextColor(getResources().getColor(R.color.cancel));
                mTextView.setText("取消授权 ！");*/
            }


            @SuppressLint("SetTextI18n")
            @Override
            public void OnFailed(BaseErrorMessage baseErrorMessage) {
                showRedText(mTextView, "授权失败 ！ \n\n errCode : " + baseErrorMessage.getErrorCode() + "  \n\n errMsg : " + baseErrorMessage.getErrorMessage());
                mTextView.setTextColor(getResources().getColor(R.color.error));
                mTextView.setText("授权失败 ！ \n\n errCode : " + baseErrorMessage.getErrorCode() + "  \n\n errMsg : " + baseErrorMessage.getErrorMessage());
            }
        });

    }


    public void showGreenText(TextView textView, String text) {
        textView.setTextColor(getResources().getColor(R.color.ok));
        textView.setText(text);
    }

    public void showZiSeText(TextView textView, String text) {
        textView.setTextColor(getResources().getColor(R.color.cancel));
        textView.setText(text);
    }

    public void showRedText(TextView textView, String text) {
        textView.setTextColor(getResources().getColor(R.color.error));
        textView.setText(text);
    }


    @SuppressLint("CheckResult")
    public void getToken(View view) {
        if (mAuthResp == null) {
            // Toast.makeText(this, "请先授权登录获取code", Toast.LENGTH_SHORT).show();
            showRedText(mTv_token, "请先授权登录获取code");
            return;
        }
        RetrofitProvider.instance().getApiService()
                .getToken(Appid, app_secret, mAuthResp.getCode(), "authorization_code")
                .compose(RxUtil.<Result<TokenResp>>changeIO())
                .subscribe(new Consumer<Result<TokenResp>>() {
                    @Override
                    public void accept(Result<TokenResp> tokenResp) throws Exception {
                        if (tokenResp.getCode() == 1) {
                            if (tokenResp.getData() != null) {
                                mTokenRespData = tokenResp.getData();
                                // mTv_token.setText(tokenResp.getData().toString());
                                showGreenText(mTv_token, tokenResp.getData().toString());
                                return;
                            }
                        }
                        showRedText(mTv_token, tokenResp.getMsg());
                        // mTv_token.setText(tokenResp.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //  mTv_token.setText(throwable.getLocalizedMessage());
                        showRedText(mTv_token, throwable.getLocalizedMessage());
                    }
                });
    }


    @SuppressLint("CheckResult")
    public void getUserInfo(View view) {
        if (mTokenRespData == null || mAuthResp == null) {
            //Toast.makeText(this, "请先授权登录和获取token", Toast.LENGTH_SHORT).show();
            showRedText(tv_user_info, "请先授权登录和获取token");
            return;
        }

        RetrofitProvider.instance().getApiService().getUserInfo(mTokenRespData.getOpenid(), mTokenRespData.getAccess_token())
                .compose(RxUtil.<Result<UserInfoResp>>changeIO())
                .subscribe(new Consumer<Result<UserInfoResp>>() {
                    @Override
                    public void accept(Result<UserInfoResp> userInfoRespResult) throws Exception {
                        if (userInfoRespResult.getCode() == 1) {
                            if (userInfoRespResult.getData() != null) {
                                showGreenText(tv_user_info, userInfoRespResult.getData().toString());
                                // tv_user_info.setText(userInfoRespResult.getData().toString());
                                return;
                            }
                        }
                        showRedText(tv_user_info, userInfoRespResult.getMsg());
                        //tv_user_info.setText(userInfoRespResult.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showRedText(tv_user_info, throwable.getLocalizedMessage());
                        //tv_user_info.setText(throwable.getLocalizedMessage());
                    }
                });


    }

    @SuppressLint("CheckResult")
    public void getShopInfo(View view) {
        if (mTokenRespData == null || mAuthResp == null) {
            showRedText(tv_shop_info, "请先授权登录和获取token");
            //Toast.makeText(this, "请先授权登录和获取token", Toast.LENGTH_SHORT).show();
            return;
        }

        RetrofitProvider.instance().getApiService().getShopInfo(mTokenRespData.getOpenid(), mTokenRespData.getAccess_token(), DeviceUtils.getMSN())
                .compose(RxUtil.<Result<ShopInfoResp>>changeIO())
                .subscribe(new Consumer<Result<ShopInfoResp>>() {
                    @Override
                    public void accept(Result<ShopInfoResp> userInfoRespResult) throws Exception {
                        if (userInfoRespResult.getCode() == 1) {
                            if (userInfoRespResult.getData() != null) {
                                showGreenText(tv_shop_info, userInfoRespResult.getData().toString());
                                return;
                            }
                        }
                        showRedText(tv_shop_info, userInfoRespResult.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showRedText(tv_shop_info, throwable.getLocalizedMessage());
                    }
                });


    }

    @SuppressLint("CheckResult")
    public void refreshToken(View view) {
        if (mTokenRespData == null || mAuthResp == null) {
            showRedText(tv_refresh_token, "请先授权登录和获取token");
            return;
        }

        RetrofitProvider.instance().getApiService().refreshToken(Appid, mTokenRespData.getRefresh_token(), "refresh_token")
                .compose(RxUtil.<Result<TokenResp>>changeIO())
                .subscribe(new Consumer<Result<TokenResp>>() {
                    @Override
                    public void accept(Result<TokenResp> userInfoRespResult) throws Exception {
                        if (userInfoRespResult.getCode() == 1) {
                            TokenResp data = userInfoRespResult.getData();
                            if (data != null) {
                                mTokenRespData.setAccess_token(data.getAccess_token());
                                mTokenRespData.setExpires_in(data.getExpires_in());
                                mTokenRespData.setRefresh_token(data.getRefresh_token());
                                showGreenText(mTv_token, mTokenRespData.toString());
                                showGreenText(tv_refresh_token, mTokenRespData.toString());
                             /*   tv_refresh_token.setText(mTokenRespData.toString());
                                mTv_token.setText(mTokenRespData.toString());*/
                                return;
                            }
                        }
                        showRedText(tv_refresh_token, userInfoRespResult.getMsg());
                        //tv_refresh_token.setText(userInfoRespResult.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showRedText(tv_refresh_token, throwable.getLocalizedMessage());
                        //  tv_refresh_token.setText(throwable.getLocalizedMessage());
                    }
                });


    }

    @SuppressLint("CheckResult")
    public void getMemberList(View view) {
        if (mTokenRespData == null || mAuthResp == null) {
            showRedText(tv_shop_list, "请先授权登录和获取token");
            // Toast.makeText(this, "请先授权登录和获取token", Toast.LENGTH_SHORT).show();
            return;
        }

        RetrofitProvider.instance().getApiService().getShopMemberList(mTokenRespData.getOpenid(), mTokenRespData.getAccess_token(), DeviceUtils.getMSN())
                .compose(RxUtil.<Result<List<MemberResp>>>changeIO())
                .subscribe(new Consumer<Result<List<MemberResp>>>() {
                    @Override
                    public void accept(Result<List<MemberResp>> userInfoRespResult) throws Exception {
                        if (userInfoRespResult.getCode() == 1) {

                            if (userInfoRespResult.getData() != null) {

                                showGreenText(tv_shop_list, userInfoRespResult.getData().toString());


                                return;
                            }
                        }
                        showRedText(tv_shop_list, userInfoRespResult.getMsg());
                        //tv_refresh_token.setText(userInfoRespResult.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showRedText(tv_shop_list, throwable.getLocalizedMessage());
                        //  tv_refresh_token.setText(throwable.getLocalizedMessage());
                    }
                });


    }


    public void clean() {
        mTextView.setText("");
        mTv_token.setText("");
        tv_user_info.setText("");
        tv_shop_info.setText("");
        tv_shop_list.setText("");
        tv_refresh_token.setText("");
    }

}


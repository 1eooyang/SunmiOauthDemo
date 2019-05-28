package com.sunmi.api;

import com.sunmi.api.resp.MemberResp;
import com.sunmi.api.resp.Result;
import com.sunmi.api.resp.ShopInfoResp;
import com.sunmi.api.resp.TokenResp;
import com.sunmi.api.resp.UserInfoResp;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：杨柳 on 2019/5/24 0024 10:50
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public interface ApiService {

    /**
     * 获取token
     */
    @FormUrlEncoded
    @POST("api/merchant/app/oauth/1.0/?service=/getaccesstoken")
    Flowable<Result<TokenResp>> getToken(@Field("app_key") String app_key,
                                        @Field("app_secret") String app_secret,
                                        @Field("code") String code,
                                        @Field("response_type") String response_type);


    /**
     * 刷新token
     */
    @FormUrlEncoded
    @POST("api/merchant/app/oauth/1.0/?service=/refreshtoken")
    Flowable<Result<TokenResp>> refreshToken(@Field("app_key") String openid,
                                     @Field("refresh_token") String refresh_token,
                                     @Field("response_type") String response_type);

    /**
     * 获取用户信息
     */
    @FormUrlEncoded
    @POST("api/merchant/app/oauth/1.0/?service=/getuserinfo")
    Flowable<Result<UserInfoResp>> getUserInfo(@Field("openid") String openid,
                                               @Field("access_token") String refresh_token);

    /**
     * 获取商户信息
     */
    @FormUrlEncoded
    @POST("api/merchant/app/oauth/1.0/?service=/getshopinfo")
    Flowable<Result<ShopInfoResp>> getShopInfo(@Field("openid") String openid,
                                               @Field("access_token") String refresh_token, @Field("sn") String sn);

    /**
     * 获取商户信息
     */
    @FormUrlEncoded
    @POST("api/merchant/app/oauth/1.0/?service=/getshopmember")
    Flowable<Result<List<MemberResp>>> getShopMemberList(@Field("openid") String openid,
                                                         @Field("access_token") String refresh_token, @Field("sn") String sn);


}

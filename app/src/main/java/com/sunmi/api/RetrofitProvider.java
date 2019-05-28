package com.sunmi.api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {


    private final Retrofit mRetrofit;

    private static class Holder {
        static RetrofitProvider instance = new RetrofitProvider();
    }

    public static RetrofitProvider instance() {
        return Holder.instance;
    }

    private RetrofitProvider() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);

        builder.addInterceptor(new LogInterceptor());

        OkHttpClient mOkHttpClient = builder.build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://test.api.sunmi.com/")
                //.baseUrl("http://101.37.150.120:8088/cyb/appapi/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();

    }

/*

    public <T> T getApiService(Class<T> clazz) {
        String url = BuildConfig.API_HOST;
        return getApiService(clazz, url);
    }
*/

    public ApiService getApiService() {
        return mRetrofit.create(ApiService.class);
    }


}

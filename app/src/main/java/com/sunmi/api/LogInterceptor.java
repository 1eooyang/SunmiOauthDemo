package com.sunmi.api;
/*
 * Created by yangliu on 2018/9/11 0011.
 */

import android.annotation.SuppressLint;
import android.os.SystemClock;

import com.sunmi.opensdk.utils.LogUtil;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LogInterceptor implements Interceptor {

    @SuppressLint("DefaultLocale")
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();


        long t1 = SystemClock.elapsedRealtime();//请求发起的时间

        // if ("POST".equals(request.method())) {
        try {

            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();

                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + URLDecoder.decode(body.encodedValue(i), "utf-8") + " ;\n ");
                }
                sb.delete(sb.length() - 1, sb.length());
           //     LogUtil.d("电子发票", String.format("发送请求: \n[%s] ", request.url()));

                LogUtil.d("SunmiOauth", "发送请求:" + request.url() + "\n{" + sb.toString() + "}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //   }


        Response  response =chain.proceed(request);
        try {


            long t2 = SystemClock.elapsedRealtime();//收到响应的时间

            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            String result = responseBody.string();

            result = result.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            result = result.replaceAll("\\+", "%2B");
            result = URLDecoder.decode(result, "utf-8");
            LogUtil.d("SunmiOauth", String.format("接收响应: %s毫秒 URL = [%s] \n %s ",
                    (t2 - t1),
                    response.request().url(),
                    result
            ));

        /*    String s = response.request().url().toString();
            if (!TextUtils.isEmpty(s) && !mpass.contains(s)) {
                Result result1 = AppUtils.getAppComponent().getGson().fromJson(result, Result.class);
                DataService.sendMsgToDS(StatisticsManager.getInstance(), AppUtils.getApp(), 0, s, result1.getCode(), (t2 - t1), 200, (result1.getCode() == 1) ? 1 : 0);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }


        return response;
    }


}

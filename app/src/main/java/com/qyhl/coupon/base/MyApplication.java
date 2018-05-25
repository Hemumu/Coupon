package com.qyhl.coupon.base;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 *
 * @author helin
 * @date 2018/05/22
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //网络框架全局配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //默认连接超时20s
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .writeTimeout(20000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}

package com.qyhl.coupon.utils;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.IGenericsSerializator;

/**
 * Created by PVer on 2017/10/4.
 */

public class JsonGenericsSerializator implements IGenericsSerializator {
    Gson mGson = new Gson();

    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        return mGson.fromJson(response, classOfT);
    }
}

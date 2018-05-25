package com.qyhl.coupon.utils;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.IGenericsSerializator;

/**
 * Created by helin on 2018/05/04.
 */

public class JsonGenericsSerializator implements IGenericsSerializator {
    Gson mGson = new Gson();

    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        return mGson.fromJson(response, classOfT);
    }
}

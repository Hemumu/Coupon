package com.qyhl.coupon.mvp.home;

import android.util.Log;

import com.qyhl.coupon.common.Url;
import com.qyhl.coupon.entity.json.JsonShopTag;
import com.qyhl.coupon.utils.JsonGenericsSerializator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * @author helin
 */
public class HomeModel implements HomeContract.HomeModel {


    private final HomeContract.HomePresenter mPresenter;

    HomeModel (HomeContract.HomePresenter presenter){
        mPresenter =presenter;
    }

    @Override
    public void getTagList() {
        OkHttpUtils
                .get()
                .url(Url.TAG_URL)
                .build()
                .execute(new GenericsCallback<JsonShopTag>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (!call.isCanceled()) {
                            Log.e("error_http", e.getMessage());
                            mPresenter.error();
                        }
                    }

                    @Override
                    public void onResponse(JsonShopTag response, int id) {
                        mPresenter.success(response.getData());
                    }
                });

    }
}

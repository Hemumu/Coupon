package com.qyhl.coupon.mvp.tag;


import android.util.Log;

import com.qyhl.coupon.common.Url;
import com.qyhl.coupon.entity.json.JsonAlbum;
import com.qyhl.coupon.utils.JsonGenericsSerializator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * @author helin
 */
public class TagShopModel implements TagShopContract.TagShopModel {


    private final TagShopContract.TagShopPresenter mPresenter;

    TagShopModel(TagShopContract.TagShopPresenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getAlbumShop(String tag, String secTag, String type, int page, final boolean isTop) {
        OkHttpUtils
                .get()
                .url(Url.ALBUM_SHOP_LIST)
                .addParams("tag", tag)
                .addParams("sec_tag", secTag)
                .addParams("page", page+"")
                .addParams("sort", type)
                .addParams("sex", "1")
                .build()
                .execute(new GenericsCallback<JsonAlbum>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (!call.isCanceled()) {
                            Log.e("error_http", e.getMessage());
                            mPresenter.error();
                        }
                    }

                    @Override
                    public void onResponse(JsonAlbum response, int id) {
                        mPresenter.success(response.getData().getData(),isTop);
                    }
                });
    }
}

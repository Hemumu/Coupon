package com.qyhl.coupon.mvp.album;


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
public class AlbumModel implements AlbumContract.AlbumModel {


    private final AlbumContract.AlbumPresenter mPresenter;

    AlbumModel(AlbumContract.AlbumPresenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getAlbumShop(String tag, final boolean isTop, int page) {
        //http://apiv2.sqyhq.cn/articles?tag=%E5%A5%B3%E8%A3%85&page=1&sort=hot&cat=&q=&sex=1&sec_tag=
        OkHttpUtils
                .get()
                .url(Url.ALBUM_SHOP_LIST)
                .addParams("tag", tag)
                .addParams("page", page+"")
                .addParams("sort", "hot")
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

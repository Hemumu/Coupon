package com.qyhl.coupon.mvp.album;

import com.qyhl.coupon.entity.ShopDetailsBean;

import java.util.List;

/**
 * @author helin
 */
public interface AlbumContract {

    interface AlbumView {

        void onError();

        void onSuccess(List<ShopDetailsBean> data,boolean isTop);
    }

    interface AlbumModel {
        /**
         * 获取专场最热列表
         *
         * @param tag
         */
        void getAlbumShop(String tag,boolean isTop,int page);
    }

    interface AlbumPresenter {

        /**
         * 获取失败
         */
        void error();

        /**
         * 获取成功
         *
         * @param data
         */
        void success(List<ShopDetailsBean> data,boolean isTop);

        /**
         * 获取专场视频列表
         * @param name
         */
        void getAlbumShop(String name,boolean isTop,int page);
    }
}

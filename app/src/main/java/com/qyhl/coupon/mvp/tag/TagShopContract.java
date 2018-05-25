package com.qyhl.coupon.mvp.tag;

import com.qyhl.coupon.entity.ShopDetailsBean;

import java.util.List;

/**
 * @author helin
 */
public interface TagShopContract {

    interface TagShopView {

        void onError();

        void onSuccess(List<ShopDetailsBean> data,boolean isTop);
    }

    interface TagShopModel {
        /**
         * 获取专场最热列表
         *
         * @param tag
         */
        void getAlbumShop(String tag,String secTag,String type,int page ,boolean isTop);
    }

    interface TagShopPresenter {

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
         */
        void getAlbumShop(String tag,String secTag,String type,int page,boolean isTop);
    }
}

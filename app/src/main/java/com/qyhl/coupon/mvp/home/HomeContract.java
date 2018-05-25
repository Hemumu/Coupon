package com.qyhl.coupon.mvp.home;

import com.qyhl.coupon.entity.ShopTagsBean;

import java.util.List;

/**
 * home
 * @author helin
 */
public interface HomeContract {

    interface HomeView{

        /**
         * 获取标签成功
         * @param data
         */
        void onSuccess(List<ShopTagsBean> data);

        void onError();
    }

    interface  HomePresenter{

        void error();
        /**
         * 获取标签成功
         * @param data
         */
        void success(List<ShopTagsBean> data);

        /**
         * 获取Tag
         */
        void getTagList();
    }


    interface HomeModel {
        /**
         * 专场Tag获取
         */
        void getTagList();
    }

}

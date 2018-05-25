package com.qyhl.coupon.mvp.home;

import com.qyhl.coupon.entity.ShopTagsBean;

import java.util.List;

/**
 * 主页
 *
 * @author
 */
public class HomePresenter implements HomeContract.HomePresenter {

    private final HomeContract.HomeView mView;
    private final HomeModel mModel;

    HomePresenter (HomeContract.HomeView view){
        mView = view;
        mModel = new HomeModel(this);
    }

    @Override
    public void error() {
        mView.onError();
    }

    @Override
    public void success(List<ShopTagsBean> data) {
        mView.onSuccess(data);
    }

    @Override
    public void getTagList() {
        mModel.getTagList();
    }
}

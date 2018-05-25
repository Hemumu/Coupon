package com.qyhl.coupon.mvp.album;

import com.qyhl.coupon.entity.ShopDetailsBean;

import java.util.List;

/**
 * @author helin
 * 专场
 *
 */
public class AlbumPresenter implements AlbumContract.AlbumPresenter {

    private final AlbumContract.AlbumView mView;
    private final AlbumModel mModel;

    AlbumPresenter(AlbumContract.AlbumView view){
        this.mView =view;
        mModel = new AlbumModel(this);

    }

    @Override
    public void error() {
        mView.onError();
    }

    @Override
    public void success(List<ShopDetailsBean> data,boolean isTop) {
        mView.onSuccess(data,isTop);
    }

    @Override
    public void getAlbumShop(String name,boolean isTop,int page) {
        mModel.getAlbumShop(name,isTop,page);
    }
}

package com.qyhl.coupon.mvp.tag;

import com.qyhl.coupon.entity.ShopDetailsBean;

import java.util.List;

/**
 * 标签
 * @author helin
 */
public class TagShopPresenter implements TagShopContract.TagShopPresenter {

    private final TagShopContract.TagShopView mView;
    private final TagShopModel mModel;

    TagShopPresenter(TagShopContract.TagShopView view){
        this.mView =view;
        mModel = new TagShopModel(this);
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
    public void getAlbumShop(String tag,String secTag,String type,int page,boolean isTop) {
        mModel.getAlbumShop(tag,secTag,type,page,isTop);
    }
}

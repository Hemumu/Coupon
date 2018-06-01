package com.qyhl.coupon.mvp.shop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qyhl.coupon.R;
import com.qyhl.coupon.base.BaseFragment;

/**
 * 商品
 * @author helin
 */
public class ShopFragment extends BaseFragment {

    /**
     * 数据是否第一次加载
     */
    private boolean mIsInited;
    /**
     * 布局界面初始化
     */
    private boolean mIsPrepared;
    public ShopFragment() {
    }

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mIsPrepared = true;
        initView();
        lazyLoad();
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    /**
     * 初始化
     */
    private void initView() {
        Log.e("initView","初始化控件222");
    }

    /**
     * 加载数据
     */
    private void loadData() {
        mIsInited = true;
        Log.e("loadData","*****Shop*****");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    /**
     * 懒加载数据
     */
    public void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsInited) {
            //异步初始化，在初始化后显示正常UI
            loadData();
        }
    }


}

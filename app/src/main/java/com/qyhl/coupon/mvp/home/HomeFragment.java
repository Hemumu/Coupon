package com.qyhl.coupon.mvp.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.qyhl.coupon.R;
import com.qyhl.coupon.base.BaseFragment;
import com.qyhl.coupon.entity.ShopTagsBean;
import com.qyhl.coupon.mvp.album.AlbumFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 主页
 *
 * @author helin
 */
public class HomeFragment extends BaseFragment implements HomeContract.HomeView{

    /**
     * 顶部标题
     */
    @BindView(R.id.group_tabLayout)
    SlidingTabLayout mGroupTabLayout;
    @BindView(R.id.shop_viewPager)
    /**
     * viewPage
     */
    ViewPager mShopViewPager;
    Unbinder unbinder;
    /**
     * 数据是否第一次加载
     */
    private boolean mIsInited;
    /**
     * 布局界面初始化
     */
    private boolean mIsPrepared;

    private HomePresenter mPresenter;
    private List<Fragment> mFragmentList;
    private HomePageAdapter mViewPageadapter;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 初始化
     */
    private void initView() {
        mPresenter  = new HomePresenter(this);
        mFragmentList = new ArrayList<>();
    }



    @Override
    public void onSuccess(List<ShopTagsBean> data) {
        dismissLoading();
        for (int i = 0; i <data.size() ; i++) {
            ShopTagsBean shopBean = data.get(i);
            mFragmentList.add(AlbumFragment.newInstance(shopBean));
        }
        mViewPageadapter = new HomePageAdapter(getFragmentManager(), mFragmentList);
        mViewPageadapter.setData(data);
        mShopViewPager.setOffscreenPageLimit(1);
        mShopViewPager.setAdapter(mViewPageadapter);
        mGroupTabLayout.setViewPager(mShopViewPager);
        mViewPageadapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        dismissLoading();
        showTast("获取数据失败");
    }

    /**
     * 加载数据
     */
    private void loadData() {
        mIsInited = true;
        showSimpleLoading();
        mPresenter.getTagList();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}


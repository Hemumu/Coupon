package com.qyhl.coupon.mvp.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.qyhl.coupon.R;
import com.qyhl.coupon.base.BaseActivity;
import com.qyhl.coupon.entity.ShopTagsBean;
import com.qyhl.coupon.mvp.album.AlbumFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author helin
 * <p>
 * 主要界面
 */
public class MainActivity extends BaseActivity implements HomeContract.HomeView{

    /**
     * 顶部标题
     */
    @BindView(R.id.group_tabLayout)
    SlidingTabLayout mGroupTabLayout;
    /**
     * viewPage
     */
    @BindView(R.id.shop_viewPager)
    ViewPager mShopViewPager;
    private HomePresenter mPresenter;
    private List<Fragment> mFragmentList;
    private HomePageAdapter mViewPageadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter  = new HomePresenter(this);
        showSimpleLoading();
        mPresenter.getTagList();
        initView();
    }

    private void initView() {
        mFragmentList = new ArrayList<>();
    }



    @Override
    public void onSuccess(List<ShopTagsBean> data) {
        dismissLoading();
        for (int i = 0; i <data.size() ; i++) {
            ShopTagsBean shopBean = data.get(i);
             mFragmentList.add(AlbumFragment.newInstance(shopBean));
        }
        mViewPageadapter = new HomePageAdapter(getSupportFragmentManager(), mFragmentList);
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
}

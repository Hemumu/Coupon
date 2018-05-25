package com.qyhl.coupon.mvp.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qyhl.coupon.entity.ShopTagsBean;

import java.util.List;

/**
 * 资讯PageAdapter
 */

public class HomePageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragment;
    private List<ShopTagsBean> data;

    public HomePageAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.mFragment = data;
    }

    public void setData(List<ShopTagsBean> sectionsData) {
        this.data = sectionsData;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}

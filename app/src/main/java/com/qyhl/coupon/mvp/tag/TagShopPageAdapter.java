package com.qyhl.coupon.mvp.tag;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 资讯PageAdapter
 */

public class TagShopPageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragment;
    private String[] data;

    public TagShopPageAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.mFragment = data;
    }

    public void setData(String[] titls) {
        this.data = titls;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return data.length;
    }
}

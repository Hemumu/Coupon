package com.qyhl.coupon.mvp.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by qyhl on 2017/11/7.
 * 首页适配器
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public HomePagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }


    @Override
    public int getCount() {
        return list.size();
    }

}

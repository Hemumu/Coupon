package com.qinanyu.bannerview.listener;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by helin on 2016/10/20 16:28.
 */
public class SimplePageChangeListener implements ViewPager.OnPageChangeListener {
    private final TextView mBannerText;
    private final String[] BannerStr;
    private ArrayList<ImageView> pointViews;
    private int[] page_indicatorId;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener){
        this.onPageChangeListener=onPageChangeListener;
    }

    public SimplePageChangeListener(ArrayList<ImageView> pointViews, int[] page_indicatorId, TextView bannerText, String[] bannerStrs){
        this.pointViews=pointViews;
        this.page_indicatorId = page_indicatorId;
        this.mBannerText =bannerText;
        this.BannerStr =bannerStrs;
        if(BannerStr.length==1  ){
            mBannerText.setText(BannerStr[0]);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {
        if(onPageChangeListener != null){
            onPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if(onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int index) {

        if( BannerStr!=null ){
            mBannerText.setText(BannerStr[index]);
        }
        for (int i = 0; i < pointViews.size(); i++) {
            pointViews.get(index).setImageResource(page_indicatorId[1]);
            if (index != i) {
                pointViews.get(i).setImageResource(page_indicatorId[0]);
            }
        }
        if(onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(index);
        }

    }

}

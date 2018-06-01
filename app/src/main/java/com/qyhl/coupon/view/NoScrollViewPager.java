package com.qyhl.coupon.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不可滑动的viewpager
 * @author helin
 */
public class NoScrollViewPager extends ViewPager {
    private boolean noScroll = true;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public ViewPager setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
        return null;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        if (noScroll) {
            return false;
        } else {
            return super.onTouchEvent(arg0);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll) {
            return false;
        } else {
            return super.onInterceptTouchEvent(arg0);
        }
    }


    private boolean isHasScrollAnim=true;

    public void setHasScrollAnim(boolean isHasScrollAnim){
        this.isHasScrollAnim=isHasScrollAnim;
    }
    /**
     * 设置其是否去求切换时的滚动动画
     *isHasScrollAnim为false时，会去除滚动效果
     */
    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,isHasScrollAnim);
    }
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }


}
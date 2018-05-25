package com.qinanyu.bannerview.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qinanyu.bannerview.R;
import com.qinanyu.bannerview.adapter.SimplePageAdapter;
import com.qinanyu.bannerview.holder.SimpleHolderCreator;
import com.qinanyu.bannerview.listener.OnItemClickListener;
import com.qinanyu.bannerview.listener.SimplePageChangeListener;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helin on 2016/10/19 11:30.
 */

public class SimpleBannerView<T> extends LinearLayout {
    private ViewPagerScroller scroller;
    private SimpleViewPage viewPager;
    SimplePageChangeListener pageChangeListener;
    private int[] page_indicatorId;
    private AdSwitchTask adSwitchTask;
    private ViewGroup parent;

    /**
     * 翻页指示点
     */
    private ViewGroup loPageTurningPoint;
    /**
     * 指示点图片数组
     */
    private ArrayList<ImageView> mPointViews = new ArrayList<ImageView>();
    private ViewPager.OnPageChangeListener onPageChangeListener;
    /**
     * Viewpage Adapter
     */
    private SimplePageAdapter mAdapter;
    /**
     * 数据
     */
    private List<T> mDatas;

    /**
     * 是否自动翻页
     */
    private boolean canTurn = false;
    /**
     * 翻页间隔时间
     */
    private long autoTurningTime;
    /**
     * 当前是否正在翻页
     */
    private boolean turning;

    /**
     * 是否循环翻页
     */
    private boolean canLoop = true;
    //
    private TextView mBannerText;

    private LinearLayout mBannerTextLayout;

    /**
     * 是否手动滑动
     */
    private boolean canTouch = true;


    /**
     * 翻页指示点的位置
     */
    public enum PageIndicatorAlign {
        ALIGN_PARENT_LEFT, ALIGN_PARENT_RIGHT, CENTER_HORIZONTAL
    }

    public SimpleBannerView(Context context) {
        super(context);
        init(context);
    }

    public SimpleBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View hView = LayoutInflater.from(context).inflate(
                R.layout.include_viewpage, this, true);
        viewPager = (SimpleViewPage) hView.findViewById(R.id.cbLoopViewPager);
        loPageTurningPoint = (ViewGroup) hView
                .findViewById(R.id.loPageTurningPoint);
        mBannerText = (TextView) hView.findViewById(R.id.banner_text);
        mBannerTextLayout = (LinearLayout) hView.findViewById(R.id.banner_text_ll);
        initViewPagerScroll();
        adSwitchTask = new AdSwitchTask(this);
    }

    /**
     * 隐藏标题栏
     * 默认为显示
     */
    public SimpleBannerView<T> hideBannerText(boolean b) {
        if (b == true) {
            mBannerTextLayout.setVisibility(GONE);
        } else {
            mBannerTextLayout.setVisibility(VISIBLE);
        }
        return this;
    }

    /**
     * 隐藏标题
     * 默认为显示
     */
    public SimpleBannerView<T> hideBannerTitle(boolean b) {
        if (b == true) {
            mBannerText.setVisibility(INVISIBLE);
        } else {
            mBannerText.setVisibility(VISIBLE);
        }
        return this;
    }

    /**
     * 设置标题栏背景
     */
    public SimpleBannerView<T> setBannerBackground(int bg) {
        mBannerTextLayout.setBackgroundResource(bg);
        return this;
    }

    /**
     * 自定义翻页动画效果
     *
     * @param transformer
     * @return
     */
    public SimpleBannerView<T> setPageTransformer(ViewPager.PageTransformer transformer) {
        viewPager.setPageTransformer(true, transformer);
        return this;
    }


    /**
     * 手动滑动
     */
    public SimpleBannerView<T> canTouchScroll(boolean b) {
        canTouch = b;
        return this;
    }


    /**
     * 自动滚动线程
     * 使用WeakReference防止内存泄露的问题
     */
    static class AdSwitchTask implements Runnable {

        private final WeakReference<SimpleBannerView> reference;

        AdSwitchTask(SimpleBannerView convenientBanner) {
            this.reference = new WeakReference<SimpleBannerView>(convenientBanner);
        }

        @Override
        public void run() {
            SimpleBannerView convenientBanner = reference.get();
            if (convenientBanner != null) {
                if (convenientBanner.viewPager != null && convenientBanner.turning) {
                    int page = convenientBanner.viewPager.getCurrentItem() + 1;
                    convenientBanner.viewPager.setCurrentItem(page);
                    convenientBanner.postDelayed(convenientBanner.adSwitchTask, convenientBanner.autoTurningTime);
                }
            }
        }
    }

    /***
     * 是否开启了翻页
     *
     * @return
     */
    public boolean isTurning() {
        return turning;
    }

    /***
     * 开始翻页
     *
     * @param autoTurningTime 自动翻页时间
     * @return
     */
    public SimpleBannerView startTurning(long autoTurningTime) {
        //如果是正在翻页的话先停掉
        if (turning) {
            stopTurning();
        }
        //设置可以翻页并开启翻页
        canTurn = true;
        this.autoTurningTime = autoTurningTime;
        turning = true;
        postDelayed(adSwitchTask, autoTurningTime);
        return this;
    }

    /**
     * 停止自动翻页
     */
    public void stopTurning() {
        turning = false;
        removeCallbacks(adSwitchTask);
    }


    public void setTextBanner(String tr) {
        mBannerText.setText(tr);
    }

    /**
     * 底部指示器资源图片
     *
     * @param page_indicatorId
     */
    public SimpleBannerView setPageIndicator(int[] page_indicatorId, String[] bannerText) {
        loPageTurningPoint.removeAllViews();
        mPointViews.clear();
        this.page_indicatorId = page_indicatorId;
        if (mDatas == null) {
            return this;
        }
        if (mDatas.size() == 1) {
            return this;//当只有一张图片时，不显示指示器
        }
        for (int count = 0; count < mDatas.size(); count++) {
            // 翻页指示的点
            ImageView pointView = new ImageView(getContext());
            pointView.setPadding(5, 0, 5, 0);
            if (mPointViews.isEmpty()) {
                pointView.setImageResource(page_indicatorId[1]);
            } else {
                pointView.setImageResource(page_indicatorId[0]);
            }
            mPointViews.add(pointView);
            loPageTurningPoint.addView(pointView);
        }

        pageChangeListener = new SimplePageChangeListener(mPointViews,
                page_indicatorId, mBannerText, bannerText);
        viewPager.addOnPageChangeListener(pageChangeListener);
        pageChangeListener.onPageSelected(viewPager.getRealItem());
        if (onPageChangeListener != null) {
            pageChangeListener.addOnPageChangeListener(onPageChangeListener);
        }
        return this;
    }


    /**
     * 设置底部指示器是否可见
     *
     * @param visible
     */
    public SimpleBannerView setPointViewVisible(boolean visible) {
        loPageTurningPoint.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置翻页监听器
     *
     * @param onPageChangeListener
     * @return
     */
    public SimpleBannerView addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
        //如果有默认的监听器（即是使用了默认的翻页指示器）则把用户设置的依附到默认的上面，否则就直接设置
        if (pageChangeListener != null) {
            pageChangeListener.addOnPageChangeListener(onPageChangeListener);
            viewPager.addOnPageChangeListener(pageChangeListener);
        } else {
            viewPager.addOnPageChangeListener(onPageChangeListener);
        }

        return this;
    }


    /**
     * 设置翻页数据
     *
     * @param datas
     * @return
     */
    public SimpleBannerView setPages(SimpleHolderCreator creator, List<T> datas) {
        this.mDatas = datas;
        mAdapter = new SimplePageAdapter<>(mDatas, creator, mBannerText);
        viewPager.setAdapter(mAdapter, canLoop);
        mAdapter.notifyDataSetChanged();
        viewPager.invalidate();
        return this;
    }


    /**
     * 通过java反射设置ViewPager的滑动速度
     */
    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            scroller = new ViewPagerScroller(
                    viewPager.getContext());
            mScroller.set(viewPager, scroller);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指示器的方向
     *
     * @param align 三个方向：居左 （RelativeLayout.ALIGN_PARENT_LEFT），居中 （RelativeLayout.CENTER_HORIZONTAL），居右 （RelativeLayout.ALIGN_PARENT_RIGHT）
     * @return
     */
    public SimpleBannerView setPageIndicatorAlign(PageIndicatorAlign align) {
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) loPageTurningPoint.getLayoutParams();
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, align == PageIndicatorAlign.ALIGN_PARENT_LEFT ? RelativeLayout.TRUE : 0);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, align == PageIndicatorAlign.ALIGN_PARENT_RIGHT ? RelativeLayout.TRUE : 0);
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, align == PageIndicatorAlign.CENTER_HORIZONTAL ? RelativeLayout.TRUE : 0);
//        loPageTurningPoint.setLayoutParams(layoutParams);
        return this;
    }

    //触碰控件的时候，翻页应该停止，离开的时候如果之前是开启了翻页的话则重新启动翻页
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_OUTSIDE) {
            // 开始翻页
            if (canTurn) {
                startTurning(autoTurningTime);
            }
        } else if (action == MotionEvent.ACTION_DOWN) {
            // 停止翻页
            if (canTurn) {
                stopTurning();
            }
        }
        if (parent != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否循环
     *
     * @param canLoop
     */
    public SimpleBannerView<T> setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
        viewPager.setCanLoop(canLoop);
        return this;
    }

    /**
     * 设置ViewPager的滚动速度
     *
     * @param scrollDuration
     */
    public void setScrollDuration(int scrollDuration) {
        scroller.setScrollDuration(scrollDuration);
    }


    /**
     * 监听item点击
     *
     * @param onItemClickListener
     */
    public SimpleBannerView setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            viewPager.setOnItemClickListener(null);
            return this;
        }
        viewPager.setOnItemClickListener(onItemClickListener);
        return this;
    }

    public boolean isManualPageable() {
        return viewPager.isCanScroll();
    }

    public void setManualPageable(boolean manualPageable) {
        viewPager.setCanScroll(manualPageable);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (parent != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return canTouch && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (parent != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return canTouch && super.onTouchEvent(event);
    }

    public SimpleBannerView<T> setNestParent(ViewGroup parent) {
        this.parent = parent;
        return this;
    }


}

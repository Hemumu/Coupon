package com.qyhl.coupon.view.ltab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

public class HomeTab extends LinearLayout implements View.OnClickListener {
    private Context context;
    //资源
    private String[] texts;
    private Drawable[] seleIcons;
    private Drawable[] normalIcons;
    private String[] seleUrls;
    private String[] normalUrls;

    //items
    private HomeTabItem[] items;
    //当前选中位置
    private int position = -1;
    //itme总数
    private int count = 0;

    //字体和图片间距
    private int imgPaddint = 0;
    //字体大小
    private int dpsize = 0;
    //图片宽高
    private int imgWidth = 0;
    private int imgHeight = 0;

    //字体选中颜色
    private int seleColor;
    //字体未选中颜色
    private int normalColor;

    private ViewPager viewPager;


    public HomeTab(Context context) {
        this(context, null);
    }

    public HomeTab(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setVerticalGravity(Gravity.CENTER);
    }

    public void setupWithViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        count = viewPager.getAdapter().getCount();
        items = new HomeTabItem[count];
        for (int i = 0; i < count; i++) {
            initItem(i);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCheck(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //选中第一项
        setCheck(0);
    }

    private void initItem(int i) {
        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
        HomeTabItem item = new HomeTabItem(context);
        item.setLayoutParams(layoutParams);
        if (seleIcons != null) {
            item.setCheckDrawable(seleIcons[i]);
        }
        if (normalIcons != null) {
            item.setNoCheckDrawable(normalIcons[i]);
        }
        if (seleUrls != null) {
            item.setCheckUrl(seleUrls[i]);
        }
        if (normalUrls != null) {
            item.setNoCheckUrl(normalUrls[i]);
        }

        item.setText(texts[i]);
        item.setOnClickListener(this);
        item.setImgPadding(imgPaddint);
        item.setSeleColor(seleColor);
        item.setTextSize(dpsize);
        item.setImgSize(imgWidth, imgHeight);
        item.setNormalColor(normalColor);

        item.setTag(i);
        item.setNoCheck();
        addView(item);
        items[i] = item;
    }

    @Override
    public void onClick(View v) {
        setCheck((Integer) v.getTag());
    }

    private void setCheck(int pos) {
        if (pos != position) {
            position = pos;
            for (int i = 0; i < count; i++) {
                if (i == position) {
                    continue;
                }
                if (items[i].isCheck()) {
                    items[i].setNoCheck();
                }
            }
            if (!items[position].isCheck()) {
                items[position].setCheck();
            }
            viewPager.setCurrentItem(position);
        }
    }


    public void setDataText(String[] texts) {
        this.texts = texts;
    }

    public void setSeleIcons(int[] seleIcons) {
        this.seleIcons = new Drawable[seleIcons.length];
        for (int i = 0; i < seleIcons.length; i++) {
            this.seleIcons[i] = ContextCompat.getDrawable(context, seleIcons[i]);
        }
    }

    public void setNormalIcons(int[] normalIcons) {
        this.normalIcons = new Drawable[normalIcons.length];
        for (int i = 0; i < normalIcons.length; i++) {
            this.normalIcons[i] = ContextCompat.getDrawable(context, normalIcons[i]);
        }
    }

    public void setSeleUrls(String[] seleUrls) {
        this.seleUrls = seleUrls;
    }

    public void setNormalUrls(String[] normalUrls) {
        this.normalUrls = normalUrls;
    }

    public void setSeleColor(int seleColor) {
        this.seleColor = seleColor;
    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
    }

    public void setImgPaddint(int imgPaddint) {
        this.imgPaddint = imgPaddint;
    }

    public void setTextSize(int dpsize) {
        this.dpsize = dpsize;
    }

    public void setImgSize(int width, int height) {
        imgWidth = width;
        imgHeight = height;
    }
}

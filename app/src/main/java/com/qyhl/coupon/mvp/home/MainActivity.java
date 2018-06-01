package com.qyhl.coupon.mvp.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

import com.qyhl.coupon.R;
import com.qyhl.coupon.base.BaseActivity;
import com.qyhl.coupon.mvp.shop.ShopFragment;
import com.qyhl.coupon.mvp.user.UserFragment;
import com.qyhl.coupon.utils.StringUtils;
import com.qyhl.coupon.view.NoScrollViewPager;
import com.qyhl.coupon.view.ltab.HomeTab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主界面
 * @author helin
 */
public class MainActivity extends BaseActivity {

    /**
     *
     */
    @BindView(R.id.homelayout)
    LinearLayout mHomelayout;
    /**
     * 顶部tabmenu
     */
    @BindView(R.id.tabbar)
    HomeTab mHomeTabbar;
    /**
     * viewPage
     */
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;


    /**
     * 菜单名称
     */
    private String[] mMenus = {"首页","订单","我的"};


    private int[] newSeleIcons = {R.drawable.icon_tab_home_select,R.drawable.icon_circle_pressed,R.drawable.icon_tab_user_select};

    private int[] newNormalIcons = {R.drawable.icon_tab_home,R.drawable.icon_circle_default,R.drawable.icon_tab_user};
    private HomePagerAdapter mAdapter;
    /**
     * fragment
     */
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mHomelayout.addOnLayoutChangeListener(this);
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(ShopFragment.newInstance());
        fragmentList.add(UserFragment.newInstance());
        mAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setHasScrollAnim(false);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAdapter);

        //设置底部菜单
        mHomeTabbar.setSeleIcons(newSeleIcons);
        mHomeTabbar.setNormalIcons(newNormalIcons);
        mHomeTabbar.setImgPaddint(StringUtils.dip2px(this, 2));
        mHomeTabbar.setSeleColor(ContextCompat.getColor(this, R.color.global_base_0));
        mHomeTabbar.setNormalColor(ContextCompat.getColor(this, R.color.global_base_0));
        mHomeTabbar.setDataText(mMenus);
        mHomeTabbar.setTextSize(12);
        mHomeTabbar.setImgSize(StringUtils.dip2px(this, 25), StringUtils.dip2px(this, 25));
        mHomeTabbar.setupWithViewPager(mViewPager);
    }


}

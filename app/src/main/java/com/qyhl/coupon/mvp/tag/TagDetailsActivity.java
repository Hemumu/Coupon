package com.qyhl.coupon.mvp.tag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.qyhl.coupon.R;
import com.qyhl.coupon.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author helin
 * 二级菜单商品
 */
public class TagDetailsActivity extends BaseActivity {

    @BindView(R.id.sec_tabLayout)
    SlidingTabLayout mSecTabLayout;
    @BindView(R.id.sec_shop_viewPager)
    ViewPager mSecShopViewPager;

    private List<Fragment> mFragmentList = new ArrayList<>();

    private String[] titls = {"推荐","销量","优惠券","最热"};
    private String[] sorts = {"tj","sale","coupon_price","hot"};
    private TagShopPageAdapter mViewPageadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_details);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        String tag =  bundle.getString("tag");
        String secTag =  bundle.getString("secTag");
        
        for (int i = 0; i <titls.length ; i++) {
            mFragmentList.add(TagshopFragment.newInstance(tag,secTag,sorts[i]));
        }

        mViewPageadapter = new TagShopPageAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPageadapter.setData(titls);
        mSecShopViewPager.setOffscreenPageLimit(1);
        mSecShopViewPager.setAdapter(mViewPageadapter);
        mSecTabLayout.setViewPager(mSecShopViewPager);
        mViewPageadapter.notifyDataSetChanged();

    }


}

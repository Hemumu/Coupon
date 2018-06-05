package com.qyhl.coupon.mvp.base;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qyhl.coupon.R;
import com.qyhl.coupon.base.BaseActivity;
import com.qyhl.coupon.mvp.home.MainActivity;
import com.qyhl.coupon.utils.MPermissionUtils;
import com.qyhl.coupon.utils.cache.SpfManager;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 引导页
 * @author helin
 */
public class SplashActivity extends BaseActivity {

    private BGABanner mBackgroundBanner, mForegroundBanner;
    ImageView splashIndicatorOne, splashIndicatorTwo, splashIndicatorThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //透明导航栏
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        isSkip();

    }


    /**
     * 是否跳过引导页
     */
    private void isSkip() {
        boolean index = SpfManager.getInstance(this).getBoolean("Skip", false);
        if( index ){
            Intent intent = new Intent(SplashActivity.this, LaunchActivity.class);
            startActivity(intent);
            finish();
        }else{
            SpfManager.getInstance(this).putBoolean("Skip", true);
            initView();
            setListener();
            processLogic();
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mBackgroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, 0, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                MPermissionUtils.requestPermissionsResult(SplashActivity.this, 1, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onPermissionDenied() {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });


        mBackgroundBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                reSet();
                switch (position) {
                    case 0:
                        splashIndicatorOne.setBackgroundResource(R.drawable.splash_indicator_checked);
                        break;
                    case 1:
                        splashIndicatorTwo.setBackgroundResource(R.drawable.splash_indicator_checked);
                        break;
                    case 2:
                        splashIndicatorThree.setBackgroundResource(R.drawable.splash_indicator_checked);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 重置指示器
     */
    private void reSet() {
        splashIndicatorOne.setBackgroundResource(R.drawable.splash_indicator_unchecked);
        splashIndicatorTwo.setBackgroundResource(R.drawable.splash_indicator_unchecked);
        splashIndicatorThree.setBackgroundResource(R.drawable.splash_indicator_unchecked);
    }

    /**
     * 设置数据源
     */
    private void processLogic() {
        mBackgroundBanner.setData(R.drawable.splash_bg0, R.drawable.splash_bg1, R.drawable.splash_bg2);
        mForegroundBanner.setData(R.drawable.splash_fg0, R.drawable.splash_fg1, R.drawable.splash_fg2);
    }

    private void initView() {
        setContentView(R.layout.activity_first);
        mBackgroundBanner = (BGABanner) findViewById(R.id.banner_guide_background);
        mForegroundBanner = (BGABanner) findViewById(R.id.banner_guide_foreground);
        splashIndicatorOne = (ImageView) findViewById(R.id.splash_indicator_one);
        splashIndicatorTwo = (ImageView) findViewById(R.id.splash_indicator_two);
        splashIndicatorThree = (ImageView) findViewById(R.id.splash_indicator_three);
    }
}

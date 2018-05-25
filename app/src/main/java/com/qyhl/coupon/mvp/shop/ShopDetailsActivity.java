package com.qyhl.coupon.mvp.shop;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.qyhl.coupon.R;
import com.qyhl.coupon.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 视频详情
 *
 * @author qyhl2
 */
public class ShopDetailsActivity extends BaseActivity {

    @BindView(R.id.web_view)
    LinearLayout mWebView;
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        Bundle bunld = getIntent().getExtras();
        String url =  bunld.getString("url");
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent( mWebView, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark),2)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        if (!mAgentWeb.back()){
            super.onBackPressed();
        }
    }
}

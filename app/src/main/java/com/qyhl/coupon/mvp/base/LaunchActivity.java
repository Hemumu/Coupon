package com.qyhl.coupon.mvp.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.qyhl.coupon.R;
import com.qyhl.coupon.mvp.home.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 启动页
 *
 * @author
 */
public class LaunchActivity extends AppCompatActivity {

    /**
     * 倒计时
     */
    private CountDownTimer mCountDownTimer;

    /**
     * 启动图
     */
    @BindView(R.id.boot_screen_cover)
    ImageView mBootScreenCover;

    /**
     * 跳过按钮
     */
    @BindView(R.id.skip_btn)
    TextView mSkipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCountDownTimer != null) {
                    mCountDownTimer.cancel();
                }
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        countDown(5000);
    }


    /**
     * 开启倒计时
     *
     * @param time
     */
    private void countDown(long time) {
        mCountDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    if (true) {
                        mSkipBtn.setText(millisUntilFinished / 1000 + 1 + " 跳过");
                    } else {
                        mSkipBtn.setText(millisUntilFinished / 1000 + 1 + " s");
                    }
                } catch (Exception e) {
                    Log.e("error_cd", e.getMessage() == null ? "null" : e.getMessage());
                }
            }

            @Override
            public void onFinish() {
                if (true) {
                    mSkipBtn.setText(1 + " 跳过");
                } else {
                    mSkipBtn.setText(1 + " s");
                }
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}

package com.qyhl.coupon.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.qyhl.coupon.utils.ContextUtilts;
import com.qyhl.coupon.view.dialog.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;


/**
 * Activity 基类
 *
 * Base Activity
 * @author helin
 */
public class BaseActivity extends AppCompatActivity implements View.OnLayoutChangeListener{

    /**
     *  Dialog
     */
    private LoadingDialog.Builder builder;

    /**
     * 软键盘监听
     */
    private InputListener inputListener;

    /**
     * 软件盘弹起后所占高度阀值
     */
    private int keyHeight = 0;
    /**
     * 屏幕高度
     */
    private int screenHeight;

    /**
     *  Create
     * @param savedInstanceState
     * @param persistentState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ContextUtilts.getInstance().setmContext(this);

        //获取屏幕高度
        Point size =new Point();
        this.getWindowManager().getDefaultDisplay().getSize(size);
        screenHeight = size.x;
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
    }

    /**
     * Destory
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消网络请求
        OkHttpUtils.getInstance().cancelTag(this);
    }

    /**
     * 提示框
     * @param msg
     */
    public void showTast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 隐藏软键盘
     */
    public void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

    }

    /**
     * 简单的dialog
     * @param title
     */
    public void showSimpleLoading(String title) {
        builder = new LoadingDialog.Builder(this);
        builder.setCanceledOnTouchOutside(false);
        builder.setTitle(title);
        builder.setCancelable(true);
        builder.getmDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                OkHttpUtils.getInstance().cancelTag(this);
            }
        });
        builder.show();
    }

    /**
     * 简单的dialog
     */
    public void showSimpleLoading() {
        builder = new LoadingDialog.Builder(this);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(true);
        builder.getmDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                OkHttpUtils.getInstance().cancelTag(this);
            }
        });
        builder.show();
    }

    public void dismissLoading() {
        if(builder!=null){
            builder.dismiss();
        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right,
                               int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            //软键盘弹起
            inputListener.showkeyboard();
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            //软键盘关闭
            inputListener.hideKeyboard();
        }
    }

    /**
     * 设置软键盘监听
     * @param inputListener
     */
    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    public interface InputListener {

        void showkeyboard();//软键盘弹起

        void hideKeyboard(); //软键盘关闭

    }

}

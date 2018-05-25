package com.qyhl.coupon.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.qyhl.coupon.view.dialog.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;


/**
 * Base Activity
 */
public class BaseActivity extends AppCompatActivity {

    private LoadingDialog.Builder builder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    public void showTast( String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    public void showSimpleLoading(String title) {
        builder = new LoadingDialog.Builder(this);
        builder.setCanceledOnTouchOutside(false);
        builder.setTitle(title);
        builder.setCancelable(true);
        builder.show();
    }

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

}

package com.qyhl.coupon.base;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.qyhl.coupon.view.dialog.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * @author helin
 * 基类 fragment
 */
public class BaseFragment  extends Fragment{

    private LoadingDialog.Builder builder;

    public void showTast(String msg){
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示Dialog
     * @param title
     */
    public void showSimpleLoading(String title) {
        builder = new LoadingDialog.Builder(this.getActivity());
        builder.setCanceledOnTouchOutside(false);
        builder.setTitle(title);
        builder.setCancelable(true);
        builder.show();
    }

    /**
     * 展示Dialog
     */
    public void showSimpleLoading() {
        builder = new LoadingDialog.Builder(this.getActivity());
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

    /**
     * 取消Dialog
     */
    public void dismissLoading() {
        if(builder!=null){
            builder.dismiss();
        }
    }




}

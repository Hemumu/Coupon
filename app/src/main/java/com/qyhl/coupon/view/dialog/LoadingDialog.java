package com.qyhl.coupon.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qyhl.coupon.R;

import java.lang.ref.WeakReference;


/**
 * Created by JYY on 2017/8/5.
 */

public class LoadingDialog {


    public static class Builder {


        private static class MyHandler extends Handler {
            private final WeakReference<Builder> mActivity;

            public MyHandler(Builder activity) {
                mActivity = new WeakReference<Builder>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Builder mLoading = mActivity.get();
                mLoading.mViewHolder.progressNum.setText(msg.what + "");
            }
        }

        private MyHandler mViewUpdateHandler = new MyHandler(this);

        private Context mContext;
        private Dialog mDialog;
        private ViewHolder mViewHolder;
        private View mView;


        public Dialog getmDialog() {
            return mDialog;
        }

        public void setmDialog(Dialog mDialog) {
            this.mDialog = mDialog;
        }

        public Builder(Activity context) {
            mContext = context;
            initView();
        }

        public Builder setTitle(CharSequence title) {
            mViewHolder.tvTitle.setText(title);
            return this;
        }

        public Builder setTitle(CharSequence title, int color) {
            mViewHolder.tvTitle.setText(title);
            mViewHolder.tvTitle.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        public Builder setTitle(int resid) {
            mViewHolder.tvTitle.setText(resid);
            return this;
        }

        public Builder setTitle(int resid, int color) {
            mViewHolder.tvTitle.setText(resid);
            mViewHolder.tvTitle.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        public Builder setCancelable(boolean flag) {
            mDialog.setCancelable(flag);
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean flag) {
            mDialog.setCanceledOnTouchOutside(flag);
            return this;
        }

        public Builder showProgress(boolean flag) {
            mViewHolder.progressNum.setVisibility(flag ? View.VISIBLE : View.GONE);
            return this;
        }

        public void setProgress(int precent) {
            mViewUpdateHandler.sendEmptyMessage(precent);
        }

        public Dialog create() {
            return mDialog;
        }

        public void show() {
            if (mDialog != null) {
                mDialog.show();
            }
        }

        public void dismiss() {
            if (mDialog != null) {
                mDialog.dismiss();
            }
        }

        private void initView() {
            mDialog = new Dialog(mContext, DialogUtils.getStyle());
            mView = LayoutInflater.from(mContext).inflate(R.layout.layout_loading_dialog, null);
            mViewHolder = new ViewHolder(mView);
            mDialog.setContentView(mView);

            DisplayMetrics dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
//            lp.width = (int) (dm.widthPixels * 0.3);
            lp.height = (int) (dm.widthPixels * 0.3);
            mDialog.getWindow().setAttributes(lp);


            mViewUpdateHandler.sendEmptyMessage(0);
        }


        class ViewHolder {

            TextView tvTitle;
            TextView progressNum;
            ProgressBar progressBar;
            LinearLayout linearLayout;

            public ViewHolder(View view) {
                tvTitle = (TextView) view.findViewById(R.id.dialog_title);
                progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
                progressNum = (TextView) view.findViewById(R.id.progress_num);
                linearLayout = (LinearLayout) view.findViewById(R.id.dialog_layout);
            }
        }

    }


}

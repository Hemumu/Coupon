package com.qyhl.coupon.utils;

import android.content.Context;

/**
 * Context 管理
 *
 * @author helin
 * @date 2018/05/09
 */
public class ContextUtilts {

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public static ContextUtilts getContextUtilts() {
        return contextUtilts;
    }

    public static void setContextUtilts(ContextUtilts contextUtilts) {
        ContextUtilts.contextUtilts = contextUtilts;
    }

    private Context mContext;

    private static ContextUtilts contextUtilts;

    private ContextUtilts() {
    }

    public static ContextUtilts getInstance() {
        if (contextUtilts == null) {
            contextUtilts = new ContextUtilts();
        }
        return contextUtilts;
    }

}

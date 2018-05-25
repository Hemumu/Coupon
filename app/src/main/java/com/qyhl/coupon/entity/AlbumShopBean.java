package com.qyhl.coupon.entity;

import java.util.List;

/**
 * @author helin
 */
public class AlbumShopBean {

    private int total;
    private boolean toEnd;
    private boolean isRelateEnd;
    private List<ShopDetailsBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isToEnd() {
        return toEnd;
    }

    public void setToEnd(boolean toEnd) {
        this.toEnd = toEnd;
    }

    public boolean isRelateEnd() {
        return isRelateEnd;
    }

    public void setRelateEnd(boolean relateEnd) {
        isRelateEnd = relateEnd;
    }

    public List<ShopDetailsBean> getData() {
        return data;
    }

    public void setData(List<ShopDetailsBean> data) {
        this.data = data;
    }
}

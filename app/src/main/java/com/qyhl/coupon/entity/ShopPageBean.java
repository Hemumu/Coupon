package com.qyhl.coupon.entity;

import java.util.List;

/**
 * 商品分页
 */
public class ShopPageBean {

    /**
     * 总数
     */
    private int total;

    private List<ShopDetailsBean> data;

    private String relate_brand;

    private boolean isRelateEnd;

    private boolean toEnd;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ShopDetailsBean> getData() {
        return data;
    }

    public void setData(List<ShopDetailsBean> data) {
        this.data = data;
    }

    public String getRelate_brand() {
        return relate_brand;
    }

    public void setRelate_brand(String relate_brand) {
        this.relate_brand = relate_brand;
    }

    public boolean isRelateEnd() {
        return isRelateEnd;
    }

    public void setRelateEnd(boolean relateEnd) {
        isRelateEnd = relateEnd;
    }

    public boolean isToEnd() {
        return toEnd;
    }

    public void setToEnd(boolean toEnd) {
        this.toEnd = toEnd;
    }
}

package com.qyhl.coupon.utils.cache;

/**
 * Created by Cookie on 2017/1/13.
 */

public interface ICache {
    void put(String key, Object value);

    Object get(String key);

    void remove(String key);

    boolean contains(String key);

    void clear();
}

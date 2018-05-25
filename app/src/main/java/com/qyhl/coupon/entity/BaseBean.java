package com.qyhl.coupon.entity;

import java.io.Serializable;

/**
 * Created by helin on 2017/2/7.
 */

public class BaseBean<T> implements Serializable {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private int code;
    private  T data;

    @Override
    public String toString() {
        return "BaseBean{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}

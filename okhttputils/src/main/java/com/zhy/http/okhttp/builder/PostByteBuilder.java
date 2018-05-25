package com.zhy.http.okhttp.builder;

import com.zhy.http.okhttp.request.PostByteRequest;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.MediaType;

/**
 * Created by qyhl on 2018/3/16.
 */

public class PostByteBuilder extends OkHttpRequestBuilder<PostByteBuilder> {
    private byte[] content;
    private MediaType mediaType;


    public PostByteBuilder content(byte[] content)
    {
        this.content = content;
        return this;
    }

    public PostByteBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostByteRequest(url, tag, params, headers, content, mediaType,id).build();
    }

}

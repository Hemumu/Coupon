package com.qyhl.coupon.entity;

import java.util.List;

/**
 *
 * 商品Tag
 *
 * @author helin
 */
public class ShopTagsBean {

    /**
     * name : 美食
     * sec_tags : [{"cat2":"休闲零食","url":"http://apiv2.sqyhq.cn/cat/美食/休闲零食@2x.png"},{"cat2":"茶水酒饮","url":"http://apiv2.sqyhq.cn/cat/美食/茶水酒饮@2x.png"},{"cat2":"粮油速食","url":"http://apiv2.sqyhq.cn/cat/美食/粮油速食@2x.png"},{"cat2":"水果生鲜","url":"http://apiv2.sqyhq.cn/cat/美食/水果生鲜@2x.png"},{"cat2":"营养保健","url":"http://apiv2.sqyhq.cn/cat/美食/营养保健@2x.png"}]
     */

    private String name;
    private List<SecTagsBean> sec_tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecTagsBean> getSec_tags() {
        return sec_tags;
    }

    public void setSec_tags(List<SecTagsBean> sec_tags) {
        this.sec_tags = sec_tags;
    }

    public static class SecTagsBean {
        /**
         * cat2 : 休闲零食
         * url : http://apiv2.sqyhq.cn/cat/美食/休闲零食@2x.png
         */

        private String cat2;
        private String url;

        public String getCat2() {
            return cat2;
        }

        public void setCat2(String cat2) {
            this.cat2 = cat2;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

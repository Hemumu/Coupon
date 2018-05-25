package com.qyhl.coupon.entity;

public class ShopDetailsBean {


    /**
     * id : 428692
     * title : 逛淘宝特卖频道
     * subtitle :
     * cover : http://47.92.153.245:8000/upload/img/20180507/155311746542.png
     * url : https://s.click.taobao.com/yq9jARw
     * show_at : 1525675071
     * deadline : 1527004799
     * item_id : 0
     * is_album : 0
     * is_tj : 0
     * is_tmall : 0
     * price_pre : 0
     * price_behind : 0
     * sex : 1
     * volume : 400
     * price_text : 登陆APP，逛就拿金币！ 剩8小时
     * zk : 0
     * is_expired : false
     * url_item :
     * url_quan :
     * item_url :
     * quan_url :
     */

    private int id;
    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subtitle;
    /**
     * 封面
     */
    private String cover;
    /**
     * 地址
     */
    private String url;
    private long show_at;
    private long deadline;
    /**
     * 商品Id
     */
    private long item_id;
    /**
     * 是否专场
     */
    private int is_album;
    /**
     * 是否推荐
     */
    private int is_tj;
    /**
     * 是否天猫
     */
    private int is_tmall;
    /**
     * 原价
     */
    private double price_pre;
    /**
     * 券后价格
     */
    private double price_behind;
    /**
     * 性别
     */
    private int sex;
    private int volume;
    /**
     * 价格文本
     */
    private String price_text;
    /**
     * 折扣
     */
    private double zk;
    /**
     * 是否过期
     */
    private boolean is_expired;
    /**
     * 商品连接
     */
    private String url_item;
    /**
     * 券连接
     */
    private String url_quan;
    private String item_url;
    private String quan_url;


    public long getShow_at() {
        return show_at;
    }

    public void setShow_at(long show_at) {
        this.show_at = show_at;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public double getPrice_pre() {
        return price_pre;
    }

    public void setPrice_pre(double price_pre) {
        this.price_pre = price_pre;
    }

    public double getPrice_behind() {
        return price_behind;
    }

    public void setPrice_behind(double price_behind) {
        this.price_behind = price_behind;
    }

    public double getZk() {
        return zk;
    }

    public void setZk(double zk) {
        this.zk = zk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




    public int getIs_album() {
        return is_album;
    }

    public void setIs_album(int is_album) {
        this.is_album = is_album;
    }

    public int getIs_tj() {
        return is_tj;
    }

    public void setIs_tj(int is_tj) {
        this.is_tj = is_tj;
    }

    public int getIs_tmall() {
        return is_tmall;
    }

    public void setIs_tmall(int is_tmall) {
        this.is_tmall = is_tmall;
    }


    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getPrice_text() {
        return price_text;
    }

    public void setPrice_text(String price_text) {
        this.price_text = price_text;
    }


    public boolean isIs_expired() {
        return is_expired;
    }

    public void setIs_expired(boolean is_expired) {
        this.is_expired = is_expired;
    }

    public String getUrl_item() {
        return url_item;
    }

    public void setUrl_item(String url_item) {
        this.url_item = url_item;
    }

    public String getUrl_quan() {
        return url_quan;
    }

    public void setUrl_quan(String url_quan) {
        this.url_quan = url_quan;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public String getQuan_url() {
        return quan_url;
    }

    public void setQuan_url(String quan_url) {
        this.quan_url = quan_url;
    }
}

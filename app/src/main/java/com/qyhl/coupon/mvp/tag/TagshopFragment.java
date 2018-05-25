package com.qyhl.coupon.mvp.tag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qyhl.coupon.R;
import com.qyhl.coupon.base.BaseFragment;
import com.qyhl.coupon.entity.ShopDetailsBean;
import com.qyhl.coupon.entity.ShopTagsBean;
import com.qyhl.coupon.mvp.shop.ShopDetailsActivity;
import com.qyhl.coupon.utils.decoration.GridSpacingItemDecoration;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 专场
 * @author helin
 */
public class TagshopFragment extends BaseFragment implements TagShopContract.TagShopView {
    /**
     * 数据列表
     *
     */
    @BindView(R.id.album_recyclerview)
    RecyclerView mAlbmRecyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;


    Unbinder unbinder;
    /**
     * 数据是否第一次加载
     */
    private boolean mIsInited;
    /**
     * 布局界面初始化
     */
    private boolean mIsPrepared;


    /**
     * 控制器
     */
    private TagShopPresenter mPresenter;
    private List<ShopDetailsBean> mData = new ArrayList<>();
    /**
     * 适配器
     */
    private CommonAdapter<ShopDetailsBean> mShopAdatapter;
    /**
     * 子菜单数据
     */
    private List<ShopTagsBean.SecTagsBean> mSceTags;
    /**
     * 类型 1 推荐 2 销量 3 价格 4
     */
    private String mType;
    /**
     * 标签
     */
    private String mTag;

    /**
     * 二级Tag
     */
    private String mSecTag;
    /**
     * 分页
     */
    private int mPage = 1;


    public void setmType(String mType) {
        this.mType = mType;
    }

    public void setmTag(String mTag) {
        this.mTag = mTag;
    }

    public void setmSecTag(String mSecTag) {
        this.mSecTag = mSecTag;
    }

    public void setmShopTagsBean() {

    }

    public TagshopFragment() {
    }

    public static TagshopFragment newInstance(String tag,String secTag,String  type) {
        TagshopFragment fragment = new TagshopFragment();
        fragment.setmSecTag(secTag);
        fragment.setmTag(tag);
        fragment.setmType(type);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tags_shop, container, false);
        mIsPrepared = true;
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new TagShopPresenter(this);
        initView();
        lazyLoad();
        return view;
    }

    /**
     * 初始化
     */
    private void initView() {
        //配置刷新
        mRefresh.setEnableLoadmore(true);
        mRefresh.setRefreshHeader(new MaterialHeader(getContext()));
        mRefresh.setRefreshFooter(new ClassicsFooter(getContext()));
        mShopAdatapter = new CommonAdapter<ShopDetailsBean>(this.getActivity(),R.layout.item_sec_shop,mData) {
            @Override
            protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, ShopDetailsBean shopDetailsBean, int position) {
                holder.setText(R.id.shop_name,shopDetailsBean.getTitle());
                holder.setText(R.id.yuan_pre,"原价 ￥"+shopDetailsBean.getPrice_pre());
                holder.setText(R.id.zk_pre,"券后 ￥"+shopDetailsBean.getPrice_behind());
                ImageView cover =  holder.getView(R.id.shop_cover);
                Glide.with(mContext)
                        .load(shopDetailsBean.getCover())
                        .apply(new RequestOptions().error(R.drawable.icon_loading_error).placeholder(R.drawable.loading_ico))
                        .into(cover);

            }
        };

        mAlbmRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        mAlbmRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2,10,true));
        mAlbmRecyclerView.setAdapter( mShopAdatapter);
        mShopAdatapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                String url =  mData.get(position).getUrl_item();
                Bundle bundle = new Bundle();
                bundle.putString("url",url);
                Intent intent = new Intent(getContext(), ShopDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        //下拉
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mPresenter.getAlbumShop(mTag,mSecTag,mType,mPage,true);
            }
        });

        //上拉
        mRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getAlbumShop(mTag,mSecTag,mType,mPage,false);
            }
        });


    }


    /**
     * 加载数据
     */
    private void loadData() {
        mIsInited = true;
        showSimpleLoading();
        mPresenter.getAlbumShop(mTag,mSecTag,mType,mPage,true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    /**
     * 懒加载数据
     */
    public void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsInited) {
            //异步初始化，在初始化后显示正常UI
            loadData();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onError() {
        showTast("获取失败");
        dismissLoading();
        mRefresh.finishLoadmore();
        mRefresh.finishRefresh();
    }

    @Override
    public void onSuccess(List<ShopDetailsBean> data,boolean isTop) {
        dismissLoading();
        mRefresh.finishLoadmore();
        mRefresh.finishRefresh();
        if( isTop ){
            mData.clear();
        }
        mData.addAll(data);
        mShopAdatapter.notifyDataSetChanged();

    }
}

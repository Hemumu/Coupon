package com.qyhl.coupon.mvp.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.qyhl.coupon.mvp.tag.TagDetailsActivity;
import com.qyhl.coupon.utils.decoration.RecycleViewDivider;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 专场
 * @author helin
 */
public class AlbumFragment extends BaseFragment implements AlbumContract.AlbumView {
    /**
     * 数据列表
     *
     */
    @BindView(R.id.album_recyclerview)
    RecyclerView mAlbmRecyclerView;
    /**
     * 下拉刷新
     */
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


    private ShopTagsBean mShopTagsBean;
    /**
     * 控制器
     */
    private AlbumPresenter mPresenter;
    private List<ShopDetailsBean> mData = new ArrayList<>();
    /**
     * 适配器
     */
    private CommonAdapter<ShopDetailsBean> mShopAdatapter;
    /**
     * 子菜单
     */
    private RecyclerView mItemRecyclerView;

    /**
     * header
     */
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    /**
     * 子菜单适配器
     */
    private CommonAdapter<ShopTagsBean.SecTagsBean> mItemAdatapter;
    /**
     * 子菜单数据
     */
    private List<ShopTagsBean.SecTagsBean> mSceTags;

    /**
     * 页数
     */
    private int mPage = 1;


    public void setmShopTagsBean(ShopTagsBean mShopTagsBean) {
        this.mShopTagsBean = mShopTagsBean;
        mSceTags=  mShopTagsBean.getSec_tags();
    }

    public AlbumFragment() {
    }

    public static AlbumFragment newInstance(ShopTagsBean shopTagsBean) {
        AlbumFragment fragment = new AlbumFragment();
        fragment.setmShopTagsBean(shopTagsBean);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        mIsPrepared = true;
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new AlbumPresenter(this);
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

        View headView = LayoutInflater.from(getContext()).inflate(R.layout.album_header, null);
        mItemRecyclerView = (RecyclerView)headView.findViewById(R.id.group_item_view);
        mItemRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),4));
        mItemRecyclerView.setNestedScrollingEnabled(false);
        mItemAdatapter = new CommonAdapter<ShopTagsBean.SecTagsBean>(this.getActivity(),R.layout.item_album_section_,mSceTags) {
            @Override
            protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, ShopTagsBean.SecTagsBean tag, int position) {
                holder.setText(R.id.item_text,tag.getCat2());
                ImageView cover =  holder.getView(R.id.item_cover);
                Glide.with(mContext)
                        .load(tag.getUrl())
                        .apply(new RequestOptions().error(R.drawable.icon_loading_error).placeholder(R.drawable.loading_ico))
                        .into(cover);

            }
        };

        mItemAdatapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                String tag =  mSceTags.get(position).getCat2();
                String name = mShopTagsBean.getName();
                Bundle bundle = new Bundle();
                bundle.putString("tag",name);
                bundle.putString("secTag",tag);
                Intent intent =  new Intent( getContext(),TagDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


        mItemRecyclerView.setAdapter(mItemAdatapter);
        mShopAdatapter = new CommonAdapter<ShopDetailsBean>(this.getActivity(),R.layout.item_album_shop,mData) {
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

        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mShopAdatapter);
        mHeaderAndFooterWrapper.addHeaderView(headView);

        mAlbmRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAlbmRecyclerView.addItemDecoration(new RecycleViewDivider(
                getContext(), LinearLayoutManager.HORIZONTAL, 2, ContextCompat.getColor(getContext(), R.color.global_background)));
        mAlbmRecyclerView.setAdapter( mHeaderAndFooterWrapper);

        //上拉刷新
        mRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getAlbumShop(mShopTagsBean.getName(),false,mPage);
            }
        });
        //下拉刷新
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage=1;
                mPresenter.getAlbumShop(mShopTagsBean.getName(),true,mPage);
            }
        });

        mShopAdatapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ShopDetailsBean shop = mData.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("url",shop.getItem_url());
                Intent intent = new Intent(getContext(), ShopDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


    }


    /**
     * 加载数据
     */
    private void loadData() {
        mIsInited = true;
        showSimpleLoading();
        mPresenter.getAlbumShop(mShopTagsBean.getName(),true,mPage);
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
        mRefresh.finishRefresh();
        mRefresh.finishLoadmore();
    }

    @Override
    public void onSuccess(List<ShopDetailsBean> data,boolean isTop) {
        dismissLoading();
        mRefresh.finishRefresh();
        mRefresh.finishLoadmore();
        if(isTop){
            mData.clear();
            mData.addAll(data);
        }else{
            mData.addAll(data);
        }
        mHeaderAndFooterWrapper.notifyDataSetChanged();

    }
}

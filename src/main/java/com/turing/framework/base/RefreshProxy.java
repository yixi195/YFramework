package com.turing.framework.base;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.turing.framework.R;
import com.turing.framework.model.PageList;
import com.turing.framework.rx.BaseObserver;
import com.turing.framework.view.viewdata.BaseListView;

/**
 * Swipe下拉风格刷新代理
 * Created by huang on 2017/5/2.
 */

public class RefreshProxy {

    private int currentPage = 1;
    private int totalPage;

    private Context mContext;
    private BaseListView mListView;
    private LuRecyclerView recyclerView;
    private LuRecyclerViewAdapter mLuAdapter;
    public boolean isDownRefresh; //是否属于下拉刷新
    private boolean isReload = true; //是否需要重新加载
    private View no_data_view;

    public RefreshProxy(Context context, BaseListView view) {
        this.mContext = context;
        this.mListView = view;
        initView();
    }

    private void initView(){
        if (mListView.getAdapter() == null)
            throw new IllegalArgumentException("Constructor parameters : Adapter cannot be null !!!");

        if (recyclerView == null)
            throw new IllegalArgumentException("Constructor parameters : getRecycleView cannot be null !!!");

        if (!(mListView.getRecycleView() instanceof LuRecyclerView))
            throw new IllegalArgumentException("Constructor parameters : RecycleView should instanceof LuRecyclerView !!!");


//        if (mListView.getSwipe() == null)
//            throw new IllegalArgumentException("Constructor parameters :Swipe cannot be null!!!");

        mLuAdapter = new LuRecyclerViewAdapter(mListView.getAdapter()) ;
        if (mListView.onItemClickListener() != null) {
            mLuAdapter.setOnItemClickListener(mListView.onItemClickListener());
        }

        recyclerView = (LuRecyclerView) mListView.getRecycleView();
//        if (mListView.emptyView() == null) {
//            no_data_view = new DefaultNoDataView(mContext);
//        } else {
//            no_data_view = mListView.emptyView();
//        }

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mLuAdapter);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white);
        recyclerView.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");
        recyclerView.setOnLoadMoreListener(onLoadMore);

//        mListView.getSwipe().setProgressViewOffset(false, 0, DisplayUtil.dip2px(mContext,Constants.DEFAULT_REFRESH_HEIGHT));
//        mListView.getSwipe().setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
//        mListView.getSwipe().setOnRefreshListener(onRefresh);
//        mListView.getSwipe().setRefreshing(true);
        // 默认自动加载数据
        onRefresh.onRefresh();
        recyclerView.setRefreshing(true);
    }

    private SwipeRefreshLayout.OnRefreshListener onRefresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            currentPage = 1;
            mListView.getAdapter().clear();
//            mListView.getSwipe().setRefreshing(true);
            recyclerView.setRefreshing(true);
            isDownRefresh = true;
            fetchData();
        }
    };

    private OnLoadMoreListener onLoadMore = new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            if (currentPage < totalPage) {
                currentPage ++;
                fetchData();
            } else {
                recyclerView.setNoMore(true);
            }
        }
    };

    /**
     * 加载数据
     */
    public void fetchData(){
        mListView.loadData(currentPage,new BaseObserver<PageList>() {
            @Override
            public void OnFail(int code, String err) {
//                //TODO 请求失败的处理
//                ToastManager.getInstance(mContext).showToast(err);
//                //如果请求有错误，则会调用缓存的，所以这里也要判断下是否 有数据
//                if (mListView.getAdapter().getDataList() != null && mListView.getAdapter().getDataList().size() > 0) {
//                    no_data_view.setVisibility(View.GONE);
//                } else no_data_view.setVisibility(View.VISIBLE);
//                mListView.getSwipe().setRefreshing(false);
//
//                isDownRefresh = false;
//
//                //如果是主界面HomeFragment登录过期
//                if (code == 3 || err.contains("请登录后")){
//                    BroadcastUtils.sendLocalBroadcast(new Intent(Constants.REFRESH_NOTICAITON));
//                }
            }

            @Override
            public void OnSuccess(PageList pageList) {
//                mListView.getSwipe().setRefreshing(false);
//                mListView.getLuRecycleView().refreshComplete(Constants.DEFAULT_PAGE_SIZE);
//                mListView.getAdapter().notifyDataSetChanged();
//                totalPage = pageList.getPageSize();
//                if (pageList.getRows() == null || pageList.getRows().size() == 0) {
////                    LinearLayout.LayoutParams vl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
////                    if (mContext instanceof BaseActivity){
////                        ViewGroup parent = (ViewGroup) no_data_view.getParent();
////                        if (parent != null) {
////                            parent.removeView(no_data_view);
////                        }
////                        ((BaseActivity)mContext).addContentView(no_data_view,vl);
////                    }
//                    no_data_view.setVisibility(View.VISIBLE);
//                } else {
//                    mListView.getAdapter().addAll(pageList.getRows());
//                    no_data_view.setVisibility(View.GONE);
//                }
//                isDownRefresh = false;
            }
        });
    }

}

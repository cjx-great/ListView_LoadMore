package com.index.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.index.R;

/**
 * Created by CJX on 2016/7/24.
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener{

    //底部布局
    private View footer = null;

    private int totalItemCount;
    private int lastVisibleItem;

    //标记是否正在加载
    private boolean isLoading = false;

    public LoadListView(Context context) {
        this(context,null);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化时添加foot布局
     * */
    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer,null);
        //只有在加载数据时才会显示(监听到最低端)
        footer.findViewById(R.id.load_layout).setVisibility(GONE);
        addFooterView(footer);
        //滚动监听
        setOnScrollListener(this);
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        //如果滚动停止并且当前可见的最低端Item为最后一个Item
        if (scrollState == SCROLL_STATE_IDLE && (lastVisibleItem == totalItemCount)){
            if (!isLoading){
                isLoading = true;

                //加载更多数据
                footer.findViewById(R.id.load_layout).setVisibility(VISIBLE);

                //接口回调实现更多数据加载
                if (onLoadListener != null){
                    onLoadListener.onLoad();
                }

            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisiableItem, int visibleItemCount,
                         int totalItemCount) {
        lastVisibleItem = firstVisiableItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    /**
     * 数据加载完毕
     * */
    public void loadCompleted(){
        isLoading = false;
        footer.findViewById(R.id.load_layout).setVisibility(GONE);
    }

    /**
     * 加载数据的回调接口，供Activity使用
     * */
    public interface OnLoadListener{
        void onLoad();
    }

    private OnLoadListener onLoadListener = null;

    public void setOnLoadListener(OnLoadListener onLoadListener){
        this.onLoadListener = onLoadListener;
    }

}

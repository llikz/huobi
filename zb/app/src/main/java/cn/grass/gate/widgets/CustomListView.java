package cn.grass.gate.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import cn.grass.gate.R;

/**
 * Created by weedys on 16/8/12.
 */
public class CustomListView extends ListView{
    public CustomListView(Context context) {
        super(context);
        initFooterView();
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFooterView();
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFooterView();
    }

//    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    View footView;
    private void initFooterView(){
        footView = LayoutInflater.from(getContext()).inflate(R.layout.item_select,null);

        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
//                if (lastItem == adapter.count && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
//                    getAdapter().count += 10;
//                    getAdapter().notifyDataSetChanged();
//                 }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                        int visibleItemCount, int totalItemCount) {
                int lastItem = firstVisibleItem + visibleItemCount - 1 ;
                if(getAdapter()!=null && lastItem==getAdapter().getCount()){
                    if(listener!=null){
                        listener.onLoadMore();
                    }
                    addFootView();
                }
            }
        });
     }
    private void addFootView(){
        if(getFooterViewsCount()<=0){
            addFooterView(footView);
        }
    }
    public void setLoadMoreFinish(){
        if(getFooterViewsCount()>=1){
            removeFooterView(footView);
        }
    }

    LoadMoreListener listener;
    public void setOnLoadMore(LoadMoreListener l){
        listener = l;
    }

//    public void onScroll(AbsListView view, int firstVisibleItem,
//                        int visibleItemCount, int totalItemCount) {
//        lastItem = firstVisibleItem + visibleItemCount - 1 ;
//        // TODO Auto-generated method stub
//    }
//    public void onScrollStateChanged(AbsListView view,
//                                     int scrollState) {
//        if (lastItem == adapter.count && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
//            adapter.count += 10;
//            adapter.notifyDataSetChanged();
//        }
//    }

    public interface LoadMoreListener{
        public void onLoadMore();
    }
}

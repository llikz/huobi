package cn.grass.gate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.adapters.ExerciseNotebookAdapter;
import cn.grass.gate.base.BaseFragment;
import cn.grass.gate.beans.BaseInfo;
import cn.grass.gate.inface.GItemClickListener;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.widgets.CustomRecyclerView;

/**
 * Created by linxc on 2017/7/24.
 */
public class ExerciseNotebookFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.fragment_exercise_notebook,null);
        initView(contentView);
        return contentView;
    }


    PullRefreshLayout pullRefreshLayout;
    CustomRecyclerView lv;
    View errorView = null;
    ExerciseNotebookAdapter adapter = null;
    ArrayList<BaseInfo> tList =null;
    private void initView(View v) {
        errorView = v.findViewById(R.id.view_error);
        pullRefreshLayout = (PullRefreshLayout) v.findViewById(R.id.pull_layout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO: 2017/7/14  刷新
            }
        });

        lv = (CustomRecyclerView) v.findViewById(R.id.list_view);
        lv.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv.setLayoutManager(linearLayoutManager);

        tList = new ArrayList<>();
        adapter = new ExerciseNotebookAdapter(getActivity(), tList);
        lv.setAdapter(adapter);
        lv.setAutoLoadMoreEnable(true);
        lv.setLoadMoreListener(new CustomRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // TODO: 2017/7/14 加载更多

            }
        });
        adapter.setItemClickListener(new GItemClickListener() {
            @Override
            public void onItemClicked(View vs, int pos) {
                ToastUtil.show(pos+"");
            }
        });
        initData();
    }


    private void initData() {
        for (int i = 0;i<10;i++){
            BaseInfo info = new BaseInfo();
            info.name= "机关内勤手册"+i;
            tList.add(info);
        }
        adapter.notifyDataSetChanged();
    }
}

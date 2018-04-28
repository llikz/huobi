package cn.grass.gate.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.adapters.JobSpecificationAdapter;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.beans.BaseInfo;
import cn.grass.gate.inface.GItemClickListener;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.widgets.CustomRecyclerView;
import cn.grass.gate.widgets.TopView;

/**
 * Created by linxc on 2017/7/24.
 * 工作规范
 */
public class JobSpecificationActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_specification);

        initView();
    }

    PullRefreshLayout pullRefreshLayout;
    CustomRecyclerView lv;
    View errorView = null;
    JobSpecificationAdapter adapter = null;
    ArrayList<BaseInfo> jList =null;
    private void initView() {
        ((TopView)findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });
        errorView = findViewById(R.id.view_error);
        pullRefreshLayout = (PullRefreshLayout) findViewById(R.id.pull_layout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO: 2017/7/14  刷新
            }
        });

        lv = (CustomRecyclerView) findViewById(R.id.list_view);
        lv.setItemAnimator(new DefaultItemAnimator());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        lv.setLayoutManager(layoutManager);

        jList = new ArrayList<>();
        adapter = new JobSpecificationAdapter(this, jList);
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
            info.name= "行政规范"+i;
            jList.add(info);
        }
        adapter.notifyDataSetChanged();
    }

    public static void startJobSpecificationActivity(Context c) {
        Intent it = new Intent(c, JobSpecificationActivity.class);
        c.startActivity(it);
    }

}

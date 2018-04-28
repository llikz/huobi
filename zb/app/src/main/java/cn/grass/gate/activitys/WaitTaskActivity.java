package cn.grass.gate.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.adapters.TaskAdapter;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.beans.WaitTask;
import cn.grass.gate.inface.GItemClickListener;
import cn.grass.gate.presenters.TaskPresenter;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.views.TaskView;
import cn.grass.gate.widgets.CustomRecyclerView;
import cn.grass.gate.widgets.TopView;

/**
 * 代办任务列表
 */
public class WaitTaskActivity extends BaseActivity implements View.OnClickListener, TaskView {
    private ArrayList<WaitTask.DataBean.ListBean> tasks = new ArrayList<>();
    private WaitTaskActivity activity;
    private PullRefreshLayout pullRefreshLayout;
    private CustomRecyclerView lv;
    private View errorView = null;
    private EditText editEt = null;
    private TaskAdapter adapter = null;
    private TaskPresenter taskPresenter = null;
    private int pageIndex = 1;
    private int pageSize=10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_task);
        //禁止系统软键盘主动弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        activity = this;
        initView();
        initData();
    }

    private void initData() {
//        for (int i = 0; i < 5; i++) {
//            Task task = new Task();
//            task.setAreaName("台区名称AAA");
//            task.setMeterNum(30);
//            task.setTaskTime("时间是20170728 11:21");
//            tasks.add(task);
//        }
        showProgressDialog("正在加载");
        taskPresenter.getTaskList(pageIndex,pageSize);
    }

    private void initView() {
        taskPresenter = new TaskPresenter(this);
        ((TopView)findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });
        editEt = (EditText)findViewById(R.id.query_et);
        editEt.setOnClickListener(this);
        errorView = findViewById(R.id.view_error);
        pullRefreshLayout = (PullRefreshLayout) findViewById(R.id.pull_layout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex=1;
                taskPresenter.getTaskList(pageIndex,pageSize);
            }
        });

        lv = (CustomRecyclerView)findViewById(R.id.list_view);
        lv.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv.setLayoutManager(linearLayoutManager);

        adapter = new TaskAdapter(activity, tasks);
        lv.setAdapter(adapter);
        lv.setAutoLoadMoreEnable(true);
        lv.setLoadMoreListener(new CustomRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                showProgressDialog("正在加载更多...");
                taskPresenter.getTaskList(pageIndex,pageSize);
            }
        });
        adapter.setItemClickListener(new GItemClickListener() {
            @Override
            public void onItemClicked(View vs, int pos) {
                TaiquListActivity.startTaiquListActivity(activity,tasks.get(pos).getTaiQuId());
            }
        });
    }


    public static void startWaitTaskActivity(Context c) {
        Intent it = new Intent(c, WaitTaskActivity.class);
        c.startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query_et:
                SearchWaitTaskActivity.startSearchWaitTaskActivity(WaitTaskActivity.this,SearchWaitTaskActivity.TYPE_TASK);
                break;
        }
    }

    @Override
    public void taskList(WaitTask taskInfo) {

        if(pullRefreshLayout!=null){
            pullRefreshLayout.setRefreshing(false);
            pullRefreshLayout.computeScroll();
        }
        if(taskInfo!=null){
            if(pageIndex==1){
                tasks.clear();
            }
            tasks.addAll(taskInfo.getData().getList());
            pageIndex++;
            //如果加载分页数据等于pageSize,证明可以继续加载,如果小于pageSize证明加载完了
            if (taskInfo.getData().getList().size() == pageSize) {
                lv.setLoadingMoreEnable(true);
            } else {
                lv.setLoadingMoreEnable(false);
            }
            showErrorView(errorView,lv,0);
            adapter.notifyDataSetChanged();
        }else{

            if(pageIndex==1){
                tasks.clear();
                adapter.notifyDataSetChanged();
                showErrorView(errorView,lv,1,"暂无任务喔~");
            }else {
                pageIndex = pageIndex - 1;
            }
            lv.setLoadingMoreEnable(false);
        }
        hiddenDialog();
    }

    @Override
    public void onSuccess(int callId, String body, String msg) {

    }

    @Override
    public void onFail(int callId, String msg, int errId) {
        hiddenDialog();
        ToastUtil.show(msg);
    }
}

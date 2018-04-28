package cn.grass.gate.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.adapters.TaskAdapter;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.beans.WaitTask;
import cn.grass.gate.inface.GItemClickListener;
import cn.grass.gate.presenters.TaskPresenter;
import cn.grass.gate.utils.OptionDialogHelper;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.views.TaskView;
import cn.grass.gate.widgets.CustomRecyclerView;

/**
 * 代办任务列表
 */
public class SearchWaitTaskActivity extends BaseActivity implements View.OnClickListener, TaskView {
    private ArrayList<WaitTask.DataBean.ListBean> tasks = new ArrayList<>();
    private SearchWaitTaskActivity activity;
    private PullRefreshLayout pullRefreshLayout;
    private CustomRecyclerView lv;
    private View errorView = null;
    private TaskAdapter adapter = null;
    private TaskPresenter taskPresenter = null;
    private int pageIndex = 1;
    private int pageSize=10;
    private int type;
    private ImageView backIv;
    private EditText queryEt;
    private String searchStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_task_search);
        activity = this;
        initView();
//        initData();
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
        Intent intent = getIntent();
        if(null != intent){
            type = intent.getIntExtra(KEY_TYPE,0);
        }
        backIv = (ImageView) findViewById(R.id.back_iv);
        backIv.setOnClickListener(this);
        queryEt = (EditText) findViewById(R.id.query_et);
        queryEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //软键盘搜索键的监听
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    OptionDialogHelper.closeKeyboard(SearchWaitTaskActivity.this);
                    showProgressDialog("正在加载");
                    toSearch();
                    return true;
                }
                return false;
            }
        });
        taskPresenter = new TaskPresenter(this);
        errorView = findViewById(R.id.view_error);
        pullRefreshLayout = (PullRefreshLayout) findViewById(R.id.pull_layout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex=1;
                taskPresenter.getLikeTaskList(pageIndex,pageSize, searchStr);
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
                taskPresenter.getLikeTaskList(pageIndex,pageSize, searchStr);
            }
        });
        adapter.setItemClickListener(new GItemClickListener() {
            @Override
            public void onItemClicked(View vs, int pos) {
                TaiquListActivity.startTaiquListActivity(activity,tasks.get(pos).getTaiQuId());
            }
        });
    }


    public static String KEY_TYPE = "key_type";
    public static final int TYPE_TASK = 0x01;
    public static void startSearchWaitTaskActivity(Context c, int type) {
        Intent intent = new Intent(c, SearchWaitTaskActivity.class);
        intent.putExtra(KEY_TYPE, type);
        c.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
        }
    }
    private void toSearch() {
        String content = queryEt.getText().toString();
        switch (type) {
            case TYPE_TASK:
//                SearchTaskResultActivity.startSearchTaskResultActivity(this, content);
                searchStr = content;
                pageIndex=1;
                taskPresenter.getLikeTaskList(pageIndex, pageSize, searchStr);
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

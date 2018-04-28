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
import cn.grass.gate.adapters.TaiquAdapter;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.beans.Taiqu;
import cn.grass.gate.inface.GItemClickListener;
import cn.grass.gate.presenters.TaiquPresenter;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.views.TaiquView;
import cn.grass.gate.widgets.CustomRecyclerView;
import cn.grass.gate.widgets.TopView;

/**
 * 台区列表
 */
public class TaiquListActivity extends BaseActivity implements View.OnClickListener, TaiquView {
    private ArrayList<Taiqu.DataBean.ListBean> taiqus = new ArrayList<>();
    private TaiquListActivity activity;
    private PullRefreshLayout pullRefreshLayout;
    private CustomRecyclerView lv;
    private View errorView = null;
    private TaiquAdapter adapter = null;
    private TaiquPresenter taiquPresenter = null;
    private int pageIndex = 1;
    private int pageSize=10;
    private String tId;//台区ID
    private EditText editEt = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_list);
        //禁止系统软键盘主动弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        activity = this;
        initView();
        initData();
    }

    private void initData() {
//        for (int i = 0; i < 5; i++) {
//            Area area = new Area();
//            area.setAreaName("天#45");
//            area.setUserName("张大伟");
//            area.setUserNo("123456789");
//            area.setUserPhone("13456561000");
//            area.setAssetNo("987654321");
//            area.setUserAddr("惠城区老人人乐后面2号楼");
//            area.setTime("2017-07-28 11:21");
//            area.setWorkType(1);
//            area.setUserId("1");
//            areas.add(area);
//        }
        showProgressDialog("正在加载");
        taiquPresenter.getTaiquList(tId,pageIndex,pageSize);
    }

    private void initView() {
        Intent intent  = getIntent();
        if(null != intent){
            tId = intent.getStringExtra("area_id");
        }
         taiquPresenter = new TaiquPresenter(this);
        ((TopView)findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });
        editEt = (EditText)findViewById(R.id.query_et);
        editEt.setOnClickListener(this);
        editEt.setHint(getString(R.string.taiqu_search_hint));
        errorView = findViewById(R.id.view_error);
        pullRefreshLayout = (PullRefreshLayout) findViewById(R.id.pull_layout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex=1;
                taiquPresenter.getTaiquList(tId,pageIndex,pageSize);
            }
        });

        lv = (CustomRecyclerView)findViewById(R.id.list_view);
        lv.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv.setLayoutManager(linearLayoutManager);

        adapter = new TaiquAdapter(activity, taiqus);
        lv.setAdapter(adapter);
        lv.setAutoLoadMoreEnable(true);
        lv.setLoadMoreListener(new CustomRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                showProgressDialog("正在加载更多...");
                taiquPresenter.getTaiquList(tId,pageIndex,pageSize);
            }
        });
        adapter.setItemClickListener(new GItemClickListener() {
            @Override
            public void onItemClicked(View vs, int pos) {
//                AreaDetailActivity.startAreaDetailActivity(activity,taiqus.get(pos));
            }
        });
    }

    public static void startTaiquListActivity(Context c,String id) {
        Intent it = new Intent(c, TaiquListActivity.class);
        it.putExtra("area_id",id);//存放台区ID
        c.startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query_et:
                SearchTaiquActivity.startSearchTaiquActivity(TaiquListActivity.this,SearchTaiquActivity.TYPE_TAIQU,"");
                break;
        }
    }

    @Override
    public void taiquList(Taiqu taiquInfo) {
        if(pullRefreshLayout!=null){
            pullRefreshLayout.setRefreshing(false);
            pullRefreshLayout.computeScroll();
        }
        if(taiquInfo!=null){
            if(pageIndex==1){
                taiqus.clear();
            }
            taiqus.addAll(taiquInfo.getData().getList());
            pageIndex++;
            if (taiquInfo.getData().getList().size() == pageSize) {
                lv.setLoadingMoreEnable(true);
            } else {
                lv.setLoadingMoreEnable(false);
            }
            showErrorView(errorView,lv,0);
            adapter.notifyDataSetChanged();
        }else{

            if(pageIndex==1){
                taiqus.clear();
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

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
import cn.grass.gate.adapters.TaiquAdapter;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.beans.Taiqu;
import cn.grass.gate.inface.GItemClickListener;
import cn.grass.gate.presenters.TaiquPresenter;
import cn.grass.gate.utils.OptionDialogHelper;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.views.TaiquView;
import cn.grass.gate.widgets.CustomRecyclerView;

/**
 * 台区列表Search
 */
public class SearchTaiquActivity extends BaseActivity implements View.OnClickListener, TaiquView {
    private ArrayList<Taiqu.DataBean.ListBean> taiqus = new ArrayList<>();
    private SearchTaiquActivity activity;
    private PullRefreshLayout pullRefreshLayout;
    private CustomRecyclerView lv;
    private View errorView = null;
    private TaiquAdapter adapter = null;
    private TaiquPresenter taiquPresenter = null;
    private int pageIndex = 1;
    private int pageSize=10;
    private int type;
    private String tId;
    private ImageView backIv;
    private EditText queryEt;
    private String searchStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taiqu_list_search);
        activity = this;
        initView();
//        initData();
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
        taiquPresenter.getLikeTaiquList(tId,pageIndex,pageSize,searchStr);
    }

    private void initView() {
        Intent intent  = getIntent();
        if(null != intent){
            type = intent.getIntExtra(KEY_TYPE,0);
            tId = intent.getStringExtra(KEY_ID);
        }
        taiquPresenter = new TaiquPresenter(this);
        backIv = (ImageView) findViewById(R.id.back_iv);
        backIv.setOnClickListener(this);
        queryEt = (EditText) findViewById(R.id.query_et);
        queryEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //软键盘搜索键的监听
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    OptionDialogHelper.closeKeyboard(SearchTaiquActivity.this);
                    showProgressDialog("正在加载");
                    toSearch();
                    return true;
                }
                return false;
            }
        });
        errorView = findViewById(R.id.view_error);
        pullRefreshLayout = (PullRefreshLayout) findViewById(R.id.pull_layout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex=1;
                taiquPresenter.getLikeTaiquList(tId,pageIndex,pageSize,searchStr);
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
                taiquPresenter.getLikeTaiquList(tId,pageIndex,pageSize,searchStr);
            }
        });
        adapter.setItemClickListener(new GItemClickListener() {
            @Override
            public void onItemClicked(View vs, int pos) {
                ToastUtil.show(pos+"");
                // TODO: 2017/7/14
//                AreaDetailActivity.startAreaDetailActivity(activity,taiqus.get(pos).getId());
            }
        });
    }

    public static String KEY_TYPE = "key_type";
    public static String KEY_ID = "taiqu_id";
    public static final int TYPE_TAIQU = 0x01;
    public static void startSearchTaiquActivity(Context c,int type,String tId) {
        Intent it = new Intent(c, SearchTaiquActivity.class);
        it.putExtra(KEY_TYPE,type);
        it.putExtra(KEY_ID,tId);
        c.startActivity(it);
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
            case TYPE_TAIQU:
                searchStr = content;
                pageIndex=1;
                taiquPresenter.getLikeTaiquList(tId,pageIndex, pageSize, searchStr);
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
            //如果加载分页数据等于pageSize,证明可以继续加载,如果小于pageSize证明加载完了
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
                showErrorView(errorView,lv,1,"暂无数据喔~");
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

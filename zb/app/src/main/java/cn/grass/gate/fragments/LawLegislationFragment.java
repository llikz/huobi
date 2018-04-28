package cn.grass.gate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;

import cn.grass.gate.IntentParameter;
import cn.grass.gate.R;
import cn.grass.gate.activitys.ArticleDetailActivity;
import cn.grass.gate.adapters.LawLegislationAdapter;
import cn.grass.gate.base.BaseFragment;
import cn.grass.gate.beans.TaxMessageInfo;
import cn.grass.gate.inface.GItemClickListener;
import cn.grass.gate.presenters.ArticlePresenter;
import cn.grass.gate.views.ArticleView;
import cn.grass.gate.widgets.CustomRecyclerView;

/**
 * Created by linxc on 2017/7/14.
 * 法律法规、学术论文
 */
public class LawLegislationFragment extends BaseFragment implements ArticleView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.fragment_law_legislation,null);
        initView(contentView);
        return contentView;
    }

    PullRefreshLayout pullRefreshLayout;
    CustomRecyclerView lv;
    View errorView = null;
    LawLegislationAdapter adapter = null;
    ArrayList<TaxMessageInfo> tList =null;
    String column = "";
    ArticlePresenter presenter =null;
    private int page = 1;
    private int pageSize = 8;
    EditText searchEt;
    private void initView(View v) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            column = bundle.getString(IntentParameter.COLUMN_ID,IntentParameter.ID_TAX_DYNAMIC);
        }

        errorView = v.findViewById(R.id.view_error);
        pullRefreshLayout = (PullRefreshLayout) v.findViewById(R.id.pull_layout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
//                presenter.getArticleList(column,page,pageSize);
            }
        });

        lv = (CustomRecyclerView) v.findViewById(R.id.list_view);
        lv.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv.setLayoutManager(linearLayoutManager);

        tList = new ArrayList<>();
        adapter = new LawLegislationAdapter(getActivity(), tList);
        lv.setAdapter(adapter);
        lv.setAutoLoadMoreEnable(true);
        lv.setLoadMoreListener(new CustomRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
//                presenter.getArticleList(column,page,pageSize);
            }
        });
        adapter.setItemClickListener(new GItemClickListener() {
            @Override
            public void onItemClicked(View vs, int pos) {
                ArticleDetailActivity.startCollectionListActivity(getActivity(),tList.get(pos).id);
            }
        });

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullRefreshLayout.setRefreshing(true);
                page = 1;
//                presenter.getArticleList(column,page,pageSize);
            }
        });

        searchEt = (EditText) v.findViewById(R.id.et_search);
        searchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SearchArticleActivity.startSearchArticleActivity(getActivity(),column);
            }
        });

//        presenter = new ArticlePresenter(this);

        pullRefreshLayout.setRefreshing(true);
//        presenter.getArticleList(column,page,pageSize);
    }


//    @Override
//    public void onFail(int callId, String msg, int errId) {
//        pullRefreshLayout.setRefreshing(false);
//        if (callId == CallBackCode.TOKEN_GET_ARTICLE_LIST) {
//            if (page == 1) {
//                tList.clear();
//                adapter.notifyDataSetChanged();
//            } else {
//                page = page - 1;
//            }
//            if (tList == null || tList.size() == 0) {
//                if (page == 1)
//                    showErrorView(errorView,lv,2);
//            }
//        } else {
//            ToastUtil.show(msg);
//        }
//    }
//
//    @Override
//    public void getArticleList(String msg, ArrayList<TaxMessageInfo> data) {
//        pullRefreshLayout.setRefreshing(false);
//        if (data != null && data.size() > 0) {
//            if (page == 1) {
//                tList.clear();
//            }
//            tList.addAll(data);
//            page++;
//            if (pageSize == data.size()) {
//                lv.setLoadingMoreEnable(true);
//            } else {
//                page--;
//                lv.setLoadingMoreEnable(false);
//            }
//            adapter.notifyDataSetChanged();
//            showErrorView(errorView,lv,0);
//        } else {
//            if (page == 1) {
//                tList.clear();
//                showErrorView(errorView,lv,1);
//                adapter.notifyDataSetChanged();
//            } else {
//                page = page - 1;
//            }
//            lv.setLoadingMoreEnable(false);
//        }
//    }
//
//    @Override
//    public void getArticleDetail(String msg, TaxMessageInfo info) {
//
//    }
}

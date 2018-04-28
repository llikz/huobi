//package cn.grass.palmTax.fragments;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.baoyz.widget.PullRefreshLayout;
//
//import java.util.ArrayList;
//
//import cn.grass.palmTax.R;
//import cn.grass.palmTax.adapters.LawLegislationAdapter;
//import cn.grass.palmTax.base.BaseFragment;
//import cn.grass.palmTax.beans.LawLegislationInfo;
//import cn.grass.palmTax.inface.GItemClickListener;
//import cn.grass.palmTax.utils.ToastUtil;
//import cn.grass.palmTax.widgets.CustomRecyclerView;
//
///**
// * Created by linxc on 2017/7/14.
// * 学术论文
// */
//public class AcademicPaperFragment extends BaseFragment{
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View contentView=inflater.inflate(R.layout.fragment_law_legislation,null);
//        initView(contentView);
//        return contentView;
//    }
//
//
//    PullRefreshLayout pullRefreshLayout;
//    CustomRecyclerView lv;
//    View errorView = null;
//    LawLegislationAdapter adapter = null;
//    ArrayList<LawLegislationInfo> tList =null;
//    private void initView(View v) {
//        errorView = v.findViewById(R.id.view_error);
//        pullRefreshLayout = (PullRefreshLayout) v.findViewById(R.id.pull_layout);
//        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // TODO: 2017/7/14  刷新
//            }
//        });
//
//        lv = (CustomRecyclerView) v.findViewById(R.id.list_view);
//        lv.setItemAnimator(new DefaultItemAnimator());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        lv.setLayoutManager(linearLayoutManager);
//
//        tList = new ArrayList<>();
//        adapter = new LawLegislationAdapter(getActivity(), tList);
//        lv.setAdapter(adapter);
//        lv.setAutoLoadMoreEnable(true);
//        lv.setLoadMoreListener(new CustomRecyclerView.LoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                // TODO: 2017/7/14 加载更多
//
//            }
//        });
//        adapter.setItemClickListener(new GItemClickListener() {
//            @Override
//            public void onItemClicked(View vs, int pos) {
//                ToastUtil.show(pos+"");
//            }
//        });
//        initData();
//    }
//
//
//    private void initData() {
//        for (int i = 0;i<10;i++){
//            LawLegislationInfo info = new LawLegislationInfo();
//            info.title= "税务总局：税收改革落实工作目前进度已达百分之"+(i+10);
//            info.date = "2017-07-14";
//            tList.add(info);
//        }
//        adapter.notifyDataSetChanged();
//    }
//}

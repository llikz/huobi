package cn.grass.gate.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import cn.grass.gate.IntentParameter;
import cn.grass.gate.R;
import cn.grass.gate.adapters.PopMenuAdapter;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.presenters.ArticlePresenter;
import cn.grass.gate.utils.DensityUtil;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.views.ArticleView;
import cn.grass.gate.widgets.TopView;

/**
 * Created by linxc on 2017/7/24.
 */
public class ArticleDetailActivity extends BaseActivity implements ArticleView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        initView();
    }

    TextView titleTv,timeTv,readNumTv;
    TopView topView;
    WebView webView;
    ArticlePresenter presenter = null;
    String columnId = "";

    private void initView() {
        if (getIntent() == null) {
            finish();
            return;
        }
        columnId = getIntent().getStringExtra(IntentParameter.ARTICLE_ID);

        titleTv = (TextView) findViewById(R.id.tv_title);
        timeTv = (TextView) findViewById(R.id.tv_time);
        readNumTv = (TextView) findViewById(R.id.tv_read_num);

        topView = (TopView) findViewById(R.id.top_bar);
        topView.setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });
        topView.setOnRightListener(new TopView.OnRightClickListener() {
            @Override
            public void onRightClick(View v) {
                showMoreWindow(v);
            }
        });

        webView = (WebView) findViewById(R.id.wv_content);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hiddenDialog();
            }
        });

        presenter = new ArticlePresenter(this);
        showProgressDialog();
        presenter.getArticleDetail(columnId);
    }

    public static void startCollectionListActivity(Context c,String id) {
        Intent it = new Intent(c, ArticleDetailActivity.class);
        it.putExtra(IntentParameter.ARTICLE_ID,id);
        c.startActivity(it);
    }


    PopupWindow popupWindow;
    private void showMoreWindow(View v) {
        View vs = LayoutInflater.from(this).inflate(R.layout.view_top_pop_menu, null);
        int width = DensityUtil.dip2px(140);
        popupWindow = new PopupWindow(vs, width, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(v);
        ListView lv = (ListView) vs.findViewById(R.id.list_menu);
        lv.setPadding(1, 0, 1, 1);
        lv.setBackgroundColor(getResources().getColor(R.color.color_393A3F));
        final String[] menus = {"收藏", "分享"};
        Drawable[] drawables = {getResources().getDrawable(R.mipmap.icon_collection), getResources().getDrawable(R.mipmap.icon_share)};
        PopMenuAdapter adapter = new PopMenuAdapter(this, menus, drawables);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i==0){
//
//                }else if (i==1){
//
//                }
                ToastUtil.show(menus[i]);
                popupWindow.dismiss();
            }
        });
    }

//    @Override
//    public void onFail(int callId, String msg, int errId) {
//        hiddenDialog();
//        ToastUtil.show(msg);
//    }
//
//    @Override
//    public void getArticleList(String msg, ArrayList<TaxMessageInfo> data) {
//
//    }
//
//    @Override
//    public void getArticleDetail(String msg, TaxMessageInfo info) {
//        hiddenDialog();
//        if (info!=null){
//            titleTv.setText(info.title);
//            String date = DateUtil.getAllSimpleTimeFormat(info.date);
//            timeTv.setText(date);
//            readNumTv.setText("阅读 "+info.readNum);
//            // TODO: 2017/7/27 文章内容后台暂无返回
//        }
//    }
}

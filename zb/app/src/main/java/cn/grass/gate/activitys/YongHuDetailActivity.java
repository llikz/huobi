package cn.grass.gate.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.activitys.work.OldMeterBoxActivity;
import cn.grass.gate.adapters.TaiquAdapter;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.beans.Area;
import cn.grass.gate.beans.Taiqu;
import cn.grass.gate.widgets.CustomRecyclerView;
import cn.grass.gate.widgets.TopView;

/**
 * 用户详情
 */
public class YongHuDetailActivity extends BaseActivity implements View.OnClickListener {
    private ArrayList<Area> areas = new ArrayList<>();
    private YongHuDetailActivity activity;
    private PullRefreshLayout pullRefreshLayout;
    private CustomRecyclerView lv;
    private View errorView = null;
    private TaiquAdapter adapter = null;
    private Taiqu.DataBean.ListBean area;

    TextView areaNameTv;
    TextView userNameTv;
    TextView userPhoneTv;
    TextView userNoTv;
    TextView assertNoTv;
    TextView userAddrTv;
    TextView workTypeTv;
    TextView timeTv;
    RelativeLayout detailLL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonghu_detail);
        activity = this;
        initView();
        initData();
    }

    private void initData() {
        area = (Taiqu.DataBean.ListBean) getIntent().getSerializableExtra("area");
        if(null != area){
            areaNameTv.setText(area.getTxtTaiQuMingCheng());
            userNameTv.setText(area.getYongHuMingCheng());
            userPhoneTv.setText(area.getLianXiDianHua());
            userNoTv.setText(area.getYongHuBianHao());
            assertNoTv.setText(area.getZiChanBianHao());
            userAddrTv.setText(area.getYongDianDiZhi());
            userTypeTv.setText(area.getTxtYongHuLeiBie());
            yongdianleixingTv.setText(area.getTxtYongDianLeiBie());
            lihuriqiTv.setText(area.getLiHuRiQi());
            yonghuzhuangtaiTv.setText(area.getTxtYingXiaoYongHuZhuangKuang());
            gongdianjuTv.setText(area.getTxtGongDianJu());
            gongdiansuoTv.setText(area.getTxtGongDianSuo());
            bianyaqibianhaoTv.setText(area.getBianYaQiBianHao());
            bianyaqimingchengTv.setText(area.getBianYaQiMingCheng());
            kuixianmingchengTv.setText(area.getTxtKuiXianMingCheng());
            chaobiaoyuanTv.setText(area.getChaoBiaoYuan());
            chaobiaozhouqiTv.setText(area.getTxtChaoBiaoZhouQi());
            chaobiaofangshiTv.setText(area.getTxtZuiJinChaoBiaoFangShi());
            chaobiaoxuhaoTv.setText(area.getChaoBiaoXuHao());
            chaobiaoshijianTv.setText(area.getZuiJinChaoBiaoShiJian());
            chaobiaoleixingTv.setText(area.getTxtJiLiangDianChaoBiaoFangShi());
            shebeileixingTv.setText(area.getTxtSheBeiLeiXing());
            xinghaoTv.setText(area.getTxtSheBeiXingHao());
            shengchanchangjiaTv.setText(area.getTxtShengChanChangJia());
            shengchanriqiTv.setText(area.getChuChangRiQi());

        }
    }

    private TextView userTypeTv;
    private TextView yongdianleixingTv;
    private TextView lihuriqiTv;
    private TextView yonghuzhuangtaiTv;
    private TextView gongdianjuTv;
    private TextView gongdiansuoTv;
    private TextView bianyaqibianhaoTv;
    private TextView bianyaqimingchengTv;
    private TextView kuixianmingchengTv;
    private TextView chaobiaoyuanTv;
    private TextView chaobiaozhouqiTv;
    private TextView chaobiaofangshiTv;
    private TextView chaobiaoxuhaoTv;
    private TextView chaobiaoshijianTv;
    private TextView chaobiaoleixingTv;
    private TextView shebeileixingTv;
    private TextView xinghaoTv;
    private TextView shengchanchangjiaTv;
    private TextView shengchanriqiTv;
    private void initView() {
        ((TopView)findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });
        areaNameTv = (TextView) findViewById(R.id.area_name_tv);
        userNameTv = (TextView) findViewById(R.id.user_name_tv);
        userPhoneTv = (TextView)   findViewById(R.id.user_phone_tv);
        userNoTv = (TextView)   findViewById(R.id.user_no_tv);
        assertNoTv = (TextView)   findViewById(R.id.asset_no_tv);
        userAddrTv = (TextView) findViewById(R.id.user_addr_tv);
        detailLL = (RelativeLayout) findViewById(R.id.user_detail_ll);
        detailLL.setOnClickListener(this);


        userTypeTv =(TextView) findViewById(R.id.user_type_tv);
        yongdianleixingTv =(TextView) findViewById(R.id.yongdian_leixing_tv);
        lihuriqiTv =(TextView) findViewById(R.id.lihuriqi__tv);
        yonghuzhuangtaiTv =(TextView) findViewById(R.id.yonghuzhuangtai__tv);
        gongdianjuTv =(TextView) findViewById(R.id.gongdianju_tv);
        gongdiansuoTv =(TextView) findViewById(R.id.gongdiansuo_tv);
        bianyaqibianhaoTv =(TextView) findViewById(R.id.bianyaqibianhao_tv);
        bianyaqimingchengTv =(TextView) findViewById(R.id.bianyaqimingcheng_tv);
        kuixianmingchengTv =(TextView) findViewById(R.id.kuixianmingcheng_tv);
        chaobiaoyuanTv =(TextView) findViewById(R.id.chaobiaoyuan_tv);
        chaobiaozhouqiTv =(TextView) findViewById(R.id.chaobiaozhouqi_tv);
        chaobiaofangshiTv =(TextView) findViewById(R.id.chaobiaofangshi_tv);
        chaobiaoxuhaoTv =(TextView) findViewById(R.id.chaobiaoxuhao_tv);
        chaobiaoshijianTv =(TextView) findViewById(R.id.chaobiaoshijian_tv);
        chaobiaoleixingTv =(TextView) findViewById(R.id.chaobiaoleixing_tv);
        shebeileixingTv =(TextView) findViewById(R.id.shebeileixing_tv);
        xinghaoTv =(TextView) findViewById(R.id.xinghao_tv);
        shengchanchangjiaTv =(TextView) findViewById(R.id.shengchanchangjia_tv);
        shengchanriqiTv =(TextView) findViewById(R.id.shengchanriqi_tv);


    }

    public static void startYongHuDetailActivity(Context c,Taiqu.DataBean.ListBean area) {
        Intent it = new Intent(c, YongHuDetailActivity.class);
        it.putExtra("area",area);
        c.startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigate_iv:
                break;
            case R.id.user_detail_ll:
                break;
            case R.id.write_table_bt:
//                TestActivity.startTestActivity(AreaDetailActivity.this);
                OldMeterBoxActivity.startOldMeterBoxActivity(YongHuDetailActivity.this,"10000");//TODO 任务ID
                break;
            case R.id.exception_type_bt:
                break;
            case R.id.navigate:
//                NavigationActivity.startNavigationActivity(YongHuDetailActivity.this);
                break;

        }
    }
}

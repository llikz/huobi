package cn.grass.gate.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.grass.gate.R;
import cn.grass.gate.adapters.ListDropDownAdapter;
import cn.grass.gate.base.BaseFragment;
import cn.grass.gate.beans.ADInfo;
import cn.grass.gate.beans.HangQing;
import cn.grass.gate.beans.HomeBean;
import cn.grass.gate.beans.Price;
import cn.grass.gate.http.message.DataEvent;
import cn.grass.gate.presenters.HomePresenter;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.views.HomeView;
import cn.grass.gate.widgets.DropDownMenu;
import cn.grass.gate.widgets.ImageCycleView;


/**
 * Created by weedys on 17/7/13.
 * 律师
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, HomeView {

//    private PullRefreshLayout pullRefreshLayout;
//    private CustomRecyclerView lv;
//    private View errorView = null;
//
    private HomePresenter homePresenter;
//
//    private ConstellationAdapter constellationAdapter;

//    private int constellationPosition = 0;
    private List<View> popupViews = new ArrayList<>();
    private String headers[] = { "请选择币种"};

    private DropDownMenu mDropDownMenu;
    private ListDropDownAdapter listDropDownAdapter;

    private TextView last;
    private TextView high;
    private TextView low;
    private EditText highet;
    private EditText lowet;
    private TextView highertext;
    private TextView lowertext;
    private String setLowNum;
    private String setHighNum;
    private String selectMarket;
    private Button cancelbt;
    private Button startbt;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vs = inflater.inflate(R.layout.fragment_home, container, false);
        initView(vs);
        return vs;
    }


    private ArrayList<ADInfo> infos = new ArrayList<>();
    private String[] imageUrls = {"http://pic96.huitu.com/res/20170620/1263364_20170620171802920030_1.jpg",
            "http://pic96.huitu.com/res/20170620/1263364_20170620171802920030_1.jpg",
            "http://pic96.huitu.com/res/20170620/1263364_20170620171802920030_1.jpg",};
    private List<String> markets = new ArrayList<>();


    {


    }

    private ImageCycleView mAdView;


    private void initView(View v) {
        vibrator = (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        EventBus.getDefault().register(this);
        String str = "ltc_btc,eth_btc,etc_btc,bts_btc,bcc_btc,qtum_btc,eos_btc,hsr_btc,xrp_btc,btc_usdt,ltc_usdt,eth_usdt,etc_usdt,bts_usdt,bcc_usdt,qtum_usdt,eos_usdt,hsr_usdt,xrp_usdtbtc_qc,ltc_qc,eth_qc,etc_qc,bts_qc,bcc_qc,qtum_qc,eos_qc,hsr_qc,xrp_qc";
        String[] str2 = {"ltc_btc","eth_btc","etc_btc"};

        String[] strings = str.split(",");
        for (int i = 0; i < strings.length; i++) {
            markets.add(strings[i]);
        }
        for (int i = 0; i < imageUrls.length; i++) {
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("top-->" + i);
            infos.add(info);
        }
        mAdView = (ImageCycleView) v.findViewById(R.id.ad_view);
        mAdView.setImageResources(infos, mAdCycleViewListener);
        mDropDownMenu = (DropDownMenu) v.findViewById(R.id.dropDownMenu);

        final ListView sexView = new ListView(getActivity());
        sexView.setDividerHeight(0);
        listDropDownAdapter = new ListDropDownAdapter(getActivity(), markets);
        sexView.setAdapter(listDropDownAdapter);
        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listDropDownAdapter.setCheckItem(position);
                selectMarket = markets.get(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : selectMarket);
                mDropDownMenu.closeMenu();
                homePresenter.getTicker(markets.get(position));
                showProgressDialog("正在加载...");
            }
        });

//        final View constellationView = getActivity().getLayoutInflater().inflate(R.layout.custom_layout, null);
//        GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
//        constellationAdapter = new ConstellationAdapter(getActivity(), markets);
//        constellation.setAdapter(constellationAdapter);
//        TextView ok = ButterKnife.findById(constellationView, R.id.ok);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDropDownMenu.setTabText(constellationPosition == 0 ? getString(R.string.zb_select_text) : markets.get(constellationPosition));
//                mDropDownMenu.closeMenu();
//            }
//        });
//        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                constellationAdapter.setCheckItem(position);
//                constellationPosition = position;
//            }
//        });
        popupViews.add(sexView);
        //init context view
        TextView contentView = new TextView(getActivity());
//        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        contentView.setText("内容显示区域");
//        contentView.setGravity(Gravity.CENTER);
//        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,contentView);


        homePresenter = new HomePresenter(this);



//        taskLL = (RelativeLayout)v.findViewById(R.id.task_ll);
//        taskLL.setOnClickListener(this);
//        queryLL = (RelativeLayout)v.findViewById(R.id.query_ll);
//        queryLL.setOnClickListener(this);
//        checkLL = (RelativeLayout)v.findViewById(R.id.check_ll);
//        checkLL.setOnClickListener(this);

//        errorView = v.findViewById(R.id.view_error);
//        pullRefreshLayout = (PullRefreshLayout) v.findViewById(R.id.pull_layout);
//        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                taiquPresenter.getTaiquList(tId,pageIndex,pageSize);
//            }
//        });
//
//        lv = (CustomRecyclerView)v.findViewById(R.id.list_view);
//        lv.setItemAnimator(new DefaultItemAnimator());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        lv.setLayoutManager(linearLayoutManager);
//        homePresenter.getHomeList();


        last = (TextView)v.findViewById(R.id.last);
        high = (TextView)v.findViewById(R.id.high);
        low = (TextView)v.findViewById(R.id.low);

        highet = (EditText)v.findViewById(R.id.highet);

        lowet = (EditText)v.findViewById(R.id.lowet);
        cancelbt = (Button)v.findViewById(R.id.cancelbt);
        cancelbt.setOnClickListener(this);
        startbt = (Button)v.findViewById(R.id.startbt);
        startbt.setOnClickListener(this);
        highertext = (TextView)v.findViewById(R.id.highertext);
        lowertext = (TextView)v.findViewById(R.id.lowertext);
        highet.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // 输入的内容变化的监听
//                Log.e("输入过程中执行该方法", "文字变化");
//
//                try {
//                    Double zhang = Double.parseDouble(s+"");
//                    if(zhang <= 0){
//                        return;
//                    }
//                    Double num = (1 + zhang) * Double.parseDouble(lastNum);
//                    DecimalFormat df = new DecimalFormat("#.0000");
////                    setHighNum = df.format(num);
//                    setHighNum = zhang+"";//TODO 设置最大值
//                    highertext.setText(setHighNum);
//                }catch (Exception e){
//                    ToastUtil.shortShow(getString(R.string.zb_input_hint));
//                }   // 输入的内容变化的监听
//                Log.e("输入过程中执行该方法", "文字变化");
//
//                try {
//                    Double zhang = Double.parseDouble(s+"");
//                    if(zhang <= 0){
//                        return;
//                    }
//                    Double num = (1 + zhang) * Double.parseDouble(lastNum);
//                    DecimalFormat df = new DecimalFormat("#.0000");
////                    setHighNum = df.format(num);
//                    setHighNum = zhang+"";//TODO 设置最大值
//                    highertext.setText(setHighNum);
//                }catch (Exception e){
//                    ToastUtil.shortShow(getString(R.string.zb_input_hint));
//                }
                setHighNum = s+"";//TODO 设置最大值
                highertext.setText(setHighNum);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // 输入前的监听
                Log.e("输入前确认执行该方法", "开始输入");

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入后的监听
                Log.e("输入结束执行该方法", "输入结束");

            }
        });

        lowet.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入的内容变化的监听
//                Log.e("输入过程中执行该方法", "文字变化");
//
//                try {
//                    Double die = Double.parseDouble(s+"");
//                    if(die <= 0){
//                        return;
//                    }
//                    Double num = (1 - die) * Double.parseDouble(lastNum);
//                    DecimalFormat df = new DecimalFormat("#.0000");
////                    setLowNum = df.format(num);
//                    setLowNum = die+"";//TODO 设置最小值
//                    lowertext.setText(setLowNum);
//                }catch (Exception e){
//                    ToastUtil.shortShow(getString(R.string.zb_input_hint));
//                }
                setLowNum = s+"";//TODO 设置最小值
                lowertext.setText(setLowNum);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // 输入前的监听
                Log.e("输入前确认执行该方法", "开始输入");

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入后的监听
                Log.e("输入结束执行该方法", "输入结束");

            }
        });

    }

    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            Toast.makeText(getActivity(), "content->" + info.getContent(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            Glide.with(getActivity()).load(imageURL).asBitmap().error(R.mipmap.icon_default_image).centerCrop().into(imageView);
        }
    };


    private boolean cancelFlag = false;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancelbt:
                vibrator.cancel();
                cancelFlag = true;
                ToastUtil.shortShow(getString(R.string.zb_cancel_hint));
                break;
            case R.id.startbt:
                //开启监控
//                homePresenter.getTicker(selectMarket);
                homePresenter.getGateRate();
                cancelFlag = false;
                ToastUtil.shortShow(getString(R.string.zb_start_hint));
                break;

//            case R.id.query_ll:
////                ScanBoxActivity.startScanActivity(getActivity(),ScanBoxActivity.TYPE_MAIN,ScanBoxActivity.CLASS_SCAN_CODE);
//                break;
//            case R.id.check_ll:
//                AssessActivity.startAssessActivity(getActivity());
////                TestActivity.startTestActivity(getActivity());
//                break;


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdView.startImageCycle();
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdView.pushImageCycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdView.pushImageCycle();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void homeList(HomeBean homeBean) {
        if(null != homeBean){

        }
    }

    String lastNum = "";
    String highNum = "";
    String lowNum = "";
    Vibrator vibrator;
    @Override
    public void hangQing(HangQing hangQing) {
        if(null != hangQing){
            lastNum = hangQing.getTicker().getLast();
            last.setText("最新:"+hangQing.getTicker().getLast());
            high.setText("最高:"+hangQing.getTicker().getHigh());
            low.setText("最低: "+hangQing.getTicker().getLow());
            //振动
            boolean flag1 = Double.parseDouble(lastNum) - Double.parseDouble(setHighNum)> 0;
            boolean flag2 = Double.parseDouble(lastNum) - Double.parseDouble(setLowNum) < 0;
            if((flag1 || flag2) && !cancelFlag){
                                  /*
                         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
                         * */

                long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启
                vibrator.vibrate(pattern,2);           //重复两次上面的pattern 如果只想震动一次，index设为-1
            }else {
                vibrator.cancel();
            }


        }
        hiddenDialog();
        EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_SHUAXIN_HANGQING));
    }

    @Override
    public void price(Price price) {
        if(null != price){
            lastNum = price.getSell_rate()+"";
            last.setText("新买:"+price.getBuy_rate());
            high.setText("新卖:"+price.getSell_rate());
            low.setText("近低: "+price.getMin_rate()+"近高: "+price.getMax_rate());
            //振动
            boolean flag1 = Double.parseDouble(lastNum) - Double.parseDouble(setHighNum)> 0;
            boolean flag2 = Double.parseDouble(lastNum) - Double.parseDouble(setLowNum) < 0;
            if((flag1 || flag2) && !cancelFlag){
                                  /*
                         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
                         * */

                long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启
                vibrator.vibrate(pattern,2);           //重复两次上面的pattern 如果只想震动一次，index设为-1
            }else {
                vibrator.cancel();
            }


        }
        hiddenDialog();
        EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_SHUAXIN_HANGQING));
    }

    @Override
    public void onSuccess(int callId, String body, String msg) {

    }

    @Override
    public void onFail(int callId, String msg, int errId) {
        ToastUtil.shortShow(getString(R.string.zb_error_hint));
        hiddenDialog();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void DataEventBus(DataEvent type) {
        if (type != null) {
            switch (type.mType) {
                case DataEvent.TYPE_SHUAXIN_HANGQING:
//                   ToastUtil.shortShow("轮到我干活了");
                    try {
                        Thread.sleep(60000);

//                        homePresenter.getTicker(selectMarket);
                        homePresenter.getGateRate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}

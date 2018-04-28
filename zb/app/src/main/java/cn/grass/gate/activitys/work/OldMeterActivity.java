package cn.grass.gate.activitys.work;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weedys.weedlibrary.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.http.message.DataEvent;
import cn.grass.gate.model.Meter;
import cn.grass.gate.utils.BitmapUtil;
import cn.grass.gate.utils.GlideLoader;
import cn.grass.gate.utils.ImageUtils;
import cn.grass.gate.widgets.TopView;

/**
 * 旧表
 */
public class OldMeterActivity extends BaseActivity implements View.OnClickListener {
    private OldMeterActivity activity;
    private String taskId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_meter);
        EventBus.getDefault().register(this);
        activity = this;
        initView();
        initData();
    }

    private void initData() {
        List<Meter> meters =  DataSupport.where("taskId = ? and type = ?", taskId,"0").find(Meter.class);
        Meter meter = meters.get(0);
        //得到图片
        Bitmap map = BitmapUtil.StringToBitmap(meter.getMeterpic());
        putMeterPicIv.setImageBitmap(map);
        Bitmap mapSeal = BitmapUtil.StringToBitmap(meter.getSealnopic());
        putSealNoPicIv.setImageBitmap(mapSeal);
        powerEt.setText(meter.getPower());
        assetNoEt.setText(meter.getAssetno());
        sealNumberEt.setText(meter.getSealnumber());
        if(null != picPathList){
            picPathList.clear();
            picPathList.add(meter.getMeterpicpath());
        }
        if(null != sealPathList){
            sealPathList.clear();
            sealPathList.add(meter.getSealnopicpath());
        }
    }

    private Meter meter;
    private EditText powerEt;
    private TextView powerScanTv;
    private TextView cleanTv;
    private EditText assetNoEt;
    private TextView assetNoScanTv;
    private EditText sealNumberEt;
    private TextView sealNumberScanTv;
    private ImageView putMeterPicIv;
    private ImageView meterPicIv;
    private ImageView putSealNoPicIv;
    private ImageView sealNoPicIv;
    private ArrayList<String> picPathList = new ArrayList<>();//存放电能表图片
    private ArrayList<String> sealPathList = new ArrayList<>();//存放序列号图片
    private String picStr;
    private String sealStr;
    private void initView() {
        ((TopView)findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                save();
                onBackPressed();
            }
        });
        ((TopView)findViewById(R.id.top_bar)).setOnRightListener(new TopView.OnRightClickListener() {
            @Override
            public void onRightClick(View v) {
                save();
                TakePicActivity.startTakePicActivity(OldMeterActivity.this,taskId);
            }
        });

        Intent intent = getIntent();
        taskId = intent.getStringExtra("task_id");
        List<Meter> meterList =  new ArrayList<>();
        try {
            meterList = DataSupport.where("taskId = ? and type = ?", taskId,"0").find(Meter.class);
            for (int i = 0; i < meterList.size(); i++) {
                LogUtil.show("aaa0"+meterList.get(i).toString());
            }
        }catch (Exception e){
            LogUtil.show(e.toString());
        }
        if(meterList.size() < 1){
            meter = new Meter();
            meter.setTaskId(taskId);
            meter.setType("0");
            meter.save();
        }
        picPathList.add("");
        sealPathList.add("");

        cleanTv = (TextView)findViewById(R.id.clean_tv);
        cleanTv.setOnClickListener(this);
        powerEt = (EditText)findViewById(R.id.power_et);
        powerScanTv = (TextView)findViewById(R.id.power_scan_tv);
        assetNoEt = (EditText)findViewById(R.id.asset_no_colon_et);
        assetNoScanTv = (TextView)findViewById(R.id.asset_no_scan_tv);
        assetNoScanTv.setOnClickListener(this);
        sealNumberEt = (EditText)findViewById(R.id.seal_number_et);
        sealNumberScanTv = (TextView)findViewById(R.id.scan_seal_number_tv);
        sealNumberScanTv.setOnClickListener(this);
        putMeterPicIv = (ImageView)findViewById(R.id.put_meter_pic_iv);
        putMeterPicIv.setOnClickListener(this);
        meterPicIv = (ImageView)findViewById(R.id.meter_pic_iv);
        meterPicIv.setOnClickListener(this);
        putSealNoPicIv = (ImageView)findViewById(R.id.put_seal_number_pic_iv);
        putSealNoPicIv.setOnClickListener(this);
        sealNoPicIv = (ImageView)findViewById(R.id.seal_number_pic_iv);
        sealNoPicIv.setOnClickListener(this);

    }
    /**
     * 保存页面上的值
     */
    private void save() {
        //压缩等到string
        picStr = ImageUtils.bitmapToString(picPathList.get(0));
        sealStr = ImageUtils.bitmapToString(sealPathList.get(0));
        //保存到db
        ContentValues values = new ContentValues();
        values.put("meterpic", picStr);
        values.put("meterpicpath", picPathList.get(0));
        values.put("power", powerEt.getText().toString().trim());
        values.put("assetno", assetNoEt.getText().toString().trim());
        values.put("sealnumber", sealNumberEt.getText().toString().trim());
        values.put("sealnopic", sealStr);
        values.put("sealnopicpath", sealPathList.get(0));
        //整体电表图片 这个数据在下一个界面
        DataSupport.updateAll(Meter.class, values,"taskId = ? and type = ?", taskId,"0");
        //TODO
        List<Meter> meterList = DataSupport.where("taskId = ? and type = ?", taskId,"0").find(Meter.class);
        for (int i = 0; i < meterList.size(); i++) {
            LogUtil.show("aaa1"+meterList.get(i).toString());
        }
    }

    public static void startOldMeterActivity(Context c) {
        Intent it = new Intent(c, OldMeterActivity.class);
        c.startActivity(it);
    }
    public static void startOldMeterActivity(Context c,String id) {
        Intent it = new Intent(c, OldMeterActivity.class);
        it.putExtra("task_id",id);//存放任务ID
        c.startActivity(it);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_SHOW_METER));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void DataEventBus(DataEvent type) {
        if (type != null) {
            switch (type.mType) {
                case DataEvent.TYPE_SHOW_METER:
                    Glide.with(activity)
                            .load(picPathList.get(0))
                            .into(putMeterPicIv);
                    Glide.with(activity)
                            .load(sealPathList.get(0))
                            .into(putSealNoPicIv);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.meter_pic_iv:
                if(null != picPathList){
                    picPathList.clear();
                }
                GlideLoader.show(OldMeterActivity.this, picPathList, 1);
                break;
            case R.id.seal_number_pic_iv:
                if(null != sealPathList){
                    sealPathList.clear();
                }
                GlideLoader.show(OldMeterActivity.this, sealPathList, 1);
                break;
            case R.id.clean_tv:
                clear();
                break;
        }
    }

    private void clear() {
        powerEt.setText("");
        assetNoEt.setText("");
        sealNumberEt.setText("");
        if(null != picPathList){
            picPathList.clear();
            picPathList.add("");
        }
        if (null != sealPathList){
            sealPathList.clear();
            sealPathList.add("");
        }
        EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_SHOW_METER));
    }
}

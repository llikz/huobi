package cn.grass.gate.activitys.work;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weedys.weedlibrary.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.grass.gate.R;
import cn.grass.gate.activitys.ShowPicActivity;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.http.message.DataEvent;
import cn.grass.gate.model.Box;
import cn.grass.gate.utils.BitmapUtil;
import cn.grass.gate.utils.GlideLoader;
import cn.grass.gate.utils.ImageUtils;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.widgets.TopView;

/**
 * 旧表箱
 */
public class OldMeterBoxActivity extends BaseActivity implements View.OnClickListener {
    private OldMeterBoxActivity activity;
    private String taskId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_meter_box);
        activity = this;
        EventBus.getDefault().register(this);
        initView();
        initData();
        initTessData();
    }

    //初始化识别的字典
    private void initTessData() {
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                deepFile("tessdata");
                EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_CLOSE_DIALOG));
            }
        }).start();
    }
    /**
     * 将assets中的文件复制出
     *
     * @param path
     */
    public void deepFile(String path) {
        String newPath = getExternalFilesDir(null) + "/";
        try {
            String str[] = getAssets().list(path);
            if (str.length > 0) {//如果是目录
                File file = new File(newPath + path);
                file.mkdirs();
                for (String string : str) {
                    path = path + "/" + string;
                    deepFile(path);
                    path = path.substring(0, path.lastIndexOf('/'));//回到原来的path
                }
            } else {//如果是文件
                InputStream is = getAssets().open(path);
                FileOutputStream fos = new FileOutputStream(new File(newPath + path));
                byte[] buffer = new byte[1024];
                int count = 0;
                while (true) {
                    count++;
                    int len = is.read(buffer);
                    if (len == -1) {
                        break;
                    }
                    fos.write(buffer, 0, len);
                }
                is.close();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        List<Box> boxes =  DataSupport.where("taskId = ? and type = ?", taskId,"0").find(Box.class);
        Box box = boxes.get(0);
        boxStampEt.setText(box.getBoxstamp());
        //得到图片
        Bitmap map = BitmapUtil.StringToBitmap(box.getBoxpic());
        putBoxPicIv.setImageBitmap(map);
        assetNoEtMust.setText(box.getAssetno());
        modelNoEt.setText(box.getModel());
        factoryEtMust.setText(box.getFactory());
        specEt.setText(box.getSpec());
        boxMaterialEtMust.setText(box.getBoxmaterial());
        productDateEtMust.setText(box.getProductdate());
        protectLevelEt.setText(box.getProtectlevel());
        remarkEt.setText(box.getRemark());
        if(null != pathList){
            pathList.clear();
            pathList.add(box.getBoxpicpath());
        }

    }
    private  Box box;
    private EditText  boxStampEt;
    private TextView boxStampScanTv;
    private TextView boxStampPicTv;
    private String boxPicStr;
    private ImageView putBoxPicIv;
    private ImageView boxPicScanIv;
    private EditText assetNoEtMust;//must结尾代表必填
    private TextView assetNoScanTv;
    private EditText modelNoEt;
    private EditText factoryEtMust;
    private EditText specEt;
    private EditText boxMaterialEtMust;
    private EditText productDateEtMust;
    private EditText protectLevelEt;
    private EditText remarkEt;
    private LinearLayout remarkLL;
    private ArrayList<String> pathList = new ArrayList<>();

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
                // 检查必填项是否有空值
                if(TextUtils.isEmpty(assetNoEtMust.getText().toString().trim())){
                    ToastUtil.longShow(getString(R.string.not_null));
                    return;
                }
                if(TextUtils.isEmpty(factoryEtMust.getText().toString().trim())){
                    ToastUtil.longShow(getString(R.string.not_null));
                    return;
                }
                if(TextUtils.isEmpty(boxMaterialEtMust.getText().toString().trim())){
                    ToastUtil.longShow(getString(R.string.not_null));
                    return;
                }
                if(TextUtils.isEmpty(productDateEtMust.getText().toString().trim())){
                    ToastUtil.longShow(getString(R.string.not_null));
                    return;
                }
                //检查完空值后保存
                save();
                OldMeterActivity.startOldMeterActivity(OldMeterBoxActivity.this,taskId);
            }
        });
        //旧表箱取消展示备注
        remarkLL = (LinearLayout)findViewById(R.id.remark_ll);
        remarkEt = (EditText)findViewById(R.id.edit_et);
        remarkLL.setVisibility(View.GONE);
        boxStampEt = (EditText)findViewById(R.id.box_stamp_et);
        boxStampScanTv = (TextView)findViewById(R.id.box_stamp_scan_tv);
        boxStampScanTv.setOnClickListener(this);
        boxStampPicTv = (TextView)findViewById(R.id.box_stamp_pic_tv);
        boxStampPicTv.setOnClickListener(this);
        putBoxPicIv = (ImageView)findViewById(R.id.put_box_pic_iv);
        putBoxPicIv.setOnClickListener(this);
        boxPicScanIv = (ImageView)findViewById(R.id.box_pic_iv);
        boxPicScanIv.setOnClickListener(this);
        assetNoEtMust = (EditText)findViewById(R.id.asset_no_et);
        assetNoScanTv = (TextView)findViewById(R.id.asset_no_scan_tv);
        modelNoEt = (EditText)findViewById(R.id.model_no_et);
        factoryEtMust = (EditText)findViewById(R.id.factory_et);
        specEt = (EditText)findViewById(R.id.spec_et);
        boxMaterialEtMust = (EditText)findViewById(R.id.cabinet_material_et);
        productDateEtMust = (EditText)findViewById(R.id.product_date_et);
        protectLevelEt = (EditText)findViewById(R.id.protect_level_et);

        Intent intent = getIntent();
        taskId = intent.getStringExtra("task_id");
//        List<Box> boxList =  DataSupport.where("taskId = ? and type = ?", taskId,"0").find(Box.class);
        List<Box> boxList =  new ArrayList<>();
        try {
            boxList = DataSupport.where("taskId = ? and type = ?", taskId,"0").find(Box.class);
        }catch (Exception e){
            LogUtil.show(e.toString());
        }
        if(boxList.size() < 1){
            box = new Box();
            box.setTaskId(taskId);
            box.setType("0");
            box.save();
        }

    }

    /**
     * 保存页面上的值
     */
    private void save() {
        //压缩等到string
        boxPicStr = ImageUtils.bitmapToString(pathList.get(0));
        //保存到db
        ContentValues values = new ContentValues();
        values.put("boxpic", boxPicStr);
        values.put("boxpicpath", pathList.get(0));
        values.put("boxstamp", boxStampEt.getText().toString().trim());
        values.put("assetno", assetNoEtMust.getText().toString().trim());
        values.put("model", modelNoEt.getText().toString().trim());
        values.put("factory", factoryEtMust.getText().toString().trim());
        values.put("spec", specEt.getText().toString().trim());
        values.put("boxmaterial", boxMaterialEtMust.getText().toString().trim());
        values.put("productdate", productDateEtMust.getText().toString().trim());
        values.put("protectlevel", protectLevelEt.getText().toString().trim());
        values.put("remark", remarkEt.getText().toString().trim());
        DataSupport.updateAll(Box.class, values,"taskId = ? and type = ?", taskId,"0");
    }

    public static void startOldMeterBoxActivity(Context c) {
        Intent it = new Intent(c, OldMeterBoxActivity.class);
        c.startActivity(it);
    }
    public static void startOldMeterBoxActivity(Context c,String id) {
        Intent it = new Intent(c, OldMeterBoxActivity.class);
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
        EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_SHOW_BOX));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void DataEventBus(DataEvent type) {
        if (type != null) {
            switch (type.mType) {
                case DataEvent.TYPE_SHOW_BOX:
                    Glide.with(activity)
                            .load(pathList.get(0))
                            .into(putBoxPicIv);
                    break;
                case DataEvent.TYPE_CLOSE_DIALOG:
                    hiddenDialog();
                    break;
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.box_pic_iv:
                if(null != pathList){
                    pathList.clear();
                }
                GlideLoader.show(OldMeterBoxActivity.this, pathList, 1);
                break;
            case R.id.put_box_pic_iv:
                String[] pic = {pathList.get(0)};
                if(!TextUtils.isEmpty(pathList.get(0))){
                    ShowPicActivity.ShowLocalPicActivity(OldMeterBoxActivity.this, pic,0);
                }
                break;
            case R.id.box_stamp_scan_tv:
//                ScanBoxActivity.startScanActivity(this,ScanBoxActivity.TYPE_BOX_SEAL_SCAN,ScanBoxActivity.TYPE_BOX_SEAL_CODE);
                break;
            case R.id.box_stamp_pic_tv:
//                TakePhoteActivity.startTakePhoteActivity(this,TakePhoteActivity.TYPE_BOX_SEAL_SCAN,TakePhoteActivity.TYPE_BOX_SEAL_CODE);//TODO
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == ScanBoxActivity.TYPE_BOX_SEAL_CODE){
//            //旧表箱封印
//            if (resultCode == RESULT_OK) {
//                if (data != null) {
//                    String code = data.getStringExtra(ScanBoxActivity.KEY_BOX_CODE);
//                    //回填码
//                    if (!TextUtils.isEmpty(code)) {
//                        boxStampEt.setText(code);
//                    }
//                }
//            }
//        }else if(requestCode == TakePhoteActivity.TYPE_BOX_SEAL_CODE){
//            //旧表箱封印(识别)
//            if (resultCode == RESULT_OK) {
//                if (data != null) {
//                    String code = data.getStringExtra(TakePhoteActivity.KEY_BOX_CODE);
//                    //正则处理过滤大写字母小写字母数字,
//                    String result = filter(code);
//                    //回填码
//                    if (!TextUtils.isEmpty(result)) {
//                        boxStampEt.setText(result);
//                    }
//                }
//            }
//        }
    }
    public String filter(String character)
    {
        character = character.replaceAll("[^(a-zA-Z0-9)]", "");
        // 小写字母转大写
        character = character.toUpperCase();
        return character;
    }
}

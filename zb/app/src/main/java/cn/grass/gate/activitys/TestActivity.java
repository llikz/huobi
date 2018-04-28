package cn.grass.gate.activitys;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.model.StoreTest;
import cn.grass.gate.utils.BitmapUtil;
import cn.grass.gate.utils.GlideLoader;
import cn.grass.gate.utils.ImageUtils;

/**
 * Created by min on 2017/7/28.
 * 一个用来测试的activity
 */

public class TestActivity extends BaseActivity implements View.OnClickListener {

    TextView scanTv;
    ImageView photoIv;
    ImageView testphotoIv;
    EditText scanEt;
//    private String testPath = "/storage/emulated/0/Download/tisddddddmg.jpg";
    private String testPath = "/storage/emulated/0/t112img.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
//        if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
////            window.setStatusBarColor(Color.BLUE);
////            window.setStatusBarColor(R.color.hint_text_color);
//        }
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        initView();
//        initData();
    }

    private void initData() {
        SQLiteDatabase db = Connector.getDatabase();

        for (int i = 0; i < 5; i++) {
            StoreTest store = new StoreTest();
            store.setName("离宝宝");
            store.setTaskId("10000");
            store.setPic("图片");
            Log.d("TAG", "store id is " + store.getId());
            store.save();
            Log.d("TAG", "store id is " + store.getId());
        }

        List<StoreTest> allNews = DataSupport.findAll(StoreTest.class);
        if(null != allNews){
            for (int i = 0; i < allNews.size(); i++) {
                Log.d("TAG", allNews.get(i).toString());
            }
        }

//        if (store.save()) {
//            Toast.makeText(TestActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(TestActivity.this, "存储失败", Toast.LENGTH_SHORT).show();
//        }
    }

    public static boolean isPath(String path){
        if(Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
            File file = new File(path);
            if(!file.exists()){
//                file.mkdirs();
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
    @Override
    protected void onStart() {
        super.onStart();
        //判断路径
        boolean flag = isPath(testPath);
        if(flag){
            //压缩等到string
            String pic = ImageUtils.bitmapToString(testPath);
            //保存到db
            ContentValues values = new ContentValues();
            values.put("pic", pic);
            //id为2
            DataSupport.update(StoreTest.class, values, 2);
        }

//        ContentValues values = new ContentValues();
//        values.put("pic", "pic");
//        //id为2
//        DataSupport.update(StoreTest.class, values, 2);




    }

    private void initView() {
        scanEt = (EditText)findViewById(R.id.scan_et);
        scanTv = (TextView)findViewById(R.id.scan);
        scanTv.setOnClickListener(this);
        photoIv = (ImageView)findViewById(R.id.photo);
        testphotoIv = (ImageView)findViewById(R.id.test_photo);
        testphotoIv.setOnClickListener(this);

    }

    public static void startTestActivity(Context c){
        Intent it=new Intent(c,TestActivity.class);
        c.startActivity(it);
    }

    private ArrayList<String> pathList = new ArrayList<>();
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.scan){
//            ScanBoxActivity.startScanActivity(this,ScanBoxActivity.TYPE_BOX_SCAN,ScanBoxActivity.BOX_CODE);
        }else if(v.getId() == R.id.photo){
            GlideLoader.show(TestActivity.this, pathList, 1);
        }else if(v.getId() == R.id.test_photo){
            //查询图片
            StoreTest store = DataSupport.find(StoreTest.class,2);
            //得到图片
            Bitmap map = BitmapUtil.StringToBitmap(store.getPic());
            //设置图片
            testphotoIv.setImageBitmap(map);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == ScanBoxActivity.BOX_CODE){
//            if (resultCode == RESULT_OK) {
//                if (data != null) {
//                    String code = data.getStringExtra(ScanBoxActivity.KEY_BOX_CODE);
//                    //回填班级码
//                    if (!TextUtils.isEmpty(code)) {
//                        scanEt.setText(code);
//                    }
//                }
//            }
//        }
    }
}

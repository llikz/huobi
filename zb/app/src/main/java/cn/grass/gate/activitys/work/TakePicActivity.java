package cn.grass.gate.activitys.work;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.widgets.TopView;

/**
 * 旧表
 */
public class TakePicActivity extends BaseActivity implements View.OnClickListener {
    private TakePicActivity activity;
    private String taskId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pic);
        activity = this;
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        taskId = intent.getStringExtra("task_id");
    }

    private void initView() {
        ((TopView)findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });
        ((TopView)findViewById(R.id.top_bar)).setOnRightListener(new TopView.OnRightClickListener() {
            @Override
            public void onRightClick(View v) {
                NewMeterActivity.startNewMeterActivity(TakePicActivity.this,taskId);
            }
        });
    }

    public static void startTakePicActivity(Context c) {
        Intent it = new Intent(c, TakePicActivity.class);
        c.startActivity(it);
    }
    public static void startTakePicActivity(Context c,String id) {
        Intent it = new Intent(c, TakePicActivity.class);
        it.putExtra("task_id",id);//存放任务ID
        c.startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}

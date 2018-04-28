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
public class NewMeterActivity extends BaseActivity implements View.OnClickListener {
    private NewMeterActivity activity;
    private String taskId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //布局跟旧表共用
        setContentView(R.layout.activity_old_meter);
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
                NewMeterBoxActivity.startNewMeterBoxActivity(NewMeterActivity.this,taskId);
            }
        });
        ((TopView)findViewById(R.id.top_bar)).getTitleTextView().setText(getString(R.string.new_meter));
    }

    public static void startNewMeterActivity(Context c) {
        Intent it = new Intent(c, NewMeterActivity.class);
        c.startActivity(it);
    }
    public static void startNewMeterActivity(Context c,String id) {
        Intent it = new Intent(c, NewMeterActivity.class);
        it.putExtra("task_id",id);//存放任务ID
        c.startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}

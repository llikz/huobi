package cn.grass.gate.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.widgets.TopView;

/**
 * Created by linxc on 2017/7/24.
 * 个人设置
 */
public class UserSettingActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        initView();
    }

    ImageView avatarIgv;
    TextView nameTv, postTv;

    private void initView() {
        ((TopView)findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });

        avatarIgv = (ImageView) findViewById(R.id.igv_avatar);
        nameTv = (TextView) findViewById(R.id.tv_name);
        postTv = (TextView) findViewById(R.id.tv_post);
        findViewById(R.id.tv_exit).setOnClickListener(this);
    }

    public static void startUserSettingActivity(Context c) {
        Intent it = new Intent(c, UserSettingActivity.class);
        c.startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exit://退出账号

                break;
        }
    }
}

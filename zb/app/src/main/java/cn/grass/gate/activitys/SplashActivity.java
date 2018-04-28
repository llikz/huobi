package cn.grass.gate.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.WindowManager;

import com.umeng.analytics.MobclickAgent;
import com.weedys.weedlibrary.utils.LogUtil;

import cn.grass.gate.GlobalApp;
import cn.grass.gate.GrassApp;
import cn.grass.gate.MainActivity;
import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.beans.UserInfo;
import cn.grass.gate.data.PrefManager;

/**
 * Created by weedys on 16/7/20.
 */
public class SplashActivity extends BaseActivity {
//    TextView verTv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
//        verTv = (TextView)findViewById(R.id.tv_ver);
//        String verstr= CommonUtils.getBaseAppVersionName(getApplication());
//        verTv.setText("v"+verstr);

        int first= PrefManager.getPrefInt(GlobalApp.PRE_FIRST,1);
        if(first==1){//第一次登录
            update.sendEmptyMessageDelayed(3, 3000);
        }else {
            UserInfo userInfo = GrassApp.getInstance().getUserInfo();
            if (userInfo!=null&&!TextUtils.isEmpty(userInfo.phone)){//已登录
                update.sendEmptyMessageDelayed(1, 3000);
            }else {
                update.sendEmptyMessageDelayed(2, 3000);//未登录
            }
        }
    }

    private Handler update=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LogUtil.show("go");
            if(msg.what==1){
                MainActivity.startMainActivity(SplashActivity.this);
//                LoginActivity.startLoginActivity(SplashActivity.this);
                finish();
            } else if(msg.what==2){
                LogUtil.show("go to login");
                LoginActivity.startLoginActivity(SplashActivity.this);
                finish();
            } else if(msg.what==3){//引导页
                FirstPageActivity.startFirstPageActivity(SplashActivity.this,FirstPageActivity.GUIDE_FIRST);
                finish();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

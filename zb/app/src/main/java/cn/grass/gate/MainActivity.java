package cn.grass.gate;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.weedys.weedlibrary.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.base.BaseFragment;
import cn.grass.gate.fragments.PersonalFragment;
import cn.grass.gate.fragments.HomeFragment;
import cn.grass.gate.http.message.AccountEvent;
import cn.grass.gate.http.message.DataEvent;
import cn.grass.gate.service.HelloteacherService;
import cn.grass.gate.utils.OptionDialogHelper;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        initPush();
        inProcess();
//        initChat();
    }
    //进程函数
     private void inProcess(){
        Intent intent =new Intent(MainActivity.this, HelloteacherService.class);
         startService(intent);
     }


    TextView homeTv,personalTv;
    TextView homeMsgTv,personalMsgTv;
    View currentView = null;
    String currentTag = TAG_HOME;
    private static String TAB_INTENT = "tab";
    private void initView() {
        //检测更新版本
        rootLayout = (FrameLayout) findViewById(R.id.index_content);

        homeTv = (TextView) findViewById(R.id.tv_home);
        personalTv = (TextView) findViewById(R.id.tv_personal);

        homeTv.setOnClickListener(this);
        personalTv.setOnClickListener(this);

        homeMsgTv = (TextView) findViewById(R.id.tv_unread_lawyer);
        personalMsgTv = (TextView) findViewById(R.id.tv_unread_personal);


        initFragment();

        if (getIntent() != null) {
            currentTag = getIntent().getStringExtra(TAB_INTENT);
        }
        showFragmentBy(currentTag);
    }

    FrameLayout rootLayout;

    private HashMap<String, Class<? extends BaseFragment>> fragments = new HashMap<>();
    public final static String TAG_HOME = "home";
    public final static String TAG_PERSONAL = "personal";
    private final static String[] tags = {TAG_HOME,TAG_PERSONAL};

    private void initFragment() {
//        fragments.put(TAG_TAX_MESSAGE, TaxMessageFragment.class);
//        fragments.put(TAG_QUICK_CHECK, QuickCheckFragment.class);
//        fragments.put(TAG_CONSULT, MainFragment.class);
        fragments.put(TAG_HOME, HomeFragment.class);
        fragments.put(TAG_PERSONAL, PersonalFragment.class);
    }

    public void showFragment(String tag) {
        showFragment(tag, null, R.id.index_content, true);
    }

    public void showFragmentBy(String tag) {
        if(TextUtils.isEmpty(tag)){
            tag = TAG_HOME;
        }
        if (currentView != null) {
            currentView.setSelected(false);
        }
       if (tag.equals(TAG_HOME)) {//首页
            homeTv.setSelected(true);
            showFragment(TAG_HOME);
            currentView = homeTv;
        }else if (tag.equals(TAG_PERSONAL)) {//个人
            personalTv.setSelected(true);
            showFragment(TAG_PERSONAL);
            currentView = personalTv;
        }
    }

    private String TAG = "fragment";
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;

    private void showFragment(String tag, Bundle data, int viewContainer,
                              boolean hideBehind) {
        final FragmentManager mFragmentManager = getSupportFragmentManager();
        fragment = mFragmentManager.findFragmentByTag(tag);
        fragmentTransaction = mFragmentManager.beginTransaction();
        if (hideBehind) {
            for (String t : tags) {
                if (!t.equals(tag)) {
                    Fragment oldfragment = mFragmentManager
                            .findFragmentByTag(t);
                    if (oldfragment != null) {
                        fragmentTransaction.hide(oldfragment);
                        LogUtil.show(t + " = hide");
                    }
                }
            }
            // mCurrTag = tag;
        }
        if (fragment == null) {
            LogUtil.show(TAG, "fragment=null");
            Class<? extends Fragment> cl = fragments.get(tag);
            try {
                fragment = cl.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (fragment != null) {
                LogUtil.show(TAG, "new fragment !=null");
                fragment.setArguments(data);
                //
                fragmentTransaction.add(viewContainer, fragment, tag);
            } else {
                LogUtil.show(TAG, "new fragment null");
            }
        } else {
            fragmentTransaction.show(fragment);
            LogUtil.show(TAG,
                    "fragment show isAdd=" + fragment.isAdded() + ",hidden="
                            + fragment.isHidden() + ",tag=" + fragment.getTag());
        }
        if (fragment != null) {
            fragment.setUserVisibleHint(true);
        }
        currentTag = tag;
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void startMainActivity(Context c) {
        Intent it = new Intent(c, MainActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(it);
    }

    private void initPush() {
        GrassApp.getInstance().startPush();
    }

    @Override
    public void onBackPressed() {
        OptionDialogHelper.showExitDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        GrassApp.getInstance().isSilent = false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void AccountEventBus(AccountEvent message) {
        if (message != null) {
            switch (message.eventId) {
                case AccountEvent.TYPE_EXIT_ACCOUNT:
//                    EMClient.getInstance().logout(true);
                    break;
                case AccountEvent.TYPE_LOGIN_MULT_ACCOUNT:
//                    EMClient.getInstance().logout(true);
                    OptionDialogHelper.showExtrusionDialog(this);
                    break;
                case AccountEvent.TYPE_USER_EXIT_APP:
                    finish();
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_home:
                showFragment(TAG_HOME);
                if (currentView != null && currentView != view) {
                    currentView.setSelected(false);
                }
                currentView = view;
                currentView.setSelected(true);
                break;
            case R.id.tv_personal:
                showFragment(TAG_PERSONAL);
                if (currentView != null && currentView != view) {
                    currentView.setSelected(false);
                }
                currentView = view;
                currentView.setSelected(true);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            currentTag = intent.getStringExtra(TAB_INTENT);
        }
        showFragmentBy(currentTag);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void DataEventBus(DataEvent type) {
//        if (type != null) {
//            switch (type.mType) {
//                case DataEvent.TYPE_SHUAXIN_HANGQING:
////                   ToastUtil.shortShow("轮到我干活了");
//                    Log.i("###", "###-->>DataEventBus-->>doFindData");
////                    try {
////                        Thread.sleep(60000);
//
////                        homePresenter.getTicker(selectMarket);
////                        homePresenter.getGateRate();
////                    doFindData();
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//                    break;
//
//            }
//        }
//    }
}

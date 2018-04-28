package cn.grass.gate;

import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.RemoteViews;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.IUmengUnregisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.weedys.weedlibrary.utils.CrashHandler;
import com.weedys.weedlibrary.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePalApplication;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cn.grass.gate.beans.UserInfo;
import cn.grass.gate.data.PrefManager;
import cn.grass.gate.data.UserInfoDb;
import cn.grass.gate.http.message.AccountEvent;
import cn.grass.gate.http.message.DataEvent;
import cn.grass.gate.utils.ActivityManager;
import cn.grass.gate.utils.ToastUtil;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.OkHttpClient;

/**
 * 继承自LitePal
 */
public class GrassApp extends LitePalApplication {
    private ActivityManager activityManager = null;
    public ActivityManager getActivityManager() {
        return activityManager;
    }
    public void setActivityManager(ActivityManager activityManager) {
        this.activityManager = activityManager;
    }
    private static GrassApp mApp;
    private UserInfo userInfo = null;
    public boolean isSilent = true;
    public int role= 0;
    public GrassApp() {
        mApp = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化自定义Activity管理器
        activityManager = ActivityManager.getScreenManager();
        CrashHandler.getInstance().init(this);
        initSize();
        initHttp();
        initUmeng();
//        initEase();
        initJPush();
        initCrashReport();

    }

    private void initCrashReport() {
        CrashReport.initCrashReport(getApplicationContext(), "07b65a2ba1", false);
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        // 调用 Handler 来异步设置别名
        String alias = "";
        if(GrassApp.getInstance().isLogin()){
            String phone = GrassApp.getInstance().getUserInfo().phone;
            if(!TextUtils.isEmpty(phone)){
                alias = phone;
            }
        }
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
            }
//            ExampleUtil.showToast(logs, getApplicationContext());
        }
    };

    public static GrassApp getInstance() {
        if (mApp == null) {
            mApp = new GrassApp();
        }
        return mApp;
    }

    private void initHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15000L, TimeUnit.MILLISECONDS)
                .readTimeout(15000L, TimeUnit.MILLISECONDS)
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    Handler updateHandle = null;

    public Handler getHandler() {
        if (updateHandle == null) {
            updateHandle = new Handler();
        }
        return updateHandle;
    }

    public boolean isLogin() {
        if (userInfo == null) {
            userInfo = getUserInfo();
        }
        if (userInfo != null && !TextUtils.isEmpty(userInfo.token)) {
            return true;
        }
        return false;
    }

    public void setUserInfo(UserInfo u) {
        if (u != null && !TextUtils.isEmpty(u.token)) {
            userInfo = u;
            UserInfoDb.saveUser(u);
        }
    }

//    public void setChatInfo(EaseUser u) {
//        if (u != null ) {
//            easeInfo = u;
//            ChatUserDb.saveChatUser(u);
//        }
//    }

    public UserInfo getUserInfo() {
        if (userInfo != null && !TextUtils.isEmpty(userInfo.token)) {
            return userInfo;
        } else {
            userInfo = UserInfoDb.getUser();
        }
        return userInfo;
    }

    public void saveUserInfo(){
        if(userInfo!=null){
            UserInfoDb.saveUser(userInfo);
        }
    }

    public String getUserId() {
        if (userInfo != null) {
            return userInfo.id;
        }
        return "-1";
    }

    public void logout() {
        userInfo = null;
        UserInfoDb.clearUserInfo();
    }

    private void initSize() {
        ////////////获取相关的尺寸-start////////////
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        if (metrics.widthPixels < metrics.heightPixels) {
            GlobalApp.screenWidth = metrics.widthPixels;
            GlobalApp.screenHeight = metrics.heightPixels;
        } else {
            GlobalApp.screenWidth = metrics.heightPixels;
            GlobalApp.screenHeight = metrics.widthPixels;
        }
        // //////////获取相关的尺寸-end////////////
    }
    private void initUmengShare() {
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx83df49b449f4a01a", "357863c3f1d338923bd67dcc6b3b3f94");
        PlatformConfig.setQQZone("1105780830", "357863c3f1d338923bd67dcc6b3b3f94");
        //重定向url暂时写下载页
        PlatformConfig.setSinaWeibo("2196888559", "af5453dcbe59eb71758f1edb58966e87", "http://app.mi.com/details?id=cn.grasscloud.jiaxiao&ref=search");
    }
    private PushAgent mPushAgent;

    public void initUmeng() {
        initUmengShare();
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setDebugMode(true);
        int p = PrefManager.getPrefInt(GlobalApp.PRE_PUSH_ENABLE, 1);
        if (p == 1) {
            mPushAgent.enable();
        }
        if (PrefManager.getPrefInt(GlobalApp.PRE_AUDIO_ENABLE, 1) == 1) {
            mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
            mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);//呼吸灯

        }
        if (PrefManager.getPrefInt(GlobalApp.PRE_VIBI_ENABLE, 1) == 1) {
            mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);//振动
        }

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 参考集成文档的1.6.3
             * http://dev.umeng.com/push/android/integration#1_6_3
             * */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                LogUtil.show("自定义:" + msg.custom);
                new Handler().post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
                        Map<String, String> extra = msg.extra;
                        int type = Integer.valueOf(extra.get("type"));
                        if (type == -1) {

                            GrassApp.getInstance().setUserInfo(null);
                            UserInfoDb.clearUserInfo();
                            ToastUtil.shortShow("账户被挤出!");
                            LogUtil.show("账户被挤出");
//                            OptionDialogHelper.showOption(context);
                            EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_LOGIN_MULT_ACCOUNT));
                        }
//                         Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                     //   LogUtil.show("自定义:" + msg.custom);
                    }
                });

            }

            /**
             * 参考集成文档的1.6.4
             * http://dev.umeng.com/push/android/integration#1_6_4
             * */
            @Override
            public Notification getNotification(Context context,
                                                UMessage msg) {
                switch (msg.builder_id) {
                    case 1:
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);
                        LogUtil.show("消息:展示");

                        return builder.build();

                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }

            @Override
            public void dealWithNotificationMessage(Context context, UMessage uMessage) {
                super.dealWithNotificationMessage(context, uMessage);
                LogUtil.show("处理" + uMessage.custom);
                EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_NEW_FRIEND_NUM));
            }

        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 该Handler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * 参考集成文档的1.6.2
         * http://dev.umeng.com/push/android/integration#1_6_2
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                //   Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                LogUtil.show("点击了" + msg.custom);
            }

            @Override
            public void openActivity(Context context, UMessage uMessage) {
                super.openActivity(context, uMessage);
                Map<String, String> map = uMessage.extra;
                int type = Integer.valueOf(map.get("type").toString());
                String content = map.get("contentid");
                String roleCodeStr=map.get("roleCode");
                if(!GrassApp.getInstance().isLogin()){

                    return;
                }
//                if(GrassApp.getInstance().getUserInfo().getCurrentUserRole()==null){
//                    SwitchRoleActivity.startSwitchRoleActivity(context);
//                    return;
//                }

//                if(!TextUtils.isEmpty(roleCodeStr)){
//                    int roleCode=Integer.valueOf(roleCodeStr);
//                    int currentCode=GrassApp.getInstance().getUserInfo().getCurrentUserRole().roleCode;
//                    String roleTip="家长子账号";
//                    switch (roleCode){
//                        case RoleInfo.ROLE_PARENT:
//                            roleTip="家长子账号";
//                            break;
//                        case RoleInfo.ROLE_WORKER:
//                            roleTip="教职工子账号";
//                            break;
//                        case RoleInfo.ROLE_MANAGER:
//                            roleTip="学校管理子账号";
//                            break;
//                        case RoleInfo.ROLE_TEACHER:
//                            roleTip="老师子账号";
//                            break;
//                        default:
//                            roleTip="家长子账号";
//                            break;
//                    }
//                    if(roleCode!=currentCode){
//                        ToastUtil.shortShow("请切换到 "+roleTip+" 去查看");
//                        return;
//                    }
//                }
//                if (type == 1) {
//                    switchNotice(context,content);
//                } else if (type == 2) {
//
//                    NoticeDetailActivity.startNoticeDetailActivity(context, content);
//                }else if (type == 3) {
//                    NewInvitationActivity.startNewInvitationActivity2(context);
//                }
                LogUtil.show("打开页面" + type + content);
            }

            @Override
            public void openUrl(Context context, UMessage uMessage) {
                super.openUrl(context, uMessage);
                String custom = uMessage.custom;
                LogUtil.show("打开网页" + custom);
            }


        };
        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知
        //参考http://bbs.umeng.com/thread-11112-1-1.html
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

    }



//    private void switchNotice(Context c,String content){
//        int roleCode=GrassApp.getInstance().getUserInfo().getCurrentUserRole().roleCode;
//        switch (roleCode){
//            case RoleInfo.ROLE_PARENT:
//                NoticeDetailActivity.startNoticeDetailActivity(c,content);
//                break;
//            case RoleInfo.ROLE_WORKER:
//                NoticeDetailActivity.startNoticeDetailActivity(c,content);
//                break;
//            case RoleInfo.ROLE_MANAGER:
//                ManageMainActivity.startManagerActivity(c);
//                break;
//            case RoleInfo.ROLE_TEACHER:
//                NoticeDetailActivity.startNoticeDetailActivity(c,content);
//                break;
//            default:
//                NoticeDetailActivity.startNoticeDetailActivity(c,content);
//                break;
//        }
//    }
    public void endPush(){
        if(mPushAgent==null){
            mPushAgent = PushAgent.getInstance(this);
        }
        if(mPushAgent!=null){
            mPushAgent.disable(new IUmengUnregisterCallback() {
                @Override
                public void onUnregistered(String s) {
                    LogUtil.show("注销推送"+s);

                }
            });
        }
    }
    public void enablePush(){

        if(mPushAgent==null){
            mPushAgent = PushAgent.getInstance(this);
        }
        if(mPushAgent!=null){
            mPushAgent.enable(new IUmengRegisterCallback() {
                @Override
                public void onRegistered(String s) {
                    LogUtil.show("注册推送成功！"+s);
                }
            });
        }
        mPushAgent.enable();
        mPushAgent.setDebugMode(true);
        mPushAgent.onAppStart();
        // TODO: 2017/7/25  注册推送
//        if (GrassApp.getInstance().isLogin()) {
//
//            String phone = GrassApp.getInstance().getUserInfo().phone;
//            String uid = GrassApp.getInstance().getUserInfo().id;
//            String childid = "";
//
//            mPushAgent.setAlias(phone, ALIAS_TYPE.QQ);
//            mPushAgent.setAlias(GrassApp.getInstance().getUserInfo().token, "token");
//            mPushAgent.setAlias(phone, "phone");
//            mPushAgent.setAlias(uid, "userid");
//            mPushAgent.setAlias(childid, "childid");
//            LogUtil.show("孩子ID"+childid);
//            LogUtil.show("用户ID"+uid);
//            mPushAgent.setRegisterCallback(new IUmengRegisterCallback() {
//                @Override
//                public void onRegistered(String s) {
////                ToastUtil.show("注册");
//                    LogUtil.show("注册成功" + s);
//                }
//            });
//            //极光推送
//            // 调用 JPush 接口来设置别名。
//            JPushInterface.setAliasAndTags(getApplicationContext(),
//                    phone, null,mAliasCallback);
//        }
    }
    public void startPush() {
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.disable(new IUmengUnregisterCallback() {
            @Override
            public void onUnregistered(String s) {
                LogUtil.show("注销推送"+s);
                enablePush();
            }
        });
    }

//    private void initEase() {
//        ChatHelper.getInstance().init(getApplicationContext());
//    }

}

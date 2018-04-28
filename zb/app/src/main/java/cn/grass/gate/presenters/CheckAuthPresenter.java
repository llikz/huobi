package cn.grass.gate.presenters;

import android.os.Handler;
import android.os.Message;

//import com.hyphenate.EMCallBack;
//import com.hyphenate.chat.EMClient;
import com.weedys.weedlibrary.http.RequestCallBack;
import com.weedys.weedlibrary.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

import cn.grass.gate.GrassApp;
import cn.grass.gate.http.Api;
import cn.grass.gate.http.HttpManager;
import cn.grass.gate.views.CheckAuthView;

//import cn.grasscloud.jiaxiao.GlobalApp;
//import cn.grasscloud.jiaxiao.GrassApp;
//import cn.grasscloud.jiaxiao.beans.UserInfo;
//import cn.grasscloud.jiaxiao.data.PrefManager;
//import cn.grasscloud.jiaxiao.http.Api;
//import cn.grasscloud.jiaxiao.http.HttpManager;
//import cn.grasscloud.jiaxiao.views.CheckAuthView;

/**
 * Created by weedys on 16/8/4.
 */
public class CheckAuthPresenter {
    private CheckAuthView view;

    public CheckAuthPresenter(CheckAuthView v) {
        view = v;
        if (GrassApp.getInstance().isLogin()) {
            token = GrassApp.getInstance().getUserInfo().token;
        }
    }

    public int TOKEN_SEND = 0x09;

//    public void sendCode(int type, String phone) {
//        Map<String, String> map = new HashMap<>();
//        map.put("type", "" + type);
//        map.put("phone", phone);
//        HttpManager.getInstance().postRequest(GrassApp.getInstance().getApplicationContext(), Api.URL_GET_CODE, map, new RequestCallBack() {
//
//            @Override
//            public void onSuccess(int callId, String content, String msg) {
//                view.onSuccess(callId, content, msg);
//            }
//
//            @Override
//            public void onFail(int callId, int errorId, String msg) {
//                view.onFail(callId, msg, errorId);
//            }
//        }, TOKEN_SEND);
//    }

    public int TOKEN_REGEDIT = 0x10;

//    public void regedit(String phone, String pwd, String code) {
//        Map<String, String> map = new HashMap<>();
//        pwd = CommonUtils.MD5(pwd);
//        map.put("passpwd", pwd);
//        map.put("uname", phone);
//        map.put("code", code);
//        HttpManager.getInstance().postRequest(GrassApp.getInstance().getApplicationContext(), Api.URL_REGEDIT, map, new RequestCallBack() {
//
//            @Override
//            public void onSuccess(int callId, String content, String msg) {
//                try {
//                    JSONObject json = new JSONObject(content);
//                    if (json != null) {
//                        UserInfo u = new UserInfo();
//                        u.uname = json.optString("uname", "");
//                        u.avatar = json.optString("avatar", "");
//                        u.phone = json.optString("phone", "");
//                        u.uid = json.optString("uid", "");
//                        u.token = json.optString("token", "");
//                        u.isbind = json.optInt("avatar", 0);
//                        u.signature = json.optString("signature", "");
//                        u.account = json.optString("account", "");
//                        u.pwd = json.optString("password", "");
//                        u.avatar = json.optString("avatar", "");
//
////                        JSONObject obj = json.optJSONObject("child");
////                        if (obj != null) {
////                            String sid = obj.optString("sid");
////                            String childName = obj.optString("childName");
////                            String className = obj.optString("className");
////                            int relation = obj.optInt("relation");
////                            ChildInfo child = new ChildInfo();
////                            child.childName = childName;
////                            child.uid = sid;
////                            child.childId = sid;
////                            u.role = relation;
////                            child.relation = relation;
////                            child.childClassName = className;
////                            u.setChildInfo(obj.toString());
////                            u.childInfo = child;
////                        }
//
//                        GrassApp.getInstance().setUserInfo(u);
//                        LoginChat(u.account, u.pwd);
//                        PrefManager.setPrefString(GlobalApp.PRE_USER_LOGIN_UNAME, u.phone);
//                    }
//                    view.onSuccess(callId, content, msg);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    view.onFail(callId, "解析异常", -1);
//                    return;
//                }
//            }
//
//            @Override
//            public void onFail(int callId, int errorId, String msg) {
//                view.onFail(callId, msg, errorId);
//            }
//        }, TOKEN_REGEDIT);
//    }

    public String TAG = "Loginactivity";

//    public void LoginChat(String currentUsername, String currentPassword) {
//        EMClient.getInstance().login(currentUsername, currentPassword, new EMCallBack() {
//
//            @Override
//            public void onSuccess() {
//                Log.d(TAG, "login: onSuccess");
//
//                // ** manually load all local groups and conversation
//                EMClient.getInstance().groupManager().loadAllGroups();
//                EMClient.getInstance().chatManager().loadAllConversations();
//
//                // update current user's display name for APNs
////                boolean updatenick = EMClient.getInstance().updateCurrentUserNick(
////                        DemoApplication.currentUserNick.trim());
////                if (!updatenick) {
////                    Log.e("LoginActivity", "update current user nick fail");
////                }
//
//                // get user's info (this should be get from App's server or 3rd party service)
////                ChatHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
//                update.sendEmptyMessage(1);
//
//            }
//
//            @Override
//            public void onProgress(int progress, String status) {
//                Log.d(TAG, "login: onProgress");
//            }
//
//            @Override
//            public void onError(final int code, final String message) {
//                Log.d(TAG, "login: onError: " + code);
////                ToastUtil.show("失败"+message);
//                update.sendEmptyMessage(0);
//            }
//        });
//
//
//    }

    private String token;
    public int TOKEN_SETPHONE = 0x11;

//    public void setPhone(final String phone, String pwd, String code) {
//        Map<String, String> map = new HashMap<>();
//        map.put("newPhone", phone);
//        map.put("code", code);
//        map.put("password", CommonUtils.MD5(pwd));
////        map.put("token",token);
//        HttpManager.getInstance().postRequest(GrassApp.getInstance().getApplicationContext(), Api.URL_CHANGE_PHONE, map, new RequestCallBack() {
//
//            @Override
//            public void onSuccess(int callId, String content, String msg) {
//                if (GrassApp.getInstance().isLogin()) {
//                    UserInfo u = GrassApp.getInstance().getUserInfo();
//                    u.phone = phone;
//                    GrassApp.getInstance().setUserInfo(u);
//                }
//                view.onSuccess(callId, content, msg);
//            }
//
//            @Override
//            public void onFail(int callId, int errorId, String msg) {
//                view.onFail(callId, msg, errorId);
//            }
//        }, TOKEN_SETPHONE);
//    }


    public int TOKEN_FORGET_PWD = 0x16;

    public void forgetPwd(String phone, String pwd, String code) {
        Map<String, String> map = new HashMap<>();
        pwd = CommonUtils.MD5(pwd);
        map.put("userName", phone);
        map.put("password", pwd);
        map.put("code", code);
        HttpManager.getInstance().postRequest(GrassApp.getInstance().getApplicationContext(), Api.URL_FORGET_PWD, map, new RequestCallBack() {

            @Override
            public void onSuccess(int callId, String content, String msg) {
                try {

                    view.onSuccess(callId, content, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    view.onFail(callId, "解析异常", -1);
                    return;
                }
            }

            @Override
            public void onFail(int callId, int errorId, String msg) {
                view.onFail(callId, msg, errorId);
            }
        }, TOKEN_FORGET_PWD);
    }

    public int TOKEN_CHANGE_PWD = 0x17;

//    public void changePwd(String newpwd, String old) {
//        Map<String, String> map = new HashMap<>();
//        old = CommonUtils.MD5(old);
//        newpwd = CommonUtils.MD5(newpwd);
//        map.put("oldpwd", old);
//        map.put("newpwd", newpwd);
////        map.put("token",token);
//        HttpManager.getInstance().postRequest(GrassApp.getInstance().getApplicationContext(), Api.URL_CHANGE_PWD, map, new RequestCallBack() {
//
//            @Override
//            public void onSuccess(int callId, String content, String msg) {
//                try {
//                    view.onSuccess(callId, content, msg);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    view.onFail(callId, "解析异常", -1);
//                    return;
//                }
//            }
//
//            @Override
//            public void onFail(int callId, int errorId, String msg) {
//                view.onFail(callId, msg, errorId);
//            }
//        }, TOKEN_CHANGE_PWD);
//    }

    public Handler update = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
}

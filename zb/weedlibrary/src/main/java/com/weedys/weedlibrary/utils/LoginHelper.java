package com.weedys.weedlibrary.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.AccessTokenKeeper;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.weedys.weedlibrary.beans.LoginInfo;
import com.weedys.weedlibrary.qq.AppConstants;
import com.weedys.weedlibrary.wbo.WeiboContact;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by weedys on 16/11/18.
 */
public class LoginHelper{
    private UserInfo mInfo;
    public static Tencent mTencent;
    private static LoginHelper helper;
    private Context context;
    private boolean isServerSideLogin = false;
    private LoginInfo user;
    public LoginHelper(Context c){
        helper = this;
        context = c;

    }
    public static LoginHelper instance(Context c){

        if(helper==null){
            helper = new LoginHelper(c);
        }
        String mAppid= AppConstants.APP_ID;
        if (mTencent == null) {
            mTencent = Tencent.createInstance(mAppid,c);
        }
        initWeibo(c);
        initWx(c);
        return helper;
    }
    LoginListener ll = null;
    public void QQLogin(LoginListener l){

        ll=l;
        if(mTencent==null){
            if (ll != null) {
                ll.onLoginFail();

            }
            return;
        }
        if(!mTencent.isSupportSSOLogin((Activity) context)){
            ToastUtil.show(context,"您没有安装最新版本QQ，请先下载并安装");
            if (ll != null) {
                ll.onLoginFail();

            }
           return;
        }
        if (!mTencent.isSessionValid()) {
            mTencent.login((Activity)context, "all", loginListener);
            isServerSideLogin = false;
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(context);
                mTencent.login((Activity) context, "all", loginListener);
                isServerSideLogin = false;
                return;
            }
            mTencent.logout(context);
         //   updateUserInfo();
        }
    }
    public void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.optString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.optString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.optString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
            if (ll != null) {
                ll.onLoginFail();
            }
        }
    }
    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            initOpenidAndToken(values);
//            ToastUtil.show(context,values.toString());
            getUserInfo();
        }
    };
    IUiListener getUserListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            if(values==null){
                ToastUtil.shortShow(context,"授权失败");
                if (ll != null) {
                    ll.onLoginFail();
                }
                return;
            }
            user = new LoginInfo();
            String avator = null;
            if(values.has("figureurl")) {
                avator=values.optString("figureurl");
                if(TextUtils.isEmpty(avator)){
                    avator=values.optString("figureurl_qq_2");
                }
            }
            LogUtil.show("userinfo:"+values.toString());
            user.openId=mTencent.getOpenId();
            user.avator = avator;
            user.type = 0;
            user.unike = values.optString("nickname");
            if(ll!=null){
                ll.onLoginSuccess(user);
            }
            //ToastUtil.show(context,values.toString());
        }
    };

    public static boolean ready(Context context) {
        if (mTencent == null) {
            return false;
        }
        if(!mTencent.isSupportSSOLogin((Activity)context)){
            return false;
        }
        boolean ready = mTencent.isSessionValid()
                && mTencent.getQQToken().getOpenId() != null;
        if (!ready) {
            Toast.makeText(context, "login and get openId first, please!",
                    Toast.LENGTH_SHORT).show();
        }
        return ready;
    }
    private void getUserInfo() {
        if (context != null && mTencent != null) {
            mInfo = new UserInfo(context, mTencent.getQQToken());
        }
        if (LoginHelper.ready(context)) {
            mInfo.getUserInfo(getUserListener);
        }else {
            if (ll != null) {
                ll.onLoginFail();
            }
        }

    }
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            LogUtil.show("title:"+response.toString());
            if (null == response) {
                ToastUtil.shortShow(context,"授权失败");
                if(ll!=null){
                    ll.onLoginFail();
                }
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                ToastUtil.shortShow(context,"授权失败");
                if(ll!=null){
                    ll.onLoginFail();
                }
                return;
            }
            ToastUtil.shortShow(context,"授权成功");
            doComplete((JSONObject)response);
        }
        protected void doComplete(JSONObject values) {

        }
        @Override
        public void onError(UiError e) {
            ToastUtil.shortShow(context,"登录失败,"+e.errorDetail);
            if(ll!=null){
                ll.onLoginFail();
            }
        }
        @Override
        public void onCancel() {
            LogUtil.show("取消登录");
            if(ll!=null){
                ll.onLoginFail();
            }
        }
    }
    //微博登录
   static AuthInfo weiboAuth = null;
   static SsoHandler mSsoHandler  =null;
    public static void initWeibo(Context c){
        if(weiboAuth==null)
        weiboAuth = new AuthInfo(c, WeiboContact.APP_KEY,WeiboContact.REDIRECT_URL,WeiboContact.SCOPE);
        if(mSsoHandler==null)
        mSsoHandler = new SsoHandler((Activity)c,weiboAuth);
    }
    //微博登陆
    public void LoginWeibo(LoginListener l){
        ll = l;
        mSsoHandler.authorize(new WeiboAuthListener() {
            @Override
            public void onComplete(Bundle bundle) {
                Oauth2AccessToken  mAccessToken = Oauth2AccessToken.parseAccessToken(bundle); // 从 Bundle 中解析 Token
                if (mAccessToken.isSessionValid()) {
                    AccessTokenKeeper.writeAccessToken(context, mAccessToken); //保存Token
                    RequestListener mListener = new RequestListener() {
                        @Override
                        public void onComplete(String response) {
                            if (!TextUtils.isEmpty(response)) {
                                // 调用 User#parse 将JSON串解析成User对象 User user = User.parse(response);

                            }
                            LogUtil.show("微博："+response);
//                            ToastUtil.show(context,response);
                            LoginInfo info = null;
                            if(!TextUtils.isEmpty(response)){
                                info = new LoginInfo();
                                try {
                                    JSONObject resJosn=new JSONObject(response);
                                    info.openId=resJosn.optString("idstr");
                                    info.unike = resJosn.optString("name");
                                    info.avator = resJosn.optString("profile_image_url");
                                    info.sex =resJosn.optString("gender").equals("m")?1:0;
                                    info.type =2;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(ll!=null && info!=null){
                                ll.onLoginSuccess(info);
                            }
                        }

                        @Override
                        public void onWeiboException(WeiboException e) {
                            if(ll!=null){
                                ll.onLoginFail();
                            }
                        }
                    };
                   long uid = Long.parseLong(mAccessToken.getUid());
                    UsersAPI mUsersAPI=new UsersAPI(context,WeiboContact.APP_KEY,mAccessToken);
                    mUsersAPI.show(uid, mListener);


                } else {
// 当您注册的应用程序签名不正确时，就会收到错误Code，请确保签名正确
                    String code = bundle.getString("code", "-911");
                    if(ll!=null){
                        ll.onLoginFail();
                    }
                }
            }

            @Override
            public void onWeiboException(WeiboException e) {
                if(ll!=null){
                    ll.onLoginFail();
                }
            }

            @Override
            public void onCancel() {
                if(ll!=null){
                    ll.onLoginFail();
                }
            }
        });
    }
    public void onWeiboResult(int requestCode, int resultCode, Intent data){
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
    private static void initWx(Context c){
        api = WXAPIFactory.createWXAPI(c, AppConstants.WX_APP_ID, false);
    }
    private static IWXAPI api;
    //微信登录
    public void LoginWx(LoginListener l){
        ll=l;
        api.registerApp(AppConstants.WX_APP_ID);
        if (api.isWXAppInstalled()) {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_grasscloud";

            WXEntryReceiver wxEntryReceiver= new WXEntryReceiver();
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
            IntentFilter filter = new IntentFilter();
            filter.addAction(AppConstants.ACTION_LOGIN_WX);
            lbm.registerReceiver(wxEntryReceiver,filter);
            api.sendReq(req);

        }else{
            ToastUtil.shortShow(context,"没有安装微信客户端！");
            if(l!=null){
                l.onLoginFail();
            }
        }
    }




    /**
     * 通过code获取accessToken
     *
     * @param code
     */
    public void getAccessToken(String code) {
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" + "appid=" + AppConstants.WX_APP_ID + "&secret=" + AppConstants.WX_APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if(ll!=null){
                    ll.onLoginFail();
                }
            }

            @Override
            public void onResponse(String response, int id) {

                if(TextUtils.isEmpty(response)){
                    if(ll!=null){
                        ll.onLoginFail();
                    }
                    return;
                }
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String accessToken = jsonObj.optString("access_token");
                    String openId = jsonObj.optString("openid");
                    if (!TextUtils.isEmpty(accessToken)
                            && !TextUtils.isEmpty(openId)) {
                        // 获取到openId和accessToken后，获取用户的微信个人信息
                        getWechatUserInfo(accessToken, openId);
                    } else {
                        if(ll!=null){
                            ll.onLoginFail();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(ll!=null){
                        ll.onLoginFail();
                    }
                }
            }
        });

    }

    /**
     * 获取微信用户信息
     *
     * @param
     */
    public void getWechatUserInfo(String accessToken, String openId) {
        String url="https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId;
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if(ll!=null){
                    ll.onLoginFail();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                if(TextUtils.isEmpty(response)){
                    if(ll!=null){
                        ll.onLoginFail();
                    }
                    return;
                }
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String openId = jsonObj.optString("openid");
                    String userName = jsonObj.optString("nickname");
                    if (!TextUtils.isEmpty(openId)
                            && !TextUtils.isEmpty(userName)) {
                        String sex = jsonObj.optInt("sex") == 1 ? "男"
                                : "女";
                        LoginInfo u = new LoginInfo();
                        u.openId=openId;
                        u.unike = userName;
                        u.sex=jsonObj.optInt("sex");
                        u.avator=jsonObj.optString("headimgurl");
                        u.type=1;
                        if(ll!=null)ll.onLoginSuccess(u);
                    } else {
                        if(ll!=null)ll.onLoginFail();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(ll!=null)ll.onLoginFail();
                }
            }
        });
    }

    public interface LoginListener{
        void onLoginSuccess(LoginInfo info);
        void onLoginFail();
    }
    private class WXEntryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            LogUtil.show("WXEntryReceiver", "接收微信登陆广播");

            if(intent.getAction().equals(AppConstants.ACTION_LOGIN_WX)){
                int errCode = intent.getExtras().getInt("errCode");
                    System.out.println("获取错误码："+errCode);
                if(errCode==BaseResp.ErrCode.ERR_USER_CANCEL||errCode==BaseResp.ErrCode.ERR_AUTH_DENIED){
                    if(ll!=null)ll.onLoginFail();
                }else{
                    String code = intent.getExtras().getString("code");
                    getAccessToken(code);

                }
            }
        }
    }
}

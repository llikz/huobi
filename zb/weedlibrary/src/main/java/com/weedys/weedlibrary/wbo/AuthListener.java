package com.weedys.weedlibrary.wbo;

import android.os.Bundle;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * Created by weedys on 16/11/25.
 */
public class AuthListener implements WeiboAuthListener {
    public AuthListener(){}
    Oauth2AccessToken mAccessToken =null;
    @Override
    public void onComplete(Bundle values) {
    mAccessToken = Oauth2AccessToken.parseAccessToken(values); // 从 Bundle 中解析 Token
    if (mAccessToken.isSessionValid()) {

//        AccessTokenKeeper.writeAccessToken(WBAuthActivity.this, mAccessToken); //保存Token

    } else {
// 当您注册的应用程序签名不正确时，就会收到错误Code，请确保签名正确
        String code = values.getString("code", "-911");
    } }

    @Override
    public void onWeiboException(WeiboException e) {

    }

    @Override
    public void onCancel() {

    }
}

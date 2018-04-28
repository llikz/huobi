package com.weedys.weedlibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by weedys on 16/7/22.
 */
public class NetWorkUtils {
    private final static String TAG="wtnetwork";
    private static NET_TYPE mNetType = NET_TYPE.NONE;
    public static enum NET_TYPE {
        NONE, WIFI, MOBILE
    }
    public static NET_TYPE checkNetWork(Context context) {
        LogUtil.show(TAG, "checkNetWork");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetInfo != null) {
            if (!activeNetInfo.isAvailable()) {
                mNetType = NET_TYPE.NONE;
            } else {
                int type = activeNetInfo.getType();
                if (type == ConnectivityManager.TYPE_WIFI)
                    mNetType = NET_TYPE.WIFI;
                else {
                    mNetType = NET_TYPE.MOBILE;
                }
            }
        } else {
            mNetType = NET_TYPE.NONE;

        }
        return mNetType;

    }
    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public static NET_TYPE getNetType() {
        return mNetType;
    }

//    public static boolean isNetConnected() {
//        return mNetType != NET_TYPE.NONE;
//    }
}

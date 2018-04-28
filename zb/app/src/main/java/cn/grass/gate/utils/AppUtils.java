package cn.grass.gate.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.weedys.weedlibrary.utils.FileUtil;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cn.grass.gate.GrassApp;
import cn.grass.gate.http.Api;

/**
 * Created by weedys on 16/7/22.
 */
public class AppUtils {
    public static String getDeviceId() {
        String imei = ((TelephonyManager) GrassApp.getInstance().getSystemService(
                Activity.TELEPHONY_SERVICE)).getDeviceId();
        if (!TextUtils.isEmpty(imei)) {
            WifiManager wifiManager = (WifiManager) GrassApp.getInstance().getSystemService(Context.WIFI_SERVICE);
            imei = wifiManager.getConnectionInfo().getMacAddress();
        }
        if (!TextUtils.isEmpty(imei)) {
            imei = FileUtil.getLocalDeviceIdUUID();
        }
        return imei;
    }


    /**
     * 获取三段版本号
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String channelName = getApplicationMetaValue(context, "UMENG_CHANNEL");
        return getBaseAppVersionName(context) + "." + channelName;

    }

    /**
     * 获取app基础版本号，对应配置文件的versionName字段
     *
     * @return
     */
    public static String getBaseAppVersionName(Context context) {
        String version = "1.0";
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);

            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    /**
     * 获取app基础版本号，对应配置文件的versionCode字段
     *
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int version = 2014;
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);

            version = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    /***
     * 获取ip地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        String ipAddress = null;
        try {
            List<NetworkInterface> interfaces = Collections
                    .list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface iface : interfaces) {
                if (iface.getDisplayName().equals("eth0")) {
                    List<InetAddress> addresses = Collections.list(iface
                            .getInetAddresses());
                    for (InetAddress address : addresses) {
                        if (address instanceof Inet4Address) {
                            ipAddress = address.getHostAddress();
                        }
                    }
                } else if (iface.getDisplayName().equals("wlan0")) {
                    List<InetAddress> addresses = Collections.list(iface
                            .getInetAddresses());
                    for (InetAddress address : addresses) {
                        if (address instanceof Inet4Address) {
                            ipAddress = address.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipAddress;
    }

    /**
     * 获取Manifest文件中的meta信息
     */
    public static String getApplicationMetaValue(Context context,
                                                 String metaName) {
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            // return appInfo.metaData.getString(metaName);
            return appInfo.metaData.get(metaName) + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (serviceList == null || serviceList.isEmpty())
            return false;
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) && TextUtils.equals(
                    serviceList.get(i).service.getPackageName(), context.getPackageName())) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

//    public boolean mayRequestContacts(final Context c) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            return true;
//        }
//        if (c.checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        }
//        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
//            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
//                    .setAction(android.R.string.ok, new View.OnClickListener() {
//                        @Override
//                        @TargetApi(Build.VERSION_CODES.M)
//                        public void onClick(View v) {
//                            c.requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//                        }
//                    });
//        } else {
//            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//        }
//        return false;
//    }

    public static void download(Context c, String apk) {
        Intent it = new Intent(Intent.ACTION_VIEW);
        String apkUrl = Api.HOST + apk;
        it.setData(Uri.parse(apkUrl));
        c.startActivity(it);
    }

    public static ArrayList<HashMap<String,String>> getSubjectList(){
        return null;
    }

}

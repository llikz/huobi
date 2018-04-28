package cn.grass.gate.http;//package com.weedys.weedlibrary.http;
//

import android.content.Context;
import android.text.TextUtils;

import com.weedys.weedlibrary.http.RequestCallBack;
import com.weedys.weedlibrary.http.ResultCode;
import com.weedys.weedlibrary.utils.CommonUtils;
import com.weedys.weedlibrary.utils.LogUtil;
import com.weedys.weedlibrary.utils.NetWorkUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.File;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import cn.grass.gate.GrassApp;
import cn.grass.gate.http.message.AccountEvent;
import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by weedys on 16/7/20.
 */
public class HttpManager {
    public static HttpManager manager;
    public String KEY_RET = "status";
    public String KEY_MSG = "message";
    public String KEY_DATA = "data";

    public HttpManager() {
        manager = this;
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
    }

    public static HttpManager getInstance() {

        if (manager == null) {
            manager = new HttpManager();
        }
        return manager;
    }

    private OkHttpClient okHttpClient;

    public void init() {
    }

    public void postRequest(final Context c, String url, Map<String, String> map, final RequestCallBack callback, final int callId) {
        if (!NetWorkUtils.isConnected(c)) {
            if (callback != null)
                callback.onFail(callId, ResultCode.RESULT_OFFLINE, "无网络");
            LogUtil.show("离线");
            return;
        }
        url = getDefaultParam(c, url);
        String testurl = url;
        String str = "";
//        第一种：普遍使用，二次取值
        if(true){
            for (String key : map.keySet()) {
                str +="&"+ key + "=" + map.get(key);
            }
            LogUtil.show("postRequest testurl:" + testurl + str);
        }
        OkHttpUtils.post()
                .url(url)
                .params(map)
                .id(callId)
                .tag("post")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        filterError(callback, e, ResultCode.RESULT_FAILD, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        filterData(c, response, callback, id);
                    }
                });

    }

    public void getRequest(final Context c, String url, Map<String, String> map, final RequestCallBack callback, final int callId) {
        if (!NetWorkUtils.isConnected(c)) {
            if (callback != null)
                callback.onFail(callId, ResultCode.RESULT_OFFLINE, "无网络");
            LogUtil.show("离线");
            return;
        }
        url = getDefaultParam(c, url);
        LogUtil.show("getRequest url:", url);
        final RequestCall builder = OkHttpUtils.get()
                .id(callId)
                .url(url)
                .params(map)
                .tag("get")
                .build();

        builder.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                int code = 0;
                filterError(callback, e, ResultCode.RESULT_FAILD, id);
            }

            @Override
            public void onResponse(String response, int id) {
                filterData(c, response, callback, id);
            }
        });

        LogUtil.show("request:" + builder.getRequest() == null ? "null" : builder.getRequest().toString());

    }
    public void getRequestForGson(final Context c, String url, Map<String, String> map, final RequestCallBack callback, final int callId) {
        if (!NetWorkUtils.isConnected(c)) {
            if (callback != null)
                callback.onFail(callId, ResultCode.RESULT_OFFLINE, "无网络");
            LogUtil.show("离线");
            return;
        }
        url = getDefaultParam(c, url);
        LogUtil.show("getRequestFromGson url:", url);
        final RequestCall builder = OkHttpUtils.get()
                .id(callId)
                .url(url)
                .params(map)
                .tag("get")
                .build();

        builder.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                int code = 0;
                filterError(callback, e, ResultCode.RESULT_FAILD, id);
            }

            @Override
            public void onResponse(String response, int id) {
                filterDataForGson(response, callback, callId);
            }
        });

        LogUtil.show("requestFromGson:" + builder.getRequest() == null ? "null" : builder.getRequest().toString());

    }

    private void filterDataForGson(String response, RequestCallBack callback, int callId) {
        if (callback == null) {
            return;
        }
        if (!TextUtils.isEmpty(response)) {
            LogUtil.show(response);
            try {
                JSONObject obj = new JSONObject(response);
//                int ret = obj.optInt(KEY_RET, -1);
                String msg = obj.optString(KEY_MSG);
//                        JSONObject data = obj.optJSONObject(KEY_DATA);
//                if (ret == ResultCode.RESULT_OK) {
                    callback.onSuccess(callId, response, msg);//跟之前不同的地方.直接用返回的json解析
//                            if (data != null) {
//                                callback.onSuccess(callId, data,toString(), msg);
//                            } else {
//                                callback.onSuccess(callId, "", msg);
//                            }
//                } else {
//                    if (ret == ResultCode.RESULT_USER_NOLOGIN) {
//                        GrassApp.getInstance().logout();
//                        EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_LOGIN_MULT_ACCOUNT));
//                        return;
//                    }
//                    callback.onFail(callId, ret, msg);
//                }
            } catch (Exception e) {
                e.printStackTrace();
                callback.onFail(callId, ResultCode.RESULT_SERVER_ERROR, "服务器出故障了，返回格式错误" + e.getMessage());
                LogUtil.show("JSON格式错误:" + response);
            }
        }
    }

    //    private void postData(Context c, final String url, final JSONObject param){
//        OkHttpUtils.postString().url(url)
//                .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .content(param.toString())
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//
//            }
//        });
//    }
    public void MultPost(final Context c, String url, HashMap<String, String> map, final HashMap<String, String> fileParam, final RequestCallBack callBack, int callId) {
        if (!NetWorkUtils.isConnected(c)) {
            if (callBack != null)
                callBack.onFail(callId, ResultCode.RESULT_OFFLINE, "无网络");
            return;
        }
        url = getDefaultParam(c, url);
        String testurl = url;
        String str = "";
        //第一种：普遍使用，二次取值
//        if(LogUtil.ishow()){
//            for (String key : map.keySet()) {
//                str +="&"+ key + "=" + map.get(key);
//            }
//            LogUtil.show("MultPost testurl:" + testurl + str);
//        }
        PostFormBuilder orb = OkHttpUtils.post().url(url)
                .params(map)
                .id(callId)
                .tag("file");
        if (fileParam != null) {
            Iterator<String> keys = fileParam.keySet().iterator();
            while (keys != null && keys.hasNext()) {
                String key = keys.next();
                String val = fileParam.get(key);
                File fs = new File(val);
                if (fs.exists()) {
                    orb.addFile(key, "png", fs);
                }
            }
        }
        orb.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                filterError(callBack, e, ResultCode.RESULT_FAILD, id);
            }

            @Override
            public void onResponse(String response, int id) {
                filterData(c, response, callBack, id);
            }
        });

    }

    public void upload(final Context c, String url, HashMap<String, String> files, final RequestCallBack callBack, int callId) {
        url = getDefaultParam(c, url);
        PostFormBuilder orb = OkHttpUtils.post().url(url)
                .id(callId)
                .tag("file");
        if (files != null) {
            Iterator<String> keys = files.keySet().iterator();
            while (keys != null && keys.hasNext()) {
                String key = keys.next();
                String val = files.get(key);
                File fs = new File(val);
                if (fs.exists()) {
                    orb.addFile(key, "", fs);
                }
            }
        }

        orb.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                filterError(callBack, e, ResultCode.RESULT_FAILD, id);
            }

            @Override
            public void onResponse(String response, int id) {
                filterData(c, response, callBack, id);
            }
        });
    }

    public void uploadFile(final Context c, String url, HashMap<String, String> files, final RequestCallBack callBack, int callId) {
        url = getDefaultParam(c, url);
        PostFormBuilder orb = OkHttpUtils.post().url(url)
                .id(callId)
                .tag("file");
        if (files != null) {
            Iterator<String> keys = files.keySet().iterator();
            while (keys != null && keys.hasNext()) {
                String key = keys.next();
                String val = files.get(key);
                File fs = new File(val);
                if (fs.exists()) {
                    orb.addFile(key, "", fs);
                }
            }
        }

        orb.build().execute(new FileUploadCallback(){
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
            }
        });
    }

    private String verStr = null;
    private String deviceIds = null;
    private String sysInt = null;

    public String getDefaultParam(Context c, String url) {

        if (!TextUtils.isEmpty(url)) {
            StringBuffer sb = new StringBuffer(url);
            if (!url.contains("?")) {
                sb.append("?");
            }
            if (TextUtils.isEmpty(verStr)) {
                verStr = CommonUtils.getBaseAppVersionName(c);
            }
            if (TextUtils.isEmpty(deviceIds)) {
                deviceIds = CommonUtils.getDeviceId(c);
            }
            if (TextUtils.isEmpty(sysInt)) {
                sysInt = CommonUtils.getSdkInt();
            }
            sb.append("&os=0");
            sb.append("&ver=" + verStr);
            sb.append("&deviceid=" + deviceIds);
            sb.append("&sdk_int=" + sysInt);

            if (GrassApp.getInstance().getUserInfo()!=null) {
                String token = GrassApp.getInstance().getUserInfo().token;
                String date =System.currentTimeMillis()+"";
                String userId =GrassApp.getInstance().getUserInfo().id;
                if (!TextUtils.isEmpty(token)){
                    token = CommonUtils.getMessageDigest((token+date+userId).getBytes())+date+userId;
                }
                sb.append("&tokens=" + token);
            }
            url = sb.toString();
            LogUtil.show("默认参数 url:" + url);
        }
        return url;
    }

    /**
     * 过滤成功返回数据
     *
     * @param response
     * @param callback
     * @param callId
     */
    private void filterData(Context c, String response, RequestCallBack callback, int callId) {
        if (callback == null) {
            return;
        }
        if (!TextUtils.isEmpty(response)) {
            LogUtil.show(response);
            try {
                JSONObject obj = new JSONObject(response);
                int ret = obj.optInt(KEY_RET, -1);
                String msg = obj.optString(KEY_MSG);
                JSONObject data = obj.optJSONObject(KEY_DATA);
                if (ret == ResultCode.RESULT_OK) {
                    if (data != null) {
                        callback.onSuccess(callId, data.toString(), msg);
                    } else {
                        callback.onSuccess(callId, "", msg);
                    }
                } else {
                    if (ret == ResultCode.RESULT_USER_NOLOGIN) {
                        GrassApp.getInstance().logout();
                        EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_LOGIN_MULT_ACCOUNT));
//                        LoginActivity.startLoginActivityTop(c);
                        return;
                    }
                    callback.onFail(callId, ret, msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
                callback.onFail(callId, ResultCode.RESULT_SERVER_ERROR, "服务器出故障了，返回格式错误" + e.getMessage());
                LogUtil.show("JSON格式错误:" + response);
            }
        }
    }

    private void filterError(RequestCallBack callback, Exception e, int code, int id) {
        if (callback != null) {
            String neterror = "网络故障";
            int errorId;
            if (e != null && ((e instanceof UnknownHostException) || (e instanceof ConnectException) || (e instanceof TimeoutException))) {
                neterror = "请检查网络是否连接";
                errorId = ResultCode.RESULT_OFFLINE;
            } else {
                neterror = "网络繁忙，请稍候再试!";
                errorId = ResultCode.RESULT_SERVER_ERROR;
            }
            callback.onFail(id, errorId, neterror);
            LogUtil.show("http error::" + e.getMessage());
        }
    }

}

//package com.weedys.weedlibrary.http;
//
//import android.content.Context;
//import android.text.TextUtils;
//
//import com.weedys.weedlibrary.utils.CommonUtils;
//import com.weedys.weedlibrary.utils.LogUtil;
//import com.weedys.weedlibrary.utils.NetWorkUtils;
//import com.zhy.http.okhttp.OkHttpUtils;
//import com.zhy.http.okhttp.builder.PostFormBuilder;
//import com.zhy.http.okhttp.callback.StringCallback;
//import com.zhy.http.okhttp.request.RequestCall;
//
//import org.json.JSONObject;
//
//import java.io.File;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import okhttp3.Call;
//import okhttp3.OkHttpClient;
//
///**
// * Created by weedys on 16/7/20.
// */
//public class HttpManager {
//    public static HttpManager manager;
//    public String KEY_RET="status";
//    public String KEY_MSG="message";
//    public String KEY_DATA="data";
//    public HttpManager(){
//        manager = this;
//        if(okHttpClient==null){
//            okHttpClient = new OkHttpClient();
//        }
//    }
//    public static HttpManager getInstance(){
//
//        if(manager==null){
//            manager= new HttpManager();
//        }
//        return manager;
//    }
//    private  OkHttpClient okHttpClient;
//    public void init(){
//
//
//    }
//    public void postRequest(Context c,String url, Map<String,String> map, final RequestCallBack callback, final int callId){
//        if(!NetWorkUtils.isConnected(c)){
//            if(callback!=null)
//                callback.onFail(callId, ResultCode.RESULT_OFFLINE,"无网络");
//            LogUtil.show("离线");
//            return;
//        }
//        url=getDefaultParam(c,url);
//        OkHttpUtils.post()
//                .url(url)
//                .params(map)
//                .id(callId)
//                .tag("post")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        filterError(callback,e,id);
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                       filterData(response,callback,id);
//                    }
//                });
//
//    }
//    public void getRequest(Context c, String url, Map<String,String> map, final RequestCallBack callback, final int callId){
//        if(!NetWorkUtils.isConnected(c)){
//            if(callback!=null)
//                callback.onFail(callId, ResultCode.RESULT_OFFLINE,"无网络");
//            LogUtil.show("离线");
//            return;
//        }
//        url=getDefaultParam(c,url);
//        final RequestCall builder= OkHttpUtils.get()
//                .id(callId)
//                .url(url)
//                .params(map)
//                .tag("get")
//                .build();
//
//        builder.execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        int code=0;
////                        try {
//////                            if(null!=builder.){
//////                                code=call.execute().code();
//////                                LogUtil.show("status code:"+code);
//////                             }
////                        } catch (IOException e1) {
////                            e1.printStackTrace();
////                        }
//                        filterError(callback,e,id);
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        filterData(response,callback,id);
//                    }
//                });
//        LogUtil.show("request:"+builder.getRequest()==null?"null":builder.getRequest().toString());
//
//    }
////    private void postData(Context c, final String url, final JSONObject param){
////        OkHttpUtils.postString().url(url)
////                .mediaType(MediaType.parse("application/json; charset=utf-8"))
////                .content(param.toString())
////                .build().execute(new StringCallback() {
////            @Override
////            public void onError(Call call, Exception e, int id) {
////
////            }
////
////            @Override
////            public void onResponse(String response, int id) {
////
////            }
////        });
////    }
//    public void MultPost(Context c, String url, HashMap<String,String> map, final HashMap<String,String> fileParam,final RequestCallBack callBack, int callId){
//        if(!NetWorkUtils.isConnected(c)){
//            if(callBack!=null)
//            callBack.onFail(callId, ResultCode.RESULT_OFFLINE,"无网络");
//            return;
//        }
//        url=getDefaultParam(c,url);
//       PostFormBuilder orb= OkHttpUtils.post().url(url)
//                .params(map)
//                .id(callId)
//                .tag("file");
//        if(fileParam!=null) {
//            Iterator<String> keys = fileParam.keySet().iterator();
//            while (keys!=null && keys.hasNext()){
//                String key=keys.next();
//                String val=fileParam.get(key);
//                File fs=new File(val);
//                if(fs.exists()) {
//                    orb.addFile(key, "png", fs);
//                }
//            }
//        }
//        orb.build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                filterError(callBack,e,id);
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                filterData(response,callBack,id);
//            }
//        });
//    }
//    public void upload(Context c,String url,HashMap<String,String> files,final RequestCallBack callBack,int callId){
//        url=getDefaultParam(c,url);
//        PostFormBuilder orb= OkHttpUtils.post().url(url)
//                .id(callId)
//                .tag("file");
//        if(files!=null) {
//            Iterator<String> keys = files.keySet().iterator();
//            while (keys!=null && keys.hasNext()){
//                String key=keys.next();
//                String val=files.get(key);
//                File fs=new File(val);
//                if(fs.exists()) {
//                    orb.addFile(key, "", fs);
//                }
//            }
//        }
//        orb.build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                filterError(callBack,e,id);
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                filterData(response,callBack,id);
//            }
//        });
//    }
//    private String verStr = null;
//    private String deviceIds=null;
//    private String sysInt=null;
//    public String getDefaultParam(Context c,String url){
//
//        if(!TextUtils.isEmpty(url)){
//            StringBuffer sb = new StringBuffer(url);
//            if(!url.contains("?")){
//                sb.append("?");
//            }
//            if(TextUtils.isEmpty(verStr)) {
//                verStr = CommonUtils.getBaseAppVersionName(c);
//            }
//            if(TextUtils.isEmpty(deviceIds)){
//                deviceIds = CommonUtils.getDeviceId(c);
//            }
//            if(TextUtils.isEmpty(sysInt)){
//                sysInt=CommonUtils.getSdkInt();
//            }
//            sb.append("&os=1");
//            sb.append("&ver="+verStr);
//            sb.append("&deviceid="+ deviceIds);
//            sb.append("&sdk_int="+sysInt);
//            sb.append("&role=1&");
//            url=sb.toString();
//            LogUtil.show("默认参数 url:"+url);
//        }
//        return url;
//    }
//
//    /**
//     * 过滤成功返回数据
//     * @param response
//     * @param callback
//     * @param callId
//     */
//    private void filterData(String response,RequestCallBack callback,int callId){
//        if(callback==null){
//            return;
//        }
//        if(!TextUtils.isEmpty(response)){
//            LogUtil.show(response);
//            try {
//                JSONObject obj = new JSONObject(response);
//                int ret=obj.optInt(KEY_RET,-1);
//                String msg=obj.optString(KEY_MSG);
//                JSONObject data=obj.optJSONObject(KEY_DATA);
//                if(ret==ResultCode.RESULT_OK){
//                    if(data!=null) {
//                        callback.onSuccess(callId, data.toString(), msg);
//                    }else{
//                        callback.onSuccess(callId, "", msg);
//                    }
//                }else{
//                    callback.onFail(callId,ret,msg);
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//                callback.onFail(callId,ResultCode.RESULT_SERVER_ERROR,"服务器故障，返回格式错误"+e.getMessage());
//                LogUtil.show("JSON格式错误:"+response);
//            }
//        }
//    }
//    private void filterError(RequestCallBack callback,Exception e,int id){
//        if(callback!=null){
//            String neterror="网络故障";
//            int errorId=ResultCode.RESULT_OFFLINE;
//            if(e!=null &&(e instanceof UnknownHostException)){
//                neterror="请检查网络是否连接";
//                errorId=ResultCode.RESULT_OFFLINE;
//            }else{
//                neterror = "服务器故障";
//                errorId=ResultCode.RESULT_SERVER_ERROR;
//            }
//            callback.onFail(id,errorId,neterror);
//            LogUtil.show("http error::"+e.getMessage());
//        }
//    }
//
//}

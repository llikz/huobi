package cn.grass.gate.presenters;


import com.weedys.weedlibrary.http.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.grass.gate.GrassApp;
import cn.grass.gate.http.Api;
import cn.grass.gate.http.HttpManager;
import cn.grass.gate.views.ScanView;


public class ScanPresenter{
    public ScanView view;
    public ScanPresenter(ScanView v){
        view =v;
    }

    public static int TOKEN_SCAN=0X119;
    public void getScan(String result){
        HashMap<String,String> map=new HashMap<>();
        map.put("qrCode",result);
        HttpManager.getInstance().postRequest(GrassApp.getInstance(), Api.URL_LOGIN, map,
                new RequestCallBack() {
                    @Override
                    public void onSuccess(int callId, String content, String msg) {
//                        if(!TextUtils.isEmpty(content)){
//                            try {
//                                JSONObject root = new JSONObject(content);
//                                view.onSuccess(callId,content,msg);
//                            }catch (Exception e){
//                                view.onFail(callId,"解析错误",-1);
//                            }
//                        }
                        view.onSuccess(callId,content,msg);
                    }
                    @Override
                    public void onFail(int callId, int errorId, String msg) {
                        view.onFail(callId,msg,errorId);
                    }
                },TOKEN_SCAN
        );
    }
    public static int TOKEN_SCAN_BING_CHILD=0X120;
    public void scanBindChild(String classCode){
        HashMap<String,String> map=new HashMap<>();
        map.put("classCode",classCode);
        HttpManager.getInstance().getRequest(GrassApp.getInstance(), Api.URL_LOGIN, map,
                new RequestCallBack() {
                    @Override
                    public void onSuccess(int callId, String content, String msg) {
                        try {
                            int flag = 2;
                            String message ="";
                            JSONObject obj = new JSONObject(content);
                            if (obj != null) {
//                                JSONObject obj2 = obj.optJSONObject("result");
                                flag = obj.optInt("flag");
                                message = obj.optString("message");
                            }
                            view.getScanResult(callId,flag,message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFail(int callId, int errorId, String msg) {
                        view.onFail(callId,msg,errorId);
                    }
                },TOKEN_SCAN_BING_CHILD
        );
    }
}

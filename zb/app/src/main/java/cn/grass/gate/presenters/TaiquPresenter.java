package cn.grass.gate.presenters;

import com.weedys.weedlibrary.http.RequestCallBack;

import java.util.HashMap;

import cn.grass.gate.GrassApp;
import cn.grass.gate.beans.Taiqu;
import cn.grass.gate.http.Api;
import cn.grass.gate.http.HttpManager;
import cn.grass.gate.utils.GsonImpl;
import cn.grass.gate.views.TaiquView;

/**
 * 台区列表
 * 返回台区列表,台区详情
 */
public class TaiquPresenter {

    private TaiquView view;
    public TaiquPresenter(TaiquView v){
        view = v;
    }
    public int TOKEN_GET_AREA_LIST=0x01;
    public void getTaiquList(String tId,int pageIndex,int pageSize){
        HashMap<String,String> map=new HashMap<>();
        map.put("TaiQuId",tId);
        map.put("pageIndex",""+pageIndex);
        map.put("pageSize",""+pageSize);
        HttpManager.getInstance().getRequestForGson(GrassApp.getInstance(), Api.URL_GET_AREA_LIST,
                map, new RequestCallBack() {
                    @Override
                    public void onSuccess(int callId, String content, String msg) {
                        Taiqu bean = GsonImpl.get().toObject(content,Taiqu.class);
                        if(view!=null){
                            view.taiquList(bean);
                            return;
                        }
                    }

                    @Override
                    public void onFail(int callId, int errorId, String msg) {
                        if(view!=null){
                            view.onFail(callId,msg,errorId);
                        }
                    }
                },TOKEN_GET_AREA_LIST);
    }


    public int TOKEN_GET_LIKE_TAIQU_LIST=0x02;
    public void getLikeTaiquList(String tId,int pageIndex,int pageSize,String searchStr){
        HashMap<String,String> map=new HashMap<>();
//        map.put("TaiQuId",tId);//这里ID没有用到
        map.put("pageIndex",""+pageIndex);
        map.put("pageSize",""+pageSize);
        map.put("titleKey",searchStr);

        HttpManager.getInstance().getRequestForGson(GrassApp.getInstance(), Api.URL_GET_LIKE_AREA_LIST,
                map, new RequestCallBack() {
                    @Override
                    public void onSuccess(int callId, String content, String msg) {
                        Taiqu bean = GsonImpl.get().toObject(content,Taiqu.class);
                        if(view!=null){
                            view.taiquList(bean);
                            return;
                        }
                    }

                    @Override
                    public void onFail(int callId, int errorId, String msg) {
                        if(view!=null){
                            view.onFail(callId,msg,errorId);
                        }
                    }
                },TOKEN_GET_LIKE_TAIQU_LIST);
    }
//    public void getActivityDetail(String actId){
//        HashMap<String,String> map=new HashMap<>();
//        map.put("actId",""+actId);
//        HttpManager.getInstance().getRequest(GrassApp.getInstance(), Api.URL_GET_ACTIVITY_DETAIL,
//                map, new RequestCallBack() {
//                    @Override
//                    public void onSuccess(int callId, String content, String msg) {
//                        ArrayList<ActivitiesInfo> activitiesInfos;
//                        if(!TextUtils.isEmpty(content)){
//                            try{
//                                JSONObject obj=new JSONObject(content);
//                                activitiesInfos = new ArrayList<ActivitiesInfo>();
//
//                                ActivitiesInfo info=new ActivitiesInfo();
//                                info.aid = obj.optString("id");
//                                info.title = obj.optString("title");
//                                info.content = obj.optString("content");
//                                JSONArray imgs=obj.optJSONArray("imgs");
//                                if(imgs!=null && imgs.length()>0){
//                                    String[] imgUrl=new String[imgs.length()];
//                                    for(int j=0;j<imgs.length();j++){
//                                        imgUrl[j]=imgs.optString(j);
//                                    }
//                                    info.images = imgUrl;
//                                }
//                                info.date = obj.optString("dateString");
//                                info.read = obj.optInt("viewnum");
//                                activitiesInfos.add(info);
//
//                                if(view!=null){
//                                    view.activityList(activitiesInfos);
//                                    return;
//                                }
//
//                            }catch (Exception e){
//
//                            }
//                            if(view!=null){
//                                view.onFail(callId,"解析异常",-1);
//                                return;
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFail(int callId, int errorId, String msg) {
//                        if(view!=null){
//                            view.onFail(callId,msg,errorId);
//                        }
//                    }
//                },TOKEN_GET_ACTIVITY_LIST);
//    }
}

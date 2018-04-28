package cn.grass.gate.presenters;

import com.weedys.weedlibrary.http.RequestCallBack;

import java.util.HashMap;

import cn.grass.gate.GrassApp;
import cn.grass.gate.beans.HangQing;
import cn.grass.gate.beans.HomeBean;
import cn.grass.gate.beans.Price;
import cn.grass.gate.http.Api;
import cn.grass.gate.http.HttpManager;
import cn.grass.gate.utils.GsonImpl;
import cn.grass.gate.views.HomeView;

/**
 * 市场
 */
public class HomePresenter {

    private HomeView view;
    public HomePresenter(HomeView v){
        view = v;
    }
    public int TOKEN_GET_HOME_LIST=0x01;
    public void getHomeList(){
        HashMap<String,String> map=new HashMap<>();
//        map.put("TaiQuId",tId);
//        map.put("pageIndex",""+pageIndex);
//        map.put("pageSize",""+pageSize);
        HttpManager.getInstance().getRequestForGson(GrassApp.getInstance(), Api.URL_GET_HOME_LIST,
                map, new RequestCallBack() {
                    @Override
                    public void onSuccess(int callId, String content, String msg) {
                        HomeBean bean = GsonImpl.get().toObject(content,HomeBean.class);
                        if(view!=null){
                            view.homeList(bean);
                            return;
                        }
                    }

                    @Override
                    public void onFail(int callId, int errorId, String msg) {
                        if(view!=null){
                            view.onFail(callId,msg,errorId);
                        }
                    }
                },TOKEN_GET_HOME_LIST);
    }

    public int TOKEN_GET_TICKER=0x02;
    public void getTicker(String market){
        HashMap<String,String> map=new HashMap<>();
        map.put("market",market);
        HttpManager.getInstance().getRequestForGson(GrassApp.getInstance(), Api.URL_GET_HANGQING,
                map, new RequestCallBack() {
                    @Override
                    public void onSuccess(int callId, String content, String msg) {
                        HangQing bean = GsonImpl.get().toObject(content,HangQing.class);
                        if(view!=null){
                            view.hangQing(bean);
                            return;
                        }
                    }

                    @Override
                    public void onFail(int callId, int errorId, String msg) {
                        if(view!=null){
                            view.onFail(callId,msg,errorId);
                        }
                    }
                },TOKEN_GET_TICKER);
    }

    public int TOKEN_GET_GATEIO_CNY=0x03;
    public void getGateRate(){
        HashMap<String,String> map=new HashMap<>();
        HttpManager.getInstance().getRequestForGson(GrassApp.getInstance(), Api.URL_GET_GATEIO_CNY,
                map, new RequestCallBack() {
                    @Override
                    public void onSuccess(int callId, String content, String msg) {
                        Price bean = GsonImpl.get().toObject(content,Price.class);
                        if(view!=null){
                            view.price(bean);
                            return;
                        }
                    }

                    @Override
                    public void onFail(int callId, int errorId, String msg) {
                        if(view!=null){
                            view.onFail(callId,msg,errorId);
                        }
                    }
                },TOKEN_GET_GATEIO_CNY);
    }

}

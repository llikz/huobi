package cn.grass.gate.views;

/**
 * Created by weedys on 16/8/4.
 */
public interface BaseView {
    public void onSuccess(int callId,String body,String msg);
    public void onFail(int callId,String msg,int errId);
}

package cn.grass.gate.views;


/**
 * Created by linxc on 2017/1/19.
 */
public interface ScanView extends BaseView{
    void getScanResult(int callId, int flag, String msg);
}

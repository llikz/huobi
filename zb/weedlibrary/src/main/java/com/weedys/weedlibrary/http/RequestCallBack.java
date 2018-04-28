package com.weedys.weedlibrary.http;

/**
 * Created by weedys on 16/7/21.
 */
public interface RequestCallBack { 
    public void onSuccess(int callId,String content,String msg);
    public void onFail(int callId,int errorId,String msg);
}

package com.weedys.weedlibrary.http;

/**
 * Created by weedys on 16/7/21.
 */
public class ResultCode {
    public static int RESULT_OK=1;//成功,改成1以前是101
    public static int RESULT_FAILD=0;//失败,改成0以前是201
    public static int RESULT_OFFLINE=-1;//无网络
    public static int RESULT_USER_NOLOGIN=208;//用户在其他设备登录
    public static int RESULT_SERVER_ERROR=-5;//数据出错

    public static int RESULT_USERINFO_ERROR=102;
}

package com.weedys.weedlibrary.beans;

import java.io.Serializable;

/**
 * Created by weedys on 16/11/25.
 */
public class LoginInfo implements Serializable{
    public String openId;
    public String unike;
    public int sex = 0;
    public String avator;
//    public String
    public int type = 0;//0 qq,1 微信 2微博
}

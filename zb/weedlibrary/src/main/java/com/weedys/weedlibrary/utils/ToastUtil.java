package com.weedys.weedlibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by weedys on 16/7/21.
 */
public class ToastUtil {
    public static void show(Context c,String msg){
        Toast.makeText(c,msg,Toast.LENGTH_SHORT).show();
    }
    public static void shortShow(Context c,String msg)
    {
        show(c,msg,Toast.LENGTH_SHORT);
    }
    public static void longShow(Context c,String msg)
    {
        show(c,msg,Toast.LENGTH_LONG);
    }
    private static void show(Context c,String msg,int duration){
       // Toast.makeText(GrassApp.getInstance(),msg,duration).show();
        Toast ts=Toast.makeText(c,msg,duration);

        ts.show();
    }
}

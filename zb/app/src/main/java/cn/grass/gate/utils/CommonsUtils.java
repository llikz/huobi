package cn.grass.gate.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;


/**
 * Created by wgyscsf on 2016/8/30.
 * 邮箱：wgyscsf@163.com
 * 博客：http://blog.csdn.net/wgyscsf
 */
public class CommonsUtils {
    //根据时间长短计算语音条宽度:220dp
    public synchronized static int getVoiceLineWight(Context context, int seconds) {
        //改一下这个逻辑，大于180s的最长了，120的增长一个，
        //1-2s是最短的。2-10s每秒增加一个单位。10-60s每10s增加一个单位。
        if (seconds <= 20) {
            return dip2px(context, 60);
        } else if (seconds <= 180) {
            //90~170
            return dip2px(context, 90 + 8 * (seconds/10));
        } else {
            //170~220
            int length =  170 + 10 * (seconds / 10);
            if(length >= 250){
                length = 250;//最大长度
            }
            return dip2px(context,length);

        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 字符串过滤空格及换行符
     *
     * @return 过滤后字符串
     */
    public static String filterString(String text) {
        if (!TextUtils.isEmpty(text)){
//            String str = text.replaceAll(" |\r\n|\r|\n|\n\r|\t", "");
            String str = text.trim();
            return str;
        }else {
            return "";
        }
    }

    /**
     * 调节该activity 亮度
     */
    public static void setLight(Activity context, int brightness) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        context.getWindow().setAttributes(lp);
    }

    /**
     * 用户密码限制
     * @return boolean true 合法密码  false 非法密码
     */
    public static boolean pwdLimit(String pwd) {
        if (pwd.length()<3||pwd.length()>20){
            ToastUtil.show("请输入多于3位，少于20位的密码");
            return false;
        }else {
            return true;
        }
    }

}

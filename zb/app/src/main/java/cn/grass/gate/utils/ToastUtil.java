package cn.grass.gate.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weedys.weedlibrary.utils.LogUtil;

import cn.grass.gate.GrassApp;

/**
 * Created by weedys on 16/7/21.
 */
public class ToastUtil {
    private static Toast toast;

    private static View view;

    private ToastUtil() {
    }

    private static void getToast(Context context) {
        if (toast == null) {
            toast = new Toast(context);
        }
        if (view == null) {
            view = Toast.makeText(context, "", Toast.LENGTH_SHORT).getView();
        }
        toast.setView(view);
    }
    private static void showToast(Context context, CharSequence msg, int duration) {
        try {
            getToast(context);
            toast.setText(msg);
            toast.setDuration(duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception e) {
            LogUtil.show(e.getMessage());
        }
    }
    public static void show(String msg){
        LogUtil.show("toast:"+msg);
        showToast(GrassApp.getInstance(),msg,Toast.LENGTH_SHORT);
    }
    public static void shortShow(String msg)
    {
        show(msg,Toast.LENGTH_SHORT);
    }
    public static void longShow(String msg)
    {
        show(msg,Toast.LENGTH_LONG);
    }
    private static void show(String msg,int duration){
       // Toast.makeText(GrassApp.getInstance(),msg,duration).show();
        LogUtil.show("toast:"+msg);
        Toast ts=Toast.makeText(GrassApp.getInstance(),msg,duration);
        ts.setGravity(Gravity.CENTER,0,0);
        LinearLayout toastView =(LinearLayout) ts.getView();
        TextView msgTv=(TextView) toastView.getChildAt(0);
        msgTv.setGravity(Gravity.CENTER);
//        msgTv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.common_bt_more_bottombar_normal,0,0,0);
//        ImageView iv=new ImageView(GrassApp.getInstance());
//        iv.setImageResource(R.mipmap.ic_launcher);
//        toastView.setOrientation(LinearLayout.HORIZONTAL);
//        toastView.setHorizontalGravity(Gravity.CENTER_VERTICAL);
//        toastView.addView(iv,0);
        ts.show();
    }
}

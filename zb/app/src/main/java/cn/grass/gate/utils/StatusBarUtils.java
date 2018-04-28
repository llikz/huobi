package cn.grass.gate.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by min on 2017/4/28.
 * 修改状态栏上的颜色
 */

public class StatusBarUtils {
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                Window window = activity.getWindow();
                //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏颜色
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * 沉浸式状态栏
     * @param activity
     */
    public static void setStatueBarColor(Activity activity){
        if(null != activity){
            activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                setHideVirtualKey(window);
                window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        setHideVirtualKey(window);
                    }
                });
            }
        }
    }
    public static void setHideVirtualKey(Window window){
        //保持布局状态
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                //布局位于状态栏下方
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                //全屏(去掉全屏是为了能看到最上面的状态栏)
//                View.SYSTEM_UI_FLAG_FULLSCREEN|
                //隐藏导航栏
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        if (Build.VERSION.SDK_INT>=19){
            uiOptions |= 0x00001000;
        }else{
            uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        window.getDecorView().setSystemUiVisibility(uiOptions);
    }


    public static void setWindowStatusBarColor(Dialog dialog, int colorResId) {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                Window window = dialog.getWindow();

                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                window.setStatusBarColor(dialog.getContext().getResources().getColor(colorResId));
                //底部导航栏

                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * 先判断一下你的手机是否含有NavigationBar这个东西，就是虚拟键;
     * 返回为true的话就不给他设置，反之设置
     * @param context
     */
    public static void transportStatus(Activity context){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            if (!isHaveNavigationBar(context))

                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }

    }

    /**
     * 判断一下你的手机是否含有NavigationBar这个东西，就是虚拟键;
     * @param context
     * @return
     */
    public static boolean isHaveNavigationBar(Context context) {
        boolean hasNavigationBar;
        boolean isHave = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                isHave = false;
            } else if ("0".equals(navBarOverride)) {
                isHave = true;
            }
        } catch (Exception e) {

        }
        return isHave;
    }
}

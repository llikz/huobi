package cn.grass.gate.utils;

import com.weedys.weedlibrary.utils.LogUtil;

/**
 * Created by min on 2017/5/27.
 */
public class ClickUtil {
    // 两次点击按钮之间的点击间隔不能少于2000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 3000;
    private static long lastClickTime;

    /**
     * 返回true就是点击时间间隔大于MIN_CLICK_DELAY_TIME
     * @return
     */
    public static boolean isNotFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        LogUtil.show("curClickTime:"+curClickTime+"lastClickTime:"+lastClickTime+"--"+Math.abs(curClickTime - lastClickTime));
        //取绝对值是为了防止用户更改手机时间
        if (Math.abs(curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
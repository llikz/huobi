package cn.grass.gate.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by min on 2017/5/11.
 * ActivityManager管理activity
 * 可以移除,移除全部,添加
 */

public class ActivityManager {
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;
    private ActivityManager() {
    }
    public static ActivityManager getScreenManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }
    public static List<Activity> mActivities=new ArrayList<>();
    public static void addActivity(Activity mActivity){
        mActivities.add(mActivity);
    }
    public static void removeActivity(Activity mActivity){
        mActivities.remove(mActivity);
    }
    public static void finishAll(){
        for(Activity mActivity1:mActivities){
            if(!mActivity1.isFinishing()){
                mActivity1.finish();
            }
        }
    }
    public static void finishAllExceptOne(Class cls){
        for(Activity mActivity1:mActivities){
            if(!mActivity1.isFinishing()){
                if (mActivity1.getClass().equals(cls)) {
                }else {
                    mActivity1.finish();
                }
            }
        }
    }

    //退出栈顶Activity
    public void popActivity(Activity activity) {
        if (activity != null) {
            //在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
            activity.finish();
            activityStack.remove(activity);
            removeActivity(activity);//TODO
            activity = null;
        }
    }
    //获得当前栈顶Activity
    public Activity currentActivity() {
        Activity activity = null;
        if(!activityStack.empty())
            activity= activityStack.lastElement();
        return activity;
    }
    //将当前Activity推入栈中
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
        addActivity(activity);//TODO
    }
    //退出栈中所有Activity
    public void popAllActivityExceptOne(Class cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }
}

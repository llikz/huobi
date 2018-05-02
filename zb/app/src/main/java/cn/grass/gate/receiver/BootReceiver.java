package cn.grass.gate.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

    /*要接收的intent源*/
     static final String ACTION = "android.intent.action.BOOT_COMPLETED";
     static final String ACTION2 = "android.intent.action.AIRPLANE_MODE";//关闭或打开飞行模式时的广播
     static final String ACTION3 = "android.intent.action.CLOSE_SYSTEM_DIALOGS";//锁屏
    private String TAG="BootReceiver###";
    public void onReceive(Context context, Intent mintent)
     {
                 if (Intent.ACTION_BOOT_COMPLETED.equals(mintent.getAction())
                         || Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(mintent.getAction())
                         ||Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(mintent.getAction())
                         ||Intent.ACTION_NEW_OUTGOING_CALL.equals(mintent.getAction())) {
                     Log.i(TAG, "-->>onReceive");
                         // 启动完成
                         Intent intent = new Intent(context, Alarmreceiver.class);
                         intent.setAction("arui.alarm.action");
                         PendingIntent sender = PendingIntent.getBroadcast(context, 0,
                                         intent, 0);
                         long firstime = SystemClock.elapsedRealtime();
                        AlarmManager am = (AlarmManager) context
                                 .getSystemService(Context.ALARM_SERVICE);

                         // 10秒一个周期，不停的发送广播
                         am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime,
                                         6*10 * 1000, sender);
                     }
             }
}

package cn.grass.gate.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.grass.gate.service.HelloteacherService;

public class Alarmreceiver extends BroadcastReceiver {
    private String TAG="Alarmreceiver###";
    @Override
     public void onReceive(Context context, Intent intent) {
                 // TODO Auto-generated method stub
        if ("arui.alarm.action".equals(intent.getAction())) {
            Log.i(TAG, "-->>onReceive");
             Intent i = new Intent();
             i.setClass(context, HelloteacherService.class);
             // 启动service
             // 多次调用startService并不会启动多个service 而是会多次调用onStart
             context.startService(i);
         }
    }
}

package cn.grass.gate.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.weedys.weedlibrary.http.RequestCallBack;

import org.litepal.tablemanager.Connector;

import java.util.HashMap;

import cn.grass.gate.GrassApp;
import cn.grass.gate.R;
import cn.grass.gate.beans.Price;
import cn.grass.gate.http.Api;
import cn.grass.gate.http.HttpManager;
import cn.grass.gate.model.HuoBiData;
import cn.grass.gate.receiver.Alarmreceiver;
import cn.grass.gate.utils.GsonImpl;

public class HelloteacherService extends Service {
//     HelloteacherService2 hs2;
    private String TAG="HelloteacherService###";
     @Override
    public void onCreate() {
         super.onCreate();
        Log.i(TAG, "-->>onCreate");
    }

             @Override
     public int onStartCommand(Intent intent, int flags, int startId) {
                 Log.i(TAG, "-->>onStartCommand-->>"+startId);
                 flags = START_STICKY;

                 new Handler().postDelayed(new Runnable() {
                     public void run() {
                         //获取数据
                         getData();
                     }
                 }, 1000);

                  //启用前台服务，主要是startForeground()
//                     Notification notification = new Notification(R.mipmap.icon_logo, "用电脑时间过长了！白痴！", System.currentTimeMillis());
////                     notification.setLatestEventInfo(this, "快去休息","一定保护眼睛,不然遗传给孩子，老婆跟别人跑啊。", null);
//                        notification.setContentTitle("最简单的Notification");
                 //获取NotificationManager实例
                 NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                 //实例化NotificationCompat.Builde并设置相关属性
                 NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                         //设置小图标
                         .setSmallIcon(R.mipmap.icon_logo)
                         //设置通知标题
                         .setContentTitle("最简单的Notification")
                         //设置通知内容
                         .setContentText("只有小图标、标题、内容");

                 Notification notification = builder.build(); // 获取构建好的Notification
                 //设置通知时间，默认为系统发出通知的时间，通常不用设置
                 //.setWhen(System.currentTimeMillis());
                 //通过builder.build()方法生成Notification对象,并发送通知,id=1
                 notifyManager.notify(1, builder.build());
                     //设置通知默认效果
                 notification.flags = Notification.FLAG_SHOW_LIGHTS;
                 startForeground(1, notification);

                 AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
                 //读者可以修改此处的Minutes从而改变提醒间隔时间
                 //此处是设置每隔55分钟启动一次
                 //这是55分钟的毫秒数
                 int Minutes = 1 * 60 * 1000;
                     //SystemClock.elapsedRealtime()表示1970年1月1日0点至今所经历的时间
                 long triggerAtTime = SystemClock.elapsedRealtime() + Minutes;
                     //此处设置开启AlarmReceiver这个Service
                 Intent i = new Intent(this, Alarmreceiver.class);
                 PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
                 //ELAPSED_REALTIME_WAKEUP表示让定时任务的出发时间从系统开机算起，并且会唤醒CPU。
                 manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

                return super.onStartCommand(intent, Service.START_STICKY, startId);
             }
    public int TOKEN_GET_GATEIO_USDTCNY=0x04;
    private void getData() {
        HashMap<String,String> map=new HashMap<>();
        HttpManager.getInstance().getRequestForGson(GrassApp.getInstance(), Api.URL_GET_GATEIO_USDTCNY,
                map, new RequestCallBack() {
                    @Override
                    public void onSuccess(int callId, String content, String msg) {
                        HuoBiData bean = GsonImpl.get().toObject(content,HuoBiData.class);
                        //保存到数据库
                        Log.i(TAG, "-->>onSuccess-->>"+bean.toString());
//                        bean.save();

//                        if(bean.save()){
//                            Log.i(TAG, "-->>Success-->>save:"+bean.toString());
//                        }else{
//                            Log.i(TAG, "-->>Fail-->>save:"+bean.toString());
//                            SQLiteDatabase db = Connector.getDatabase();
//                        }
                    }

                    @Override
                    public void onFail(int callId, int errorId, String msg) {
                        Log.i(TAG, "-->>onFail-->>");
                    }
                },TOKEN_GET_GATEIO_USDTCNY);
    }
    //监听服务2实现函数


            @Override
     public IBinder onBind(Intent arg0) {
         Log.i(TAG, "-->>onBind");
         // TODO Auto-generated method stub
         return null;
     }
      @Override
      public void onDestroy() {
      Log.i(TAG, "-->>onDestroy");
         super.onDestroy();

     }
 }

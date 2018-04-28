package cn.grass.gate.http.message;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.weedys.weedlibrary.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import cn.grass.gate.R;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by min on 2017/6/16.
 */

public class JPReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            LogUtil.show("JPush用户注册成功");
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
                .getAction())) {
            LogUtil.show("接受到推送下来的自定义消息");
            receivingNotification(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
                .getAction())) {
            LogUtil.show("接受到推送下来的通知");
            receiverNotification();
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
                .getAction())) {
            LogUtil.show("用户点击打开了通知");
            openNotification(context, bundle);
        }
    }

    private void receiverNotification() {
        EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_GET_UNREAD_MSG));

//        MainPresenter presenter = new MainPresenter(new MainView() {
//            @Override
//            public void getWeatherDetail(WeatherInfo info) {
//
//            }
//
//            @Override
//            public void onSuccess(int callId, String body, String msg) {
//
//            }
//
//            @Override
//            public void onFail(int callId, String msg, int errId) {
//
//            }
//        });
//        presenter.getUnread();

        EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_GET_UPDATE_MSG));
    }

    private void openNotification(Context context, Bundle bundle) {
//        //打开自定义的Activity
//        Intent i = new Intent(context, SplashActivity.class);
//        i.putExtras(bundle);
//        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(i);
    }

    private void receivingNotification(Context context, Bundle bundle) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        // 使用notification
        // 使用广播或者通知进行内容的显示
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context);
        builder.setContentText(message).setSmallIcon(R.mipmap.icon_logo).setContentTitle(JPushInterface.EXTRA_TITLE);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        manager.notify(1, builder.build());
    }
}

package com.kevin.administrator.service_demo_2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Administrator on 2016/6/13.
 */
public class MyDownlodService extends Service {
    private String fileName;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String imageUrl = intent.getStringExtra("imageUrl");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Httputils.isNetworkConn(MyDownlodService.this)) {
                    byte[] bytes = Httputils.getHttpToResult(imageUrl);
                    fileName = imageUrl.substring(imageUrl.lastIndexOf('.') - 1);
                    boolean bl = ExternalStorageUtils.writeExternalStoragePublic(Environment.DIRECTORY_DOWNLOADS, fileName, bytes);
                    if (bl) {
                        sendNotifaction(fileName);
                    }
                }

            }
        }).start();

        return START_REDELIVER_INTENT;
    }

    private void sendNotifaction(String fileName) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("提示消息");
        builder.setContentText("下载完成");
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Intent intent = new Intent(this, ShowActivity.class);
        intent.putExtra("filename", fileName);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}

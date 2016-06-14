package com.kevin.administrator.service_demo_2;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Administrator on 2016/6/13.
 */
public class ShowActivity extends AppCompatActivity {
    private ImageView iv_show;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        iv_show= (ImageView) findViewById(R.id.iv_show);
        //去获取通知中传递的文件名
        String fileName=getIntent().getStringExtra("fileName");
        //获取当前图片的路径
        String pathName= Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+ File.separator+fileName;
        Bitmap bm= BitmapFactory.decodeFile(pathName);//根据参数中指定的图片路径转换成bitmap对象
        iv_show.setImageBitmap(bm);
        //关闭通州
        NotificationManager manager= (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(1);
    }
}
package com.kevin.administrator.lx_bind_service_01;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/6/13.
 */
public class MyBindService extends Service {
    @Nullable
    @Override
    /**
     *
     * 表示应用程序组件(Activity)调用bindervice()方法绑定服务时
     * 组件与服务绑定到一起时回调的函数
     * 注意:应用程序与service只能绑定一次
     */
    public IBinder onBind(Intent intent) {

        return new MyBind();
    }

    public class MyBind extends Binder {
        public MyBindService getService() {
            return MyBindService.this;
        }
    }

    public int getRandom() {
        return (int) (Math.random() * 10 + 1);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 表示于其他的应用程序组件绑定服务时回调的函数
     * @param intent
     */
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
}

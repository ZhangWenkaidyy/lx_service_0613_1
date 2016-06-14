package com.kevin.administrator.lx_bind_service_01;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn = new MyServiceConnection();
    }

    /**
     * 绑定服务
     * 应用程序组件(Acivity)调用bindService()方法启动服务时 称为绑定服务
     * bindService() 将服务与启动服务的组件绑定到一起
     * <p/>
     * 特点:
     * 1.绑定式服务类似与客户端－服务端的接口模式 允许应用承租组件(Actvity)与服务进行交互
     * (组件可以调用服务中的方法)
     * 2.应用程序调用bindService()方法后 绑定服务 启动服务并且运行
     * 3.应用程序组件与服务运行时间一致
     * 多个应用程序组件可以同时绑定到同一个服务 服务销毁 绑定解除
     * 当绑定服务的多个应用程序组件都销毁时 服务也会跟着销毁
     *
     * @param view
     */
    public void baoding(View view) {
        Intent intent = new Intent(this, MyBindService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    public void jiebang(View view) {

        unbindService(conn);
    }


    public class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MyBindService.MyBind bind = (MyBindService.MyBind) iBinder;
            MyBindService myBindService = bind.getService();
            int randomNumber = myBindService.getRandom();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}


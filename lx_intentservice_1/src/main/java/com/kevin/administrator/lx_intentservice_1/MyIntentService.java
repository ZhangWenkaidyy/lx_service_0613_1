package com.kevin.administrator.lx_intentservice_1;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Administrator on 2016/6/13.
 */
public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//底层已经开辟了工作线程
    }
}

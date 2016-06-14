package com.kevin.administrator.lx_service_0613_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void click1(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}

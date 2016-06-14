package com.kevin.administrator.service_demo_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String imageUrl = "http://img1.imgtn.bdimg.com/it/u=1815829197,442667104&fm=21&gp=0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Download(View view) {
        Intent intent = new Intent(this, MyDownlodService.class);
        intent.putExtra("imageUrl",imageUrl);
        startService(intent);
    }
}

package com.kevin.administrator.service_demo_2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Httputils {

    public static boolean isNetworkConn(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return info.isConnected();
        } else {
            return false;
        }
    }

    public static byte[] getHttpToResult(String path) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedInputStream bis = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.connect();
            int ResponseCode = conn.getResponseCode();
            if (ResponseCode == 200) {
                bis = new BufferedInputStream(conn.getInputStream());
                int temp = 0;
                byte[] buff = new byte[1024];
                while ((temp=bis.read(buff))!=-1) {
                    baos.write(buff, 0, temp);

                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return baos.toByteArray();
    }
}

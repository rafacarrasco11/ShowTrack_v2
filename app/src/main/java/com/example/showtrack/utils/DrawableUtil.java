package com.example.showtrack.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Request;
import okhttp3.Response;

public class DrawableUtil {

    static Bitmap x;

    public static Drawable drawableFromUrl(String url) throws IOException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.connect();

                    InputStream input = connection.getInputStream();

                    x = BitmapFactory.decodeStream(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new BitmapDrawable(Resources.getSystem(), x);
    }
}

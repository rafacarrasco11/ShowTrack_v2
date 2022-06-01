package com.example.showtrack.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIUtil {

    private JsonReader jsonReader;
    private Gson gson;
    OkHttpClient client;

    private String json;

    private static APIUtil instance;

    private APIUtil() {
        this.gson = new Gson();
        this.client = new OkHttpClient();
        json = "";
    }

    public static APIUtil getInstance() {
        if (instance == null)
            instance = new APIUtil();

        return instance;
    }

    public Object getFromApi(Type object, String url) throws IOException {
        jsonReader = new JsonReader(new StringReader(getJSON(url)));
        Object o = gson.fromJson(jsonReader, object);
        jsonReader.close();
        return o;
    }

    private String getJSON(String url) throws IOException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    Request request = new Request.Builder()
                            .url(url)
                            .get()
                            .build();

                    Response response = client.newCall(request).execute();
                    json = response.body().string();
                } catch (Exception e) {
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


        return json;
    }

    private String getJSON(String url, String host, String key) throws IOException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    Request request = new Request.Builder()
                            .url("https://data-imdb1.p.rapidapi.com/titles?info=mini_info&limit=10&page=1&titleType=movie&genre=Action&year=2022")
                            .get()
                            .addHeader("X-RapidAPI-Host", host)
                            .addHeader("X-RapidAPI-Key", key)
                            .build();

                    Response response = client.newCall(request).execute();
                    json = response.body().string();
                } catch (Exception e) {
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


        return json;
    }

}

package com.example.liumengchao20180702.Utills;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtills {
    public static void  getHttputils(final String path, Context context, final JsonBack jsonBack){
        AsyncTask<String,Void,String>  MyasyncTask=new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    int responseCode = urlConnection.getResponseCode();
                   if (responseCode==200){
                       InputStream inputStream = urlConnection.getInputStream();
                       String tranfroms = SteamString.tranfroms(inputStream);
                       Log.i("TAG",tranfroms+"");
                       return tranfroms;
                   }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                jsonBack.getData(s);
            }
        };
        MyasyncTask.execute();
    }
}
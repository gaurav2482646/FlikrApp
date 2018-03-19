package com.example.flikrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class TestActivity extends AppCompatActivity {
    String TAG ="TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        AckCommand ackCommand = new AckCommand();
        ackCommand.start();
    }
    public class AckCommand extends Thread {
        public  final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        private static final String TAG = "AckCommand";



        @Override
        public void run() {
            super.run();
            try {
                String response_from_server = get("http://172.17.103.82/CommandService/AntitheftService.svc/webclient/device/getcommand/9780307585");
                Log.d(TAG, "Response of Server:" + response_from_server);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String get(String url) throws IOException {
            final OkHttpClient client = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .get()
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }
}

package com.example.flikrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class TestActivity extends AppCompatActivity {
    String TAG ="TestActivity";
    TextView mResponse;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mResponse = findViewById(R.id.tvResponse);
        mEditText = findViewById(R.id.editText);

    }

    public void sendRequest(View view) {
        try{
        AckCommand ackCommand = new AckCommand();
        ackCommand.start();}catch(Exception e){
            Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public class AckCommand extends Thread {
        public  final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        private static final String TAG = "AckCommand";



        @Override
        public void run() {
            super.run();
            try {
                final String response_from_server = get(mEditText.getText().toString());
                Log.d(TAG, "Response of Server:" + response_from_server);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResponse.setText(response_from_server);
                    }
                });
            } catch (final IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResponse.setText("Error in Sending Request : "+e.getMessage());
                    }
                });
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

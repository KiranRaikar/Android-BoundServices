package com.example.kraikar.boundservices;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyService myService;

    private boolean isBound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent service = new Intent(this, MyService.class);
        bindService(service, serviceConnection, BIND_AUTO_CREATE);
    }

    public void showTime(View view) {
        String showTime = myService.getCurrentTime();
        TextView textViewShowTime = (TextView) findViewById(R.id.textViewShowTime);
        textViewShowTime.setText(showTime);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyLocalBinder myLocalBinder = (MyService.MyLocalBinder) service;
            myService = myLocalBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}

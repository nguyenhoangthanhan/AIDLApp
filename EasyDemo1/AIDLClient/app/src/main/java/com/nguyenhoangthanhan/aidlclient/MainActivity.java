package com.nguyenhoangthanhan.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nguyenhoangthanhan.aidlserver.IAidlColorInterface;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity_TAG";


    IAidlColorInterface iAidlColorInterface;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iAidlColorInterface = IAidlColorInterface.Stub.asInterface(service);
            Log.d(TAG, "Remote config Service Connected!!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("AidlColorService1111");
        intent.setPackage("com.nguyenhoangthanhan.aidlserver");

        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        Button b = findViewById(R.id.button_change_color);

        b.setOnClickListener(v -> {
            try {
                int color = iAidlColorInterface.getColor();
                v.setBackgroundColor(color);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        });
    }
}
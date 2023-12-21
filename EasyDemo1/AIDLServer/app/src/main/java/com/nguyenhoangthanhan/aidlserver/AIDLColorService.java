package com.nguyenhoangthanhan.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class AIDLColorService extends Service {

    private final static String TAG = "AIDLColorService_TAG";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private final IAidlColorInterface.Stub binder = new IAidlColorInterface.Stub() {

        @Override
        public int getColor() throws RemoteException {
            Random random = new Random();
            int color = Color.argb(255, random.nextInt(256)
                    , random.nextInt(256), random.nextInt(256));
            Log.d(TAG, "getColor: " + color);
            return color;
        }
    };
}

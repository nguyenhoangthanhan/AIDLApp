package com.nguyenhoangthanhan.updateuifrombackgroundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Message
import android.util.Log

class UpdateUIByBroadcastService : Service() {

    companion object{
        val MY_ACTION = "MY_UpdateUIByBroadcast_ACTION"
    }

    private val TAG = "UpdateUIByBroadcastService_TAG"

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "#onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "#onStartCommand")

        val t: Thread = object : Thread() {
            override fun run() {
                for (i in 0..20 step 1) {
                    Log.d(TAG, "i = $i")
                    val sendToUIIntent = Intent()
                    sendToUIIntent.action = MY_ACTION
                    sendToUIIntent.putExtra("DATAPASSED", "i = $i")
                    sendBroadcast(sendToUIIntent)
                    sleep(2000)
                }
            }
        }
        t.start()

        return super.onStartCommand(intent, flags, startId)
    }
}
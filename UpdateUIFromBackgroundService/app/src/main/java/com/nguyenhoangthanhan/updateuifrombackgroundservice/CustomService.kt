package com.nguyenhoangthanhan.updateuifrombackgroundservice

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.os.ResultReceiver
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CustomService : Service() {
    private val TAG = "CustomService_TAG"

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    val STATUS_RUNNING = 0
    val STATUS_FINISHED = 1
    val STATUS_ERROR = 2

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "#onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "#onStartCommand")
        /*
         * Step 1: We pass the ResultReceiver from the activity to the intent service via intent.
         *  */
        val receiver: ResultReceiver? = intent!!.getParcelableExtra("receiver")

        //TODO: process background task here!

        scope.launch{
            for (i in 0..20 step 1){
                val b = Bundle()
                withContext(Dispatchers.Main){
                    Log.d(TAG, "i = $i")
                }
                b.putString("current_status", "i = $i")
                receiver?.send(STATUS_FINISHED, b)
                Thread.sleep(2000)
            }
        }

        /*
         * Step 2: Now background service is processed,
         * we can pass the status of the service back to the activity using the resultReceiver
         *  */

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
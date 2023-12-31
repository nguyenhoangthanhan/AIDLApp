package com.nguyenhoangthanhan.updateuifrombackgroundservice

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


class CustomService : Service() {
    private val TAG = "CustomService_TAG"

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val disposable = CompositeDisposable()

    companion object {
        const val STATUS_RUNNING = 0
        const val STATUS_FINISHED = 1
        const val STATUS_ERROR = 2
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "#onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "#onStartCommand")
        /* Way 1:
         * Step 1: We pass the ResultReceiver from the activity to the intent service via intent.
         *  */
//        val receiver: ResultReceiver? = intent!!.getParcelableExtra("receiver")

        /* Way 2:
         * Step 1: We pass the Handler from the activity to the intent service via intent.
         *  */
        val m: Messenger = intent!!.getParcelableExtra("handler")!!

        //TODO: process background task here!

        //Coroutine
//        scope.launch{
//            for (i in 0..20 step 1){
//                val b = Bundle()
//                withContext(Dispatchers.Main){
//                    Log.d(TAG, "i = $i")
//                }
//                b.putString("current_status", "i = $i")
//                receiver?.send(STATUS_FINISHED, b)
//                Thread.sleep(2000)
//            }
//        }

        val t: Thread = object : Thread() {
            override fun run() {
                for (i in 0..20 step 1) {
                    Log.d(TAG, "i = $i")
//                    val b = Bundle()
//                    b.putString("current_status", "i = $i")
//                    apply ResultReceiver to update UI
//                    receiver?.send(STATUS_FINISHED, b)

//                    apply Handler to update UI

                    val msg = Message()
                    msg.obj = "i = $i"
                    msg.what = STATUS_FINISHED
                    m.send(msg)
                    sleep(2000)
                }
            }
        }
        t.start()

//        disposable.add(
//            Observable.interval(1000L, TimeUnit.MILLISECONDS)
//                .timeInterval()
//                .subscribeOn(Schedulers.io())
//                .subscribe {
//                    for (i in 0..20 step 1) {
//                        val b = Bundle()
//                        Log.d(TAG, "i = $i")
//                        b.putString("current_status", "i = $i")
//                        receiver?.send(STATUS_FINISHED, b)
//                        Thread.sleep(2000)
//                    }
//                }
//        )

        /*
         * Step 2: Now background service is processed,
         * we can pass the status of the service back to the activity using the resultReceiver
         *  */

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        disposable.clear()
        disposable.dispose()
    }

}
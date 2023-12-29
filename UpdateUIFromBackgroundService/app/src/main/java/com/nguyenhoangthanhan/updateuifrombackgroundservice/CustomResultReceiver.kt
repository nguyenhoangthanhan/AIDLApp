package com.nguyenhoangthanhan.updateuifrombackgroundservice

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver

class CustomResultReceiver(
    handler: Handler,
    receiver: AppReceiver?
) : ResultReceiver(handler) {

    private val appReceiver:AppReceiver? = receiver

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        appReceiver?.onReceiveResult(resultCode, resultData)
    }
}


interface AppReceiver {
    fun onReceiveResult(resultCode: Int, resultData: Bundle?)
}
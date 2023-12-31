package com.nguyenhoangthanhan.updateuifrombackgroundservice

import android.os.Handler
import android.os.Message

class CustomHandler(appReceiver: AppReceiver) : Handler() {

    private val mAppReceiver = appReceiver

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        mAppReceiver.onReceiveResult(msg)
    }

    interface AppReceiver {
        fun onReceiveResult(message: Message)
    }
}
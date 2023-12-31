package com.nguyenhoangthanhan.updateuifrombackgroundservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle


class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.extras != null) {

//            val `in` = Intent("com.an.sms.example")
//            val extras = Bundle()
//            extras.putString("com.an.sms.example.otp", otpCode)
//            `in`.putExtras(extras)
//            context!!.sendBroadcast(`in`)
        }
    }
}
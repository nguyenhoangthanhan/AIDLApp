package com.nguyenhoangthanhan.updateuifrombackgroundservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nguyenhoangthanhan.updateuifrombackgroundservice.databinding.ActivityUpdateUibyBroadcastReceiver2Binding


class UpdateUIByBroadcastReceiverActivity : AppCompatActivity() {

    private var mIsReceiverRegistered = false
    private var mReceiver: MyBroadcastReceiver? = null

    private var broadcastReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
//            val datapassed = intent.getIntExtra("DATAPASSED", 0)
//            val s = intent.action.toString()
            val s1 = intent.getStringExtra("DATAPASSED")
            updateUI(s1 ?: "Null value")
        }
    }

    private lateinit var binding: ActivityUpdateUibyBroadcastReceiver2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUibyBroadcastReceiver2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val intentFilter = IntentFilter()
        intentFilter.addAction(UpdateUIByBroadcastService.MY_ACTION)
        registerReceiver(broadcastReceiver, intentFilter)
        //Start our own service
        val intent = Intent(this, UpdateUIByBroadcastService::class.java)
        startService(intent)
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(broadcastReceiver)
        super.onStop()
    }

    private fun updateUI(s: String) {
        binding.tvCount.text = s
    }
}
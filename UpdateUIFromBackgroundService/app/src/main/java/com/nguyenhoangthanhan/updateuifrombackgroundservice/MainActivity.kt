package com.nguyenhoangthanhan.updateuifrombackgroundservice

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nguyenhoangthanhan.updateuifrombackgroundservice.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), AppReceiver{
    private val TAG = "MainActivity_TAG"

    private lateinit var binding: ActivityMainBinding

    private var resultReceiver: CustomResultReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerService();
    }

    /*
     * Step 1: Register the intent service in the activity
     * */
    private fun registerService() {
        val intent = Intent(applicationContext, CustomService::class.java)

        /*
         * Step 2: We pass the ResultReceiver via the intent to the intent service
         * */
        resultReceiver = CustomResultReceiver(Handler(), this)
        intent.putExtra("receiver", resultReceiver)
        startService(intent)
    }


    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        /*
         * Step 3: Handle the results from the intent service here!
         * */

        val result:String? = resultData?.getString("current_status")
        if (result == null){
            Log.d(TAG, "result = null!!!!!!!!!!")
        }

        binding.tvCount.text = result

    }


    override fun onStop() {
        super.onStop()

        /*
         * Step 4: don't forget to clear receiver in order to avoid leaks.
         * */if (resultReceiver != null) {
            resultReceiver = null
        }
    }
}